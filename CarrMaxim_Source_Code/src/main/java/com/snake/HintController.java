package com.snake;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controls the display of hints and game information which are shown on first run or by clicking the relevant button on the main menu
 */
public class HintController {
    /**
     * Stores title text, used to determine Stage and switch back to main menu
     */
    @FXML
    private Label titleLabel;
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
}

