package com.company;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static com.company.GameScreen.environment;
import static org.junit.jupiter.api.Assertions.*;

class GamePlayTest {
    Game game = new Game(700, 700);
    GamePlay gameplay = new GamePlay(2,2,2,2,2);

    @Test
    void isAllRewardsCollected() {//test the isAllRewardsCollected() function
        //set the amount of reward to 2
        //gameplay.setRandObject(2, gameplay.randReward, Reward.class);
        //it should return false when the two amount is different.
        //assertEquals(false,gameplay.isAllRewardsCollected());
        //by running winReward() two times we can set the reward amount that the player collect to 2
        gameplay.player.winReward();
        gameplay.player.winReward();
        //if everything works well, the isAllRewardsCollected() function should return true
        assertEquals(true,gameplay.isAllRewardsCollected());

    }


    @Test
    void checkNearbyEnemy() {
        //set the image of the enemy to null
        BufferedImage sprite = null;
        Enemy enemy = new Enemy();
        ArrayList<GameObject> enemies = new ArrayList<>();
        enemies.add(new Enemy());
        //set the enemy's location to (10,10)
        enemy.setLocation(10,10);
        //set the Player's location to (10,10)
        gameplay.player.setLocation(10,10);
        //Start the game
        Game.isStart=true;
        //running the function
        gameplay.checkObject(enemies);
        //the player should be hit
        //assertEquals(true, enemy.hitObject);
        //change the player's location away from enemy
        gameplay.player.setLocation(20,10);
        //run the function
        gameplay.checkObject(enemies);
        //assert the player should not be hit
        assertEquals(false, enemy.hitObject);
    }
    /*
        @Test
        void checkNearbyReward() {
            Game game = new Game(700, 700);
            GamePlay gameplay = new GamePlay();
            //Reward reward=new Reward();
            gameplay.setRandReward(1);
            gameplay.player.setLocation(gameplay.randReward.get(0).jPos.getLocation());
            Game.isStart=true;
            assertEquals(true,gameplay.checkNearbyReward());
            gameplay.player.setLocation(gameplay.randReward.get(0).jPos.getX()+10,gameplay.randReward.get(0).jPos.getY()+10);
            assertEquals(false,gameplay.checkNearbyReward());
        }

        @Test
        void checkNearbyBonus() {
            Game game = new Game(700, 700);
            GamePlay gameplay = new GamePlay();
            gameplay.setRandBonus(1);
            gameplay.player.setLocation(gameplay.randBonus.get(0).jPos.getLocation());
            Game.isStart=true;
            assertEquals(true,gameplay.checkNearbyBonus());
            gameplay.player.setLocation(gameplay.randBonus.get(0).jPos.getX()+10,gameplay.randBonus.get(0).jPos.getY()+10);
            assertEquals(false,gameplay.checkNearbyBonus());
        }

        @Test
        void checkNearbyPunishment() {
            Game game = new Game(700, 700);
            GamePlay gameplay = new GamePlay();
            gameplay.setRandPunishment(1);
            gameplay.player.setLocation(gameplay.randPunishment.get(0).getLocation());
            Game.isStart=true;
            assertEquals(true,gameplay.checkNearbyPunishment());
            gameplay.player.setLocation(gameplay.randPunishment.get(0).getX()+10,gameplay.randPunishment.get(0).getY()+10);
            assertEquals(false,gameplay.checkNearbyPunishment());
        }
    */
    @Test
    void checkFinish() {
        //set the player's location to the end point
        int x=6*environment.endX;
        int y=6*environment.endY;
        //start the game
        Game.isStart=true;
        //assume player collect all the rewards
        gameplay.collectedAllRewards = true;
        //set the score of player to be positive
        GamePlay.player.winScore();
        //run the function
        gameplay.checkFinish (x, y);
        //the result should be true
        assertEquals(true,gameplay.isWin);
        //what if we set the game haven't started yet
        Game.isStart=false;
        //the result should be false
        assertEquals(false,gameplay.checkFinish (x, y));
        Game.isStart=true;
        //what if we change the location away from the end point
        x=6*environment.endX-10;
        //the result should be false
        assertEquals(false,gameplay.checkFinish (x, y));
        //what if we let the player haven't collect every rewards
        gameplay.collectedAllRewards = false;
        x=6*environment.endX;
        //the result should be false
        assertEquals(false,gameplay.checkFinish (x, y));
    }

}
