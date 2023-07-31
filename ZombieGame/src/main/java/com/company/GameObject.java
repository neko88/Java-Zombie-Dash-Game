package com.company;

import javax.swing.*;

import java.awt.image.BufferedImage;
import java.util.Random;

import static com.company.GameScreen.environment;

public abstract class GameObject extends Environment {
    protected BufferedImage sprite;   // Object's image shown in game screen
    private int posX;       // Object's X position on grid
    private int posY;       // Object's Y position on grid
    JComponent jPos = new JComponent() {
        @Override
        public void setLocation(int x, int y) {
            super.setLocation(x, y);
        }
    };
    boolean hitObject = false; // if player hit (approached) object's position
    protected boolean isCollected = false;

    public int getPosX(){
        return this.posX;
    }
    public int getPosY(){
        return this.posY;
    }
    public void setPosX(int x){
        this.posX = x;
    }
    public void setPosY(int y){
        this.posY = y;
    }
    /**
     * Checks if where the punishment is placed is a valid space.
     * Takes row and column indices as parameters, returns bool
     * determining whether the move is valid or not.
     * @param x
     * @param y
     * @return boolean value
     */
    public boolean validMove(int y, int x) {
        if ( environment.getGridValue(y,x) <= 15 && environment.getGridValue(y,x) != 0 ) {
            return true;
        }
        else {
            return false;
        }
    }
    public void randPosition() {
        if (environment != null) {
            Random randomize = new Random();
            int max = MAX_GRID;
            posX = randomize.nextInt(max);
            posY = randomize.nextInt(max);
            // Bad location, reroll random reward starting values
            while (validMove(posY, posX) == false) {
                posX = randomize.nextInt(max);
                posY = randomize.nextInt(max);
            }
        }
    }

}
