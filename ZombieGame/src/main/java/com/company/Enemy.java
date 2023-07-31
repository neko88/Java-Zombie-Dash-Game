// Moving enemy class
package com.company;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.image.BufferedImage;
import java.util.Random; // Randomize enemy location

import static com.company.GameScreen.environment;
import static com.company.SpriteManager.enemyTiles;
import static com.company.SpriteManager.rewardTiles;

/**
 * @author      Rebekah Wong
 * @version     1.0
 * @since       1.0
 */

// Need to access map from Environment class
// Extends either Player or Environment (WIP)
class Enemy extends GameObject {
    // Make all variables public to pass by reference instead of pass by value
    public Enemy ()
    {
        Random rand = new Random();
        this.sprite = enemyTiles.getSprite(rand.nextInt(enemyTiles.getList().size()));
        randPosition();
    }
    int posX;
    int posY;
    @Override
    public int getPosX(){
        return this.posX;
    }
    @Override
    public int getPosY(){
        return this.posY;
    }
    @Override
    public void setPosX(int x){
        this.posX = x;
    }
    @Override
    public void setPosY(int y){
        this.posY = y;
    }
    @Override
    public void randPosition() {
        if (environment != null) {
            Random randomize = new Random();
            int xMax = 600;
            int yMax = 600;
            this.posX = randomize.nextInt(xMax);
            this.posY = randomize.nextInt(yMax);
            // Bad location, reroll random reward starting values

        }
    }

  }