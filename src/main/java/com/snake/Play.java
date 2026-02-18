package com.snake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
/**
 * Play contains the main setup method start()
 * @author Maxim Carr - Modified
 */
public class Play extends Application {

    /**
     * Sets game icon, sets width and height parameters for the window and stops it from being resized, then looks for high score file and loads first scene depending on its presence (exists = main menu, doesn't exist = hints and information).
     * @param startStage Stage - the window that the program runs in
     * @throws IOException IOException - throws if a file cannot be found
     */
    //Programs of Theseus
    @Override
    public void start(Stage startStage) throws IOException {
        FXMLLoader startLoader = new FXMLLoader(Play.class.getResource("startScreenView.fxml"));
        Scene scene = new Scene(startLoader.load(), 800, 600);
        FXMLLoader hintLoader = new FXMLLoader(Play.class.getResource("hintView.fxml"));
        Scene firstRun = new Scene(hintLoader.load(),800,600);
        startStage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("sources/snake-icon.png"))));
        GameModel gameModel = new GameModel();
        gameModel.setSoundFlag(true);
        gameModel.setMedFlag();
        if(gameModel.fileNotExists()){
            gameModel.clearHighScores();
            startStage.setTitle("Snake: Info & Hints");
            startStage.setScene(firstRun);
        }
        else{
            startStage.setTitle("Snake: Main Menu");
            startStage.setScene(scene);
        }
        startStage.setUserData(gameModel);
        startStage.setResizable(false);
        startStage.show();
    }

    /**Launches JavaFX application and runs start method
     */
    public static void run() {
        launch();
    }
}