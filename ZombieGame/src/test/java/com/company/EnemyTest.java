package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    Game game = new Game(700, 700);
    Enemy enemy = new Enemy();
    //we already test all the other method of enemy in GameObject Test,
    //So the only one function we need to test here is randPosition
    @Test
    void randPosition() {
        enemy.randPosition();
        //assert the posX and posY should smaller than its maximum:600
        assertTrue(enemy.getPosX()<600);
        assertTrue(enemy.getPosY()<600);
        //assert the posX and posY should greater than 0
        assertTrue(enemy.getPosX()>0);
        assertTrue(enemy.getPosX()>0);
    }
}