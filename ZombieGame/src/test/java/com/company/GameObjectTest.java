package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {

    Game game = new Game(700, 700);
    Bonus bonus=new Bonus();
    Enemy enemy = new Enemy();
    Reward reward = new Reward();
    Player player = new Player();
    Punishment punishment = new Punishment();

    @Test
    void setPosX() {
        //running the function to set the X
        bonus.setPosX(10);
        enemy.setPosX(20);
        reward.setPosX(-5);
        player.setPosX(15);
        punishment.setPosX(12);
        //using getPosX() to check if the x is set to what we expected
        assertEquals(10,bonus.getPosX());
        assertEquals(20,enemy.getPosX());
        assertEquals(-5,reward.getPosX());
        assertEquals(15,player.getPosX());
        assertEquals(12,punishment.getPosX());
    }

    @Test
    void setPosY() {
        //running the function to set the X
        bonus.setPosY(10);
        enemy.setPosY(20);
        reward.setPosY(-5);
        player.setPosY(15);
        punishment.setPosY(12);
        //using getPosX() to check if the x is set to what we expected
        assertEquals(10,bonus.getPosY());
        assertEquals(20,enemy.getPosY());
        assertEquals(-5,reward.getPosY());
        assertEquals(15,player.getPosY());
        assertEquals(12,punishment.getPosY());
    }

    @Test
    void validMove() {
        //assert the map(2,2) is not a valid move
        assertEquals(false,bonus.validMove(2, 2));
        //assert the map(0,0) is a valid move
        assertEquals(true,enemy.validMove(0, 0));
        //assert the map(5,5) is a valid move
        assertEquals(true,reward.validMove(5, 5));
        //assert the map(1,0) is not a valid move
        assertEquals(true,player.validMove(1, 0));
        //assert the map(0,0) is a valid move
        assertEquals(true,enemy.validMove(0, 0));
        //assert the map(11,12) is a valid move
        assertEquals(true,punishment.validMove(11, 12));
    }

    @Test
    void randPosition() {
        //call randPosition() of all the classes
        bonus.randPosition();
        enemy.randPosition();
        reward.randPosition();
        player.randPosition();
        punishment.randPosition();
        //assert the posX and posY should smaller than its maximum
        assertTrue(bonus.getPosX()<Environment.MAX_GRID);
        assertTrue(reward.getPosY()<Environment.MAX_GRID);
        //assert the posX and posY should greater than 0
        assertTrue(enemy.getPosX()>0);
        assertTrue(player.getPosX()>0);
        //assert the position of the object should be valid
        assertTrue(punishment.validMove(punishment.getPosY(),punishment.getPosX()));
    }
}