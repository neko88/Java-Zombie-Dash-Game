// Reward class
package com.company;

import java.util.Random; // Randomize reward location

import static com.company.SpriteManager.rewardTiles;

/**
 * @author      Natalie Lo, Rebekah Wong, HuanYu Zhou, Jacky Li
 * @version     1.0
 * @since       1.0
 */

public class Reward extends GameObject{
    public boolean isCollected;
    public Reward(){
        Random rand = new Random();
        this.sprite = rewardTiles.getSprite(rand.nextInt(rewardTiles.getList().size()));
        randPosition();
        isCollected = false;
    }
}