package com.snake;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Class stores data and is used to pass info (scores and settings) between scenes.
 * @author Maxim Carr
 */
public class GameModel {
    /**Player's score at end of game*/
    private int endGameScore;
    /**The level the game is currently on*/
    private int currentLevel;
    /**User's current score - used to store score when level changes*/
    private int currentScore;
    /**True: the file has been written to successfully, False: the file hasn't been written to, usually because of the user not scoring high enough to be on the leaderboard*/
    private boolean written;
    /**A flag that stores and represents whether the user has asked for the dark colour scheme in settings or not - default is false*/
    private boolean darkFlag;
    /**A flag that stores and represents whether the user has asked for the theme tune to play in-game - default is true*/
    private boolean soundFlag;
    /**Stores whether the user has asked for a short game in settings - default is false*/
    private boolean shortFlag;
    /**Stores whether the user has asked for a medium-length game in settings - default is true*/
    private boolean medFlag;
    /**Stores whether the user has asked for an extended game - default is false*/
    private boolean longFlag;
    /**True: the user has beaten the game by exceeding Level 3's point threshold for whatever game length the user has selected, False: they haven't*/
    private boolean wonFlag;
    /**True: The user has beaten the game and has pressed the button to take them to endless Level 3, False: they haven't*/
    private boolean endless;
    /**The text that the user entered into the name field*/
    private String playerName;

    /**Lets the LevelController know if it should load Level 3 Endless
     * @param endless1 boolean - Is the game going into endless mode?
     */
    public void setEndless(boolean endless1){
        endless=endless1;
    }

    /**Gets whether the game is in level 3 endless mode - used by LevelController to disable point thresholds
     * @return boolean - Is the game currently in endless mode?
     */
    public boolean isEndless() {
        return endless;
    }

    /**Checks whether the user has beaten the game
     * @return boolean - Has the user won the game?
     */
    public boolean isWonFlag(){
        return wonFlag;
    }

    /**LevelController sets this to true when the user has beaten the game
     * @param won boolean - Has the user won the game?
     */
    public void setWonFlag(boolean won){
        wonFlag=won;
    }

    /**Gets the player's name
     * @return String - The player's name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**Sets the player's name from their input into the nameField, if it meets the conditions (0-9 characters, no spaces)
     * @param name String - The player's name inputted into the text box
     */
    public void setPlayerName(String name) {
        playerName = name;
    }

    /**Set by LevelController, this stores the current score in order to remember it after the next level has been loaded
     * @param score integer - The player's current score
     */
    public void setCurrentScore(int score){
        currentScore=score;
    }

    /**Gets the current score for display in new level
     * @return integer - The player's score at the end of the previous level
     */
    public int getCurrentScore(){
        return currentScore;
    }

    /**Gets the current level number, used by LevelController to know which sprites to spawn and how fast the snake's head should move and by SpriteModel to know how fast to move the snake's body
     * @return integer - The level the game is currently on
     */
    public int getCurrentLevel(){
        return currentLevel;
    }

    /**Sets the level to be loaded on score threshold (1->2, 2->3), endlessButton press (3), or new game (1)
     * @param level integer - The level to switch to
     */
    public void setCurrentLevel(int level){
        currentLevel=level;
    }

    /**Gets the user's sound setting
     * @return boolean - Has the user turned sound on?
     */
    public boolean getSoundFlag() {
        return soundFlag;
    }

    /**Set to true by SettingsController if the user has selected the sound checkbox in settings, false if otherwise - default is true
     * @param flag boolean - Has the user checked the sound box?
     */
    public void setSoundFlag(boolean flag){
        soundFlag = flag;
    }

    /**Gets the user's dark mode setting
     * @return boolean - Dark mode setting (should the game apply the dark mode colour theme to levels?)
     */
    public boolean getDarkFlag() {
        return darkFlag;
    }

    /**Set to true by SettingsController if the user has ticked the dark mode box in settings, false if otherwise - by default false
     * @param flag boolean - Is the dark mode box checked or has been left untouched?
     */
    public void setDarkFlag(boolean flag){
        darkFlag = flag;
    }
    /**Gets the flag based on the short game checkboxes' state - by default false
     * @return boolean - Has the user checked the short game box?
     */
    public boolean isShort(){
        return shortFlag;
    }
    /**Gets the flag based on the medium game checkboxes' state - by default true
     * @return boolean - Has the user checked the medium game box?
     */
    public boolean isMed(){
        return medFlag;
    }
    /**Gets the flag based on the long game checkboxes' state - by default false
     * @return boolean - Has the user checked the long game box?
     */
    public boolean isLong(){
        return longFlag;
    }

