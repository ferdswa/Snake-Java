package com.snake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controls the end game screen - Image used in view is the one from the original codebase - Picture is correct size - Displays score, allows user to save if they scored high enough - If the user has won the game, they can pick Endless Mode from this screen.
 * @author Maxim Carr
 */
public class GameOverController {
    /**
     * Shows user score
     */
    @FXML
    private Label scoreLabel;
    /**
     * Shows 'Name: ' on screen
     */
    @FXML
    private Label nameLabel;
    /**
     * Shows messages to user relating to status of their score being saved
     */
    @FXML
    private Label errorLabel;
    /**
     * Congratulates user if they've beaten the game
     */
    @FXML
    private Label congratsLabel;
    /**
     * Text field for user to enter their name
     */
    @FXML
    private TextField nameField;
    /**
     * Button for user to save their score
     */
    @FXML
    private Button saveButton;
    /**
     * Button to launch endless mode
     */
    @FXML
    private Button endlessButton;

    /**
     * Displays end-game score in the endScore label.
     */
    @FXML
    protected void displayScore(){
        Stage stage =(Stage) scoreLabel.getScene().getWindow();
        GameModel gm = (GameModel) stage.getUserData();
        scoreLabel.setText(String.format("SCORE: %d",gm.getEndGameScore()));
        nameLabel.setVisible(true);
        nameField.setVisible(true);
        saveButton.setVisible(true);
        if(gm.isWonFlag()){
            congratsLabel.setVisible(true);
            endlessButton.setVisible(true);
        }
    }

    /**
     * Loads the game back into Level Three for an endless run, passing the user's existing score to it via GameModel. GameModel tells this method whether the user requested dark mode.
     */
    @FXML
    protected void endlessMode(){
        try{
            FXMLLoader loadLevelThree;
            Stage stage=(Stage) scoreLabel.getScene().getWindow();
            GameModel gameModel = (GameModel) stage.getUserData();
            if(gameModel.getDarkFlag()){
                loadLevelThree=new FXMLLoader(getClass().getResource("levelThreeDarkView.fxml"));
            }
            else{
                loadLevelThree=new FXMLLoader(getClass().getResource("levelThreeView.fxml"));
            }
            gameModel.setCurrentLevel(3);
            gameModel.setEndless(true);
            gameModel.setCurrentScore(gameModel.getEndGameScore());
            stage.setUserData(gameModel);
            Scene scene= new Scene(loadLevelThree.load());
            stage.setTitle("Snake: Level 3");
            stage.setScene(scene);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    /**
     * Allows the user to save their top-9 score if they achieved it, score is written to file via GameModel
     */
    @FXML
    protected void saveScore(){
        errorLabel.setVisible(false);
        Stage stage =(Stage) scoreLabel.getScene().getWindow();
        GameModel gameModel = (GameModel) stage.getUserData();
        if(!gameModel.getWritten()) {
            if (!(nameField.getText().isEmpty()) && (nameField.getText().length() <= 9) && (!(nameField.getText().contains(" ")))) {
                gameModel.setPlayerName(nameField.getText().toUpperCase());
                gameModel.setScoresToFile();
                if (gameModel.getWritten()) {
                    errorLabel.setText("Your score and name have been saved successfully!");
                    errorLabel.setVisible(true);
                } else {
                    errorLabel.setText("You didn't score high enough for the leaderboard. :(\nTry again?");
                    errorLabel.setVisible(true);
                }
            } else {
                errorLabel.setText("Error: Name must be between 1 and 9 characters inclusive!\nNo spaces allowed!");
                errorLabel.setVisible(true);
            }
        }
        else{
            errorLabel.setText("Error: You've already saved your high score!");
            errorLabel.setVisible(true);
        }
    }

    /**
     * Returns the application to the main menu
     */
    @FXML
    protected void returnButton(){
        try{
            FXMLLoader loadStartScreen=new FXMLLoader(getClass().getResource("startScreenView.fxml"));
            Stage stage=(Stage) scoreLabel.getScene().getWindow();
            Scene scene= new Scene(loadStartScreen.load());
            GameModel gameModel = (GameModel) stage.getUserData();
            gameModel.setWritten(false);
            stage.setTitle("Snake: Main Menu");
            stage.setScene(scene);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    /**
     * Hides content until requirements are met (keeps the screen clean)
     */
    @FXML
    protected void initialize(){
        nameLabel.setVisible(false);
        nameField.setVisible(false);
        saveButton.setVisible(false);
        errorLabel.setVisible(false);
        congratsLabel.setVisible(false);
        endlessButton.setVisible(false);
    }
}
