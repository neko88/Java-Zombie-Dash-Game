package com.company;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {
    Game game = new Game(700, 700);
    Scoreboard scoreboard = new Scoreboard();
    @Test
    void addScore() {
        //test add score function
        //initialize two players
        Player player=new Player();
        Player player2=new Player();
        //the original size of score board should be 0
        assertEquals(0,scoreboard.scoreboard.size());
        //after insert add those two player to the scoreboard
        scoreboard.addScore(player);
        scoreboard.addScore(player2);
        //the size of the score board should be 2
        assertEquals(2,scoreboard.scoreboard.size());
    }
}