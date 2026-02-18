package com.snake;

/**Separate Main class that does not extend Application
 * @author Maxim Carr
 */
public class Main {
    /**Allows Maven to be delegated build and run tasks successfully by not being in a class that extends Application
     * @param args Command-line arguments
     */
    public static void main(String[]args){
        Play.run();
    }
}
