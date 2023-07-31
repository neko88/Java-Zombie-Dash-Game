package com.company;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class EnvironmentTest {
    Game game = new Game(700, 700);
    Environment environment=new Environment();

    @Test
    void checkValidGrid() {
        //assert return true when location inbound
        assertTrue(environment.checkValidGrid(3, 3));
        //assert return false when location outbound
        assertFalse(environment.checkValidGrid(-3, 3));
        assertFalse(environment.checkValidGrid(3, environment.getNumX()+3));
    }

    @Test
    void setStartEnd() {
        //if the location is out of bound, then it will print error
        final ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
        final ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent1));
        environment.setStartEnd(-3, 3, 3,3);
        System.setOut(new PrintStream(outContent2));
        System.out.println("\n Error: Not a valid grid in the map.");
        assertEquals(outContent2.toString(), outContent1.toString());
        //if the location is in bound, then we need to check whether the start point and end point is as our expect
        environment.setStartEnd(3, 3, 3,3);
        assertEquals(3,environment.startX);
        assertEquals(3,environment.startY);
        assertEquals(3,environment.endX);
        assertEquals(3,environment.endY);
    }

    @Test
    void checkValidMove() {
        //assert return true when the location that player move to is available
        assertTrue(environment.checkValidMove (30, 18));
        assertTrue(environment.checkValidMove (48, 32));
        //assert return false when the location that player move to is unavailable
        assertFalse(environment.checkValidMove (24, 24));
        assertFalse(environment.checkValidMove (12, 12));
        assertFalse(environment.checkValidMove (6, 6));
        assertFalse(environment.checkValidMove (18, 54));
    }

    @Test
    void getGridValue() {
        //check the location and its corresponding Grid value
        assertEquals(66,environment.getGridValue(6,6));
        assertEquals(58,environment.getGridValue(4,6));
        assertEquals(65,environment.getGridValue(2,8));
        assertEquals(59,environment.getGridValue(4,7));
        assertEquals(14,environment.getGridValue(6,5));
    }
}