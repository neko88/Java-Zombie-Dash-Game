package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Game game = new Game(700, 700);
    Player player = new Player();

    @Test
    void winReward() {
        assertEquals(0,player.getReward());
        player.winReward();
        player.winReward();
        assertEquals(2,player.getReward());
        player.winReward();
        assertEquals(3,player.getReward());
    }

    @Test
    void winBonus() {
        assertEquals(0,player.getBonus());
        assertEquals(0,player.getScore());
        player.winBonus();
        player.winBonus();
        assertEquals(2,player.getBonus());
        assertEquals(20,player.getScore());
        player.winBonus();
        assertEquals(3,player.getBonus());
        assertEquals(30,player.getScore());
    }

    @Test
    void loseScore() {
        player.resetScore();
        player.winBonus();
        player.winBonus();
        player.loseScore();
        player.loseScore();
        assertEquals(18,player.getScore());
        player.loseScore();
        player.loseScore();
        assertEquals(16,player.getScore());
    }

    @Test
    void winScore() {
        player.resetScore();
        player.winScore();
        player.winScore();
        assertEquals(2,player.getScore());
        player.winScore();
        player.winScore();
        assertEquals(4,player.getScore());
    }

    @Test
    void resetScore() {
        player.winBonus();
        player.winBonus();
        assertEquals(20,player.getScore());
        player.resetScore();
        assertEquals(0,player.getScore());
    }
}