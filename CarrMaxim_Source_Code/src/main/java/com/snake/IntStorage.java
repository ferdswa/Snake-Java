package com.snake;

/**
 * Stores an integer - required for display loop to work as normal int is not final or semi-final
 * @author Maxim Carr
 */
public class IntStorage {
    /**
     * Currently stored integer
     */
    public int storedInt;

    /**
     * Sets stored int
     * @param i integer to be stores
     */
    public IntStorage(int i){
        storedInt=i;
    }
}
