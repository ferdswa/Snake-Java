package com.snake;
/**
 * Stores a long value - required for display loop to work as normal long is not final or semi-final - used for inter-frame time calculation
 * @author Maxim Carr
 */
class LongStorage {
    /**
     * Stored long value
     */
    public long storedLong;
    /**
     * Sets stored long.
     * @param l long that is to be stored
     */
    public LongStorage(long l) {
        storedLong = l;
    }
}
