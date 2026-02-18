package com.snake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Retrieves scores from file via GameModel and displays them to the user, If the file doesn't exist, this class makes it, again via GameModel.
 * @author Maxim Carr
 */
public class HighScoreController {
    /**
     * Stores title
     */
    @FXML
    private Label titleLabel;
    /** Score position label*/
    @FXML
    private Label score, score1,score2,score3,score4,score5,score6,score7,score8;
    /**
     * Score labels store each of the nine score entries in the file.
     */
    private final ArrayList<Label> scoreLabels = new ArrayList<>();
    /**
     * Returns the application to the main menu
     */
    @FXML
    protected void returnButton(){
        try{
            FXMLLoader loadStartScreen=new FXMLLoader(getClass().getResource("startScreenView.fxml"));
            Stage stage=(Stage) titleLabel.getScene().getWindow();
            Scene scene= new Scene(loadStartScreen.load());
            stage.setTitle("Snake: Main Menu");
            stage.setScene(scene);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    /**
     * Adds labels to ArrayList and fills in scores
     */
    @FXML
    protected void initialize(){
        scoreLabels.add(score);
        scoreLabels.add(score1);
        scoreLabels.add(score2);
        scoreLabels.add(score3);
        scoreLabels.add(score4);
        scoreLabels.add(score5);
        scoreLabels.add(score6);
        scoreLabels.add(score7);
        scoreLabels.add(score8);

        GameModel gameModel = new GameModel();
        ArrayList<String>scores = gameModel.getScoresFromFile();
        int i = 0;
        for(String score:scores){
            String line = score.substring(0,score.indexOf(' '))+" - "+score.substring(score.indexOf(' ')+1);
            scoreLabels.get(i).setText(line);
            i++;
        }
    }
}