    /**
     * Sets the game to be short, depending on if the short game box was checked in Settings
     */
    public void setShortFlag(){
        shortFlag=true;
        medFlag=false;
        longFlag=false;
    }

    /**
     * Sets the game to be medium length, depending on if this box was checked in Settings, or if the user didn't change settings before going into the game
     */
    public void setMedFlag(){
        shortFlag=false;
        medFlag=true;
        longFlag=false;
    }
    /**
     * Sets the game to be short, depending on if the long game box was checked in Settings
     */
    public void setLongFlag(){
        longFlag=true;
        medFlag=false;
        shortFlag=false;
    }

    /**LevelController uses this on non-exit-button game over, sets the end game score for use in high score comparisons and file writing
     * @param score integer - The score at the end of the game
     */
    public void setEndGameScore(int score) {
        endGameScore=score;
    }

    /**Returns the score set by setEndGameScore() in order to compare it with stored scores and place it on the leaderboard if it is high enough
     * @return integer - The end-game score currently stored
     */
    public int getEndGameScore(){
        return endGameScore;
    }

    /**Shows whether the name and score of the user have been written to the file
     * @return boolean - True if file was written to successfully, false if it wasn't
     */
    public boolean getWritten() {
        return written;
    }

    /**Updates written depending on status of the score being written to the file
     * @param w integer - Store whether the user data has been written successfully
     */
    public void setWritten(boolean w){
        written=w;
    }

    /**The path to the high scores file
     * @return String - the path to the high scores file
     */
    private String getFilePath() {
        return "src\\main\\resources\\com\\snake\\sources\\high-scores.txt";
    }
    /**Shows if the high score file exists
     * @return boolean - Does the file exist or not
     */
    public boolean fileNotExists(){
        File hsFile = new File(getFilePath());
        return !hsFile.exists();
    }
    /**
     * Creates a blank high score file, used on first run and by SettingsController to wipe high scores on user request
     */
    private void createFile(){
        try {
            File file = new File(getFilePath());
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < 8; i++) {
                fw.write("--- 0\n");
            }
            fw.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**Tests whether the score can be written to the file: If it can't, returns -1; If the file is empty, returns -2 which will be used to place the score at the top of the leaderboard; If it can be written and scores already are present returns the index of where to place the current player's score
     * @param scores ArrayList of scores in String form
     * @return integer - Status code of whether the score can be written to the file and if it can, what position to put it in.
     */
    private int getInsertIndex(ArrayList<String>scores){
        if(scores.isEmpty()){
            return -2;//New file
        }
        else if((scores.size()>=8) && (getEndGameScore()<=parseInt(scores.getLast().substring(scores.getLast().indexOf(' ')+1)))){
            return -1;//Not valid score
        }
        for(String sc:scores){
            if(getEndGameScore()>parseInt(sc.substring(sc.indexOf(' ')+1))){
                return scores.indexOf(sc);
            }
        }
        return -1;
    }

    /**
     * Clears high score file on user request from Settings view
     */
    public void clearHighScores(){
        createFile();
    }

    /**Reads names and scores from file and returns them as an ArrayList, used both for setScoresToFile's sorting and index calculation and for HighScoreController to display the current 9 high scores to the user
     * @return ArrayList of Strings that contain the lines in high-scores.txt
     */
    public ArrayList<String> getScoresFromFile(){
        String line;
        ArrayList<String>lines = new ArrayList<>();
        if(fileNotExists()){
            createFile();
            return lines;
        }
        try{
            File file = new File(getFilePath());
            Scanner readF = new Scanner(file);
            while(readF.hasNextLine()){
                line = readF.nextLine();
                lines.add(line);
            }
            readF.close();
            return lines;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes scores to file by finding the correct position of the score, or sets written to false if the player didn't score high enough to place on the leaderboard
     */
    public void setScoresToFile(){
        int index;
        String line = String.format(getPlayerName()+" %d",getEndGameScore());
        if(fileNotExists()){
            createFile();
        }
        ArrayList<String>existing = getScoresFromFile();
        existing.sort(Comparator.comparing(s->parseInt(s.substring(s.indexOf(' ')+1)),Comparator.reverseOrder()));
        index = getInsertIndex(existing);
        if(index==-2){
            existing.addFirst(line);
            setWritten(true);
        }
        else if(index!=-1){
            if(existing.size()>8){existing.removeLast();}
            existing.add(index,line);
            setWritten(true);
        }
        else{
            setWritten(false);
        }
        if(getWritten()){
            try {
                File file = new File(getFilePath());
                FileWriter fw = new FileWriter(file);
                for (String ln : existing) {
                    fw.write(ln+"\n");
                }
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
