package com.snake;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.SnapshotParameters;


import java.util.Objects;
import java.util.Random;

/**
 * Stores sprite data and performs sprite logic - Based on GameUtil, ImageUtil, Snake, Food
 * @author Maxim Carr - Modified
 */
public class SpriteModel {
    /**
     * Sprite image
     */
    private Image img;
    /**X ordinate of Sprite*/
    private double posX;
    /** Y ordinate of Sprite*/
    private double posY;
    /** X velocity of Sprite*/
    private double velX;
    /** Y velocity of Sprite*/
    private double velY;
    /** Stored X ordinate of Sprite*/
    private double storedX;
    /** Stored Y ordinate of Sprite*/
    private double storedY;
    /** Stored X velocity of Sprite*/
    private double storedVX;
    /** Stored Y velocity of Sprite*/
    private double storedVY;
    /**
     * Width of image
     */
    private double width;
    /**
     * Height of image
     */
    private double height;
    /**
     * Current Sprite direction
     */
    private int currentDegree;
    /**
     * Stored sprite direction
     */
    private int storedDir;
    /**
     * Sprite that this Sprite is following
     */
    private SpriteModel following;
    /**
     * Most basic information for new sprite before assignment
     */
    public SpriteModel(){
        posX=0; posY=0;velY=0;velX=0;
    }
    /**
     * Sets stored position of sprite just in case the user presses pause
     * @param x double - X position of sprite
     * @param y double - Y position of sprite
     * @param vx double - X velocity of sprite
     * @param vy double - Y velocity of sprite
     * @param deg integer - Current direction of sprite
     */
    public void setStored(double x,double y,double vx,double vy, int deg){
        storedX=x;storedY=y;storedVX=vx;storedVY=vy;storedDir=deg;
    }

    /**Gets stored X position - used for pausing and un-pausing game
     * @return double - Stored X position of Sprite
     */
    public double getStoredX() {
        return storedX;
    }
    /**Gets stored Y position - used for pausing and un-pausing game
     * @return double - Stored Y position of Sprite
     */
    public double getStoredY() {
        return storedY;
    }
    /**Gets stored X velocity - used for pausing and un-pausing game
     * @return double - Stored X velocity of Sprite
     */
    public double getStoredVX() {
        return storedVX;
    }
    /**Gets stored Y velocity - used for pausing and un-pausing game
     * @return double - Stored Y velocity of Sprite
     */
    public double getStoredVY() {
        return storedVY;
    }
    /**Gets stored X velocity - used for pausing and un-pausing game
     * @return integer - Stored direction
     */
    public int getStoredDir() {
        return storedDir;
    }

