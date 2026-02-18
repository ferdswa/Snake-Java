package com.snake;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**Controls the display of the main menu
 * @author Maxim Carr
 */
public class StartScreenController {
    /**
     * Holds title text, used by button press methods to target stage for switching views
     */
    @FXML
    private Label titleLabel;

    /**
     * Loads first level, resets current level to 1, removes endless mode if it was enabled and chooses view based on user setting stored in GameModel
     */
    @FXML
    protected void newGameButton(){
        try{
            FXMLLoader loadLevelOne;
            Stage stage=(Stage) titleLabel.getScene().getWindow();
            GameModel gameModel = (GameModel) stage.getUserData();
            if(gameModel.getDarkFlag()){
                loadLevelOne=new FXMLLoader(getClass().getResource("levelOneDarkView.fxml"));
            }
            else{
                loadLevelOne=new FXMLLoader(getClass().getResource("levelOneView.fxml"));
            }
            gameModel.setCurrentLevel(1);
            gameModel.setEndless(false);
            stage.setUserData(gameModel);
            Scene scene= new Scene(loadLevelOne.load());
            stage.setTitle("Snake: Level 1");
            stage.setScene(scene);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    /**
     * Switches program to Hints and Info view
     */
    @FXML
    protected void hintButton(){
        try{
            FXMLLoader loadLevelOne;
            Stage stage=(Stage) titleLabel.getScene().getWindow();
            loadLevelOne=new FXMLLoader(getClass().getResource("hintView.fxml"));
            Scene scene= new Scene(loadLevelOne.load());
            stage.setTitle("Snake: Info & Hints");
            stage.setScene(scene);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    /**Switches program to High Scores view, display of high scores is handled by HighScoreController
     */
    @FXML
    protected void highScoreButton(){
        try{
            FXMLLoader loadHighScoreScreen=new FXMLLoader(getClass().getResource("highScoreView.fxml"));
            Stage stage=(Stage) titleLabel.getScene().getWindow();
            Scene scene= new Scene(loadHighScoreScreen.load());
            stage.setTitle("Snake: High Scores");
            stage.setScene(scene);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
    /**Switches program to Settings view allowing SettingsController to take user options and set them in GameModel
     */
    @FXML
    protected void settingsButton(){
        try{
            FXMLLoader loadHighScoreScreen=new FXMLLoader(getClass().getResource("settingsView.fxml"));
            Stage stage=(Stage) titleLabel.getScene().getWindow();
            Scene scene= new Scene(loadHighScoreScreen.load());
            stage.setTitle("Snake: Settings");
            stage.setScene(scene);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
    /**Closes application safely with exit code 0
     */
    @FXML
    protected void exitButton() {
        System.exit(0);
    }
}