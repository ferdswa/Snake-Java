package com.snake;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controls the screen while the user is in the settings view, provides controls to set game settings
 */
public class SettingsController {
    /**
     * Stores title text, used for getting the Scene which is then used to switch back to Main Menu
     */
    @FXML
    private Label titleLabel,
    /**
     * Displays errors or status messages to user, e.g. tell them that the high scores file has been cleared on them clicking the clear high scores button
     */
    statusLabel;
    /**
     * The current program instances' GameModel, which is used by settings to save the user's input and get the previously checked game length box, or default (medium) if the user hasn't checked a different box this run.
     */
    private GameModel gameModel;
    /**
     * If this is checked in Settings, frogger will play
     */
    @FXML
    private CheckBox soundCB;
    /**
     * If this is checked in Settings, dark mode will be applied to levels (default is false)
     */
    @FXML
    private CheckBox darkCB;
    /**
     * Sets game to be short (26 points for full game)
     */
    @FXML
    private CheckBox shortGameCB;
    /**
     * Sets game to be medium (51 points for full game)
     */
    @FXML
    private CheckBox medGameCB;
    /**
     * Sets game to be long (126 points for full game)
     */
    @FXML
    private CheckBox longGameCB;
    /**
     * Sends user back to main menu, applies settings by passing them to GameModel
     */
    @FXML
    protected void returnButton(){
        try{
            FXMLLoader loadStartScreen=new FXMLLoader(getClass().getResource("startScreenView.fxml"));
            Stage stage=(Stage) titleLabel.getScene().getWindow();
            gameModel= (GameModel) stage.getUserData();
            gameModel.setDarkFlag(darkCB.isSelected());
            gameModel.setSoundFlag(soundCB.isSelected());
            if(((shortGameCB.isSelected())&&(!medGameCB.isSelected())&&(!longGameCB.isSelected()))||((!shortGameCB.isSelected())&&(medGameCB.isSelected())&&(!longGameCB.isSelected()))||((!shortGameCB.isSelected())&&(!medGameCB.isSelected())&&(longGameCB.isSelected()))) {
                if (shortGameCB.isSelected())
                    gameModel.setShortFlag();
                else if (medGameCB.isSelected())
                    gameModel.setMedFlag();
                else
                    gameModel.setLongFlag();
                stage.setUserData(gameModel);
                Scene scene= new Scene(loadStartScreen.load());
                stage.setTitle("Snake: Main Menu");
                stage.setScene(scene);
            }
            else if(!(shortGameCB.isSelected()&&medGameCB.isSelected()&&longGameCB.isSelected())){
                if(gameModel.isShort()){
                    shortGameCB.setSelected(true);
                    medGameCB.setSelected(false);
                    longGameCB.setSelected(false);
                }
                else if(gameModel.isMed()){
                    shortGameCB.setSelected(false);
                    medGameCB.setSelected(true);
                    longGameCB.setSelected(false);
                }
                else if(gameModel.isLong()){
                    shortGameCB.setSelected(false);
                    medGameCB.setSelected(false);
                    longGameCB.setSelected(true);
                }
                statusLabel.setText("STATUS: You forgot to check one box, so we got the stored option.\nYou can select a different game length or return to the main menu now");
                statusLabel.setVisible(true);
            }
            else{
                statusLabel.setText("STATUS: Only one game length can be selected. Try again.");
                statusLabel.setVisible(true);
            }
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    /**
     * Clears high score file on button press
     */
    @FXML
    protected void deleteHighScores(){
        Stage stage=(Stage) titleLabel.getScene().getWindow();
        gameModel=(GameModel) stage.getUserData();
        gameModel.clearHighScores();
        statusLabel.setText("STATUS: High Scores Cleared Successfully");
        statusLabel.setVisible(true);
    }

    /**
     * Hides message label until its needed
     */
    @FXML
    protected void initialize(){
        statusLabel.setVisible(false);
    }
}