    /**Sets Sprite image from sources folder using the provided path
     * @param imagePath Image to be set
     */
    public void setImage(String imagePath)
    {
        Image i = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(imagePath)));
        img = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    /**Gets current X position of the sprite
     * @return double - Current X position of sprite
     */
    public double getPosX(){
        return posX;
    }
    /**Gets current Y position of the sprite
     * @return double - Current X position of sprite
     */
    public double getPosY(){
        return posY;
    }
    /**Sets current position of the sprite as an X,Y coordinate
     * @param x double - X position of Sprite
     * @param y double - Y position of Sprite
     */
    public void setPosition(double x, double y)
    {
        posX = x;
        posY = y;
    }
    /**Sets current velocity of the sprite as an X,Y vector
     * @param vX double - X velocity of Sprite
     * @param vY double - Y velocity of Sprite
     */
    public void setVel(int vX, int vY){
        velX=vX;
        velY=vY;
    }
    /**Gets current X velocity of sprite
     * @return double - Current X velocity of sprite
     */
    public int getVelX(){
        return (int)velX;
    }
    /**Gets current Y velocity of sprite
     * @return double - Current Y velocity of sprite
     */
    public int getVelY(){
        return (int)velY;
    }

    /**Updates sprite position on screen, only used with moving sprites.
     * @param time double - inter-frame elapsed time
     */
    public void updatePosition(double time){//movement
        posX+= velX*time;
        posY+= velY*time;
    }

    /**Makes snake body sprites follow the one in front of them, the head if it's the first part of the body, this makes the method handle cornering by having the current sprite 'latch on' to a position 25 px behind the previous one based on direction of travel.
     * @param multiplier integer - current level which is also snake speed modifier
     */
    public void follow(int multiplier){//Tail object logic
        double upperX, lowerX,upperY,lowerY;
        SpriteModel followed = this.getFollowing();
        int chainDirection = followed.getCurrentDegree();
        int myDirection = this.getCurrentDegree();
        if(multiplier==1) {
            upperX = followed.getPosX() + 8;
            lowerX = upperX - 16;
            upperY = followed.getPosY() + 8;
            lowerY = upperY - 16;
        }
        else {
            upperX = followed.getPosX() + (3 * multiplier);
            lowerX = upperX - (6 * multiplier);
            upperY = followed.getPosY() + (3 * multiplier);
            lowerY = upperY - (6 * multiplier);
        }
        if(chainDirection==90&&myDirection==0 || chainDirection==90&&myDirection==180){
            if(lowerY<=getPosY()&& getPosY()<=upperY){
                this.setCurrentDegree(90);
                this.setPosition(followed.getPosX()-25, followed.getPosY());
            }
        }
        else if(chainDirection==-90&&myDirection==0 || chainDirection==-90&&myDirection==180){
            if(lowerY<=getPosY()&& getPosY()<=upperY){
                this.setCurrentDegree(-90);
                this.setPosition(followed.getPosX()+25, followed.getPosY());
            }
        }
        else if(myDirection==90&&chainDirection==0 || myDirection==-90&&chainDirection==0){
            if(lowerX<=getPosX()&& getPosX()<=upperX){
                this.setCurrentDegree(0);
                this.setPosition(followed.getPosX(), followed.getPosY()+25);
            }
        }
        else if(myDirection==90&&chainDirection==180 || myDirection==-90&&chainDirection==180){
            if(lowerX<=getPosX()&& getPosX()<=upperX){
                this.setCurrentDegree(180);
                this.setPosition(followed.getPosX(), followed.getPosY()-25);
            }
        }
        else{
            this.setCurrentDegree(getCurrentDegree());
        }
        this.setBoundary();
    }
    /**
     * Sets the position of the new segment of the snake's body to be behind the preceding one (snake-body.png is 25 pixels across) based on preceding sprite's direction of travel*/
    public void segmentSetup(){
        int direction = this.getFollowing().getCurrentDegree();
        this.setCurrentDegree(direction);
        switch (direction) {
            case 0:
                this.setPosition(this.getFollowing().getPosX(), (this.getFollowing().getPosY() + 25));
                break;
            case 90:
                this.setPosition((this.getFollowing().getPosX() - 25), this.getFollowing().getPosY());
                break;
            case 180:
                this.setPosition(this.getFollowing().getPosX(), (this.getFollowing().getPosY() - 25));
                break;
            case -90:
                this.setPosition((this.getFollowing().getPosX() + 25), this.getFollowing().getPosY());
                break;
        }
        this.setVel(this.getFollowing().getVelX(), this.getFollowing().getVelY());
    }
    /**Draws sprite on Canvas
     * @param graphicsContext GraphicsContext - context in which the Sprite is drawn
     */
    public void render(GraphicsContext graphicsContext){//draws sprite on screen
        graphicsContext.drawImage(img,posX,posY);
    }

    /**
     * Sets image bounds for collisions
     */
    private void setBoundary(){
        width = img.getWidth();
        height = img.getHeight();
    }

    /**Returns sprite boundary for collision check
     * @return Rectangle2D - Has a property getIntersects() that allows program to see if a collision has occurred
     */
    private Rectangle2D getBoundary()
    {
        return new Rectangle2D(posX,posY,width,height);
    }

    /**Shorter way of writing intersection check in LevelController, used for detecting collisions
     * @param sprite Sprite - current sprite
     * @return boolean - has a collision occurred?
     */
    public boolean intersects(SpriteModel sprite)
    {
        return sprite.getBoundary().intersects(this.getBoundary());
    }

    /**Sets sprite direction
     * @param deg integer - Desired sprite direction
     */
    public void setCurrentDegree(int deg){
        currentDegree=deg;
    }

    /**Returns sprite direction
     * @return integer - The direction the sprite is heading
     */
    public int getCurrentDegree(){
        return currentDegree;
    }

    /**Sets which body part this sprite has to follow
     * @param prev Sprite - preceding snake segment
     */
    public void setFollowing(SpriteModel prev) {
        following = prev;
    }

    /**Gets the preceding sprite in the snake's body, which is the one this is following
     * @return Sprite - the sprite this Sprite is following
     */
    public SpriteModel getFollowing() {
        return following;
    }

    /**Changes Sprite and sprite image direction by a number of degrees calculated by subtracting the current direction (-90 = left, 0 = up, 90 = right, 180 = down) from the desired direction (-90 = left, 0 = up, 90 = right, 180 = down)
     * @param newDegree integer - The direction to turn the sprite to (-90 = left, 0 = up, 90 = right, 180 = down)
     */
    public void rotateByDegree(int newDegree){
        if(newDegree != currentDegree) {//Prevents unnecessary rotation
            Image tempImage = img;
            int rotateBy = newDegree - currentDegree;
            ImageView imageView = new ImageView(tempImage);
            imageView.setRotate(rotateBy);
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            setCurrentDegree(newDegree);
            img = imageView.snapshot(parameters, null);
            this.setBoundary();
        }
    }

    /**Returns a random number between 0 and 17 to be used in spawnFruit to pick a random food image
     * @return integer - random integer between 0 and 17
     */
    private int randomFruitGen(){
        Random rng = new Random();
        return rng.nextInt(18);
    }

    /**Returns a random X position on the screen between 99 and 699 pixels
     * @return integer - random X position between 99 and 699
     */
    private int randomScreenPositionX(){
        Random rng = new Random();
        return rng.nextInt(100,700);
    }
    /**Returns a random Y position on the screen between 99 and 499 pixels
     * @return integer - random Y position between 99 and 499
     */
    public int randomScreenPositionY(){
        Random rng = new Random();
        return rng.nextInt(100,500);
    }

    /**Spawns a wall at random position and random direction, away from snake head to stop instant game over
     * @param graphicsContext GraphicsContext - the context to spawn wall into
     */
    public void spawnWall(GraphicsContext graphicsContext){
        this.setImage("sources/wall.png");
        this.setPosition(this.randomScreenPositionX(),this.randomScreenPositionY());
        if(this.posX<200){
            this.setPosition(this.randomScreenPositionX(),this.randomScreenPositionY());
        }
        this.setCurrentDegree(0);
        Random rng = new Random();
        int n = rng.nextInt(4);
        switch(n){
            case 0:
                this.rotateByDegree(0);
                break;
            case 1:
                this.rotateByDegree(90);
                break;
            case 2:
                this.rotateByDegree(180);
                break;
            case 3:
                this.rotateByDegree(-90);
                break;
        }
        this.setBoundary();
        this.render(graphicsContext);
    }

    /**Renders a fruit sprite on screen with a random picture at a random position within the bounds of randomScreenPositionX, randomScreenPositionY
     * @param graphicsContext GraphicsContext - the level's graphics context used to spawn fruit into
     */
    public void spawnFruit(GraphicsContext graphicsContext){
        this.setImage(String.format("sources/food-%d.png", this.randomFruitGen()));
        this.setPosition(this.randomScreenPositionX(), this.randomScreenPositionY());
        this.render(graphicsContext);
    }

    /**Renders snake head on screen at a set position and sets its direction to face right, towards the centre of the screen
     * @param graphicsContext GraphicsContext - the level's graphics context used to spawn snake head into
     */
    public void spawnSnakeHead(GraphicsContext graphicsContext){
        this.setImage("sources/snake-head.png");
        this.setPosition(100,300);
        this.render(graphicsContext);
        this.setCurrentDegree(90);
    }
}
