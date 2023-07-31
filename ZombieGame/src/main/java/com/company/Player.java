package com.company;

import static com.company.SpriteManager.charTiles;

/**
 * @author      Jacky Li
 * @version     1.0
 * @since       1.0
 */

public class Player extends GameObject {
    /**
     * The name is given when the game asks for the players input.
     */
    String name;
    /**
     * Player's score determine whether the player will win or lose.
     */
    private static int score = 0;

    /**
     * Player's reward count is increased when the player picks up a reward in the game.
     */
    private int rewards = 0;

    /**
     * Player's bonus count is increased when the player picks up a bonus in the game.
     */
    private int bonus = 0;
    int speed;

    public Player() {
        this.sprite = charTiles.getSprite(0);
    }
    public String getName() {
        return name;
    }
    public void winReward(){
        score += 1;
        this.rewards++;
    }
    public void winBonus(){
        score += 10;
        this.bonus++;
    }
    public int getBonus(){
        return this.bonus;
    }
    public int getReward() {
        return this.rewards;
    }
    public void loseScore(){
        score--;
    }
    public void winScore(){
        score++;
    }
    public static int getScore(){return score;}
    public void resetScore() {score = 0;}
}



