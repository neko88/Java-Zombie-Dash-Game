package com.company;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static com.company.GamePlay.*;
import static com.company.GameScreen.environment;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

/*
0 = unwalkable grid
1 = path / available grid
2 = start
3 = end
4 = wall
 */

/**
 * @author      Natalie Lo, Rebekah Wong, HuanYu Zhou, Jacky Li
 * @version     1.0
 * @since       1.0
 */

public class Environment extends JComponent{
    public static int[][] map;
    public static int MAX_GRID = 19;
     int numX;
     int numY;

    public int startX;
    public int startY;
    public int endX;
    public int endY;

    public Environment() {
        environment.map = new int[][]{
                { 9,  1,  1,  1,  1, 10, 16, 17, 18, 65,  0, 65,  0,  0,  0, 38, 64,  0, 39,  0},
                { 2, 16, 20, 20, 18,  2, 24, 25, 26,  5,  0, 64,  9,  1,  1,  1,  1,  1, 10,  0},
                { 2, 44,  0,  0, 45,  2, 46, 65, 65,  2,  0,  0,  2,  0,  0,  58, 59, 60,  2,  0},
                { 2, 44,  0,  0, 45,  7,  1,  1,  1,  13, 1, 15, 13, 10,  0,  66, 55, 68, 2,  0},
                { 2, 24, 35, 25, 26,  2, 58, 59, 59, 59, 60,  2, 46, 11,  10, 74, 59, 76,  2,  0},
                {11, 10,  2,  0,  0,  2, 66,  9,  1,  1,  1, 14,  0,  0,  7,  1,  1,  1, 12,  0},
                { 0,  2,  7,  1,  1, 14, 66,  2, 65, 65, 68,  2,  0,  0,  2,  0,  0,  0,  0,  0},
                {54,  2,  2,  0, 63,  2, 66,  2, 58, 75, 76,  2,  0,  0,  2, 32, 33, 33, 33, 34},
                {39,  2,  2, 63,  0,  7,  1, 14, 66,  0,  0, 11,  1, 10,  2, 40,  9,  1, 10, 42},
                {47, 11, 14,  0,  0,  2, 66,  6, 66,  0,  0,  0,  0,  2,  2, 40,  2,  0,  2, 42},
                {71,  0,  2,  0, 38,  2, 74, 75, 65,  0,  0,  0,  0,  7,  13, 57,13,  1, 12, 42},
                { 0,  0,  7,  1,  1,  8,  1, 1, 15,  1,  1,  1, 15, 12,  0, 48, 49, 49, 49,  50},
                {71,  0,  2,  0,  0, 55,  0,  0,  2,  0, 79,  0,  2,  0,  0,  0,  0,  0,  0,  0},
                { 0,  0,  6,  0,  0,  0,  0,  0,  2,  0,  0,  0,  2,  0,  0,  0,  0,  0,  0,  0},
                { 24,25, 41, 25, 25, 25, 25, 25, 41, 25, 25, 25, 41, 25, 25, 25, 25, 25, 25, 26},
                { 0,  0,  2,  0, 65, 65, 65,  0,  2, 65, 65, 65,  2,  0,  0, 39,  0, 71,  0,  0},
                { 0,  0,  2, 55,  0,  0,  0,  0,  2,  0,  0,  0,  7,  1, 10,  0,  0, 38, 93, 94},
                { 9,  1, 13,  1,  1,  1,  1,  1,  1,  1,  1,  1, 14,  0,  2,  0, 63,  0,101,102},
                { 2, 83, 83, 83, 83, 83, 71, 55, 83, 83, 83, 83,  2,  0,  2, 83, 83, 83, 83,  2},
                { 11, 1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 13,  1, 13,  1,  1,  1,  1, 12},
        };
        numX = environment.map.length;
        numY = environment.map[0].length;
        setStartEnd(0,0,18,19);
    }
    public int getNumX(){
        return this.numX;
    }
    public int getNumY(){
        return this.numY;
    }

    public boolean checkValidGrid(int y, int x){
        if ((y >= 0) && (y < this.map.length) &&
                (x >= 0) && (x < this.map[y].length)){
            return true;
        }else{
            return false;
        }
    }
    public void setStartEnd(int startY, int startX, int endY, int endX) {
        if (checkValidGrid(startX,startY)) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }else{
            System.out.println("\n Error: Not a valid grid in the map.");
        }
    }

    public boolean checkValidMove (int x, int y){
       x/=6; y/=6;
        if (checkValidGrid(y,x) ){
            if ( (map[y][x] <= 15  || map[y][x] == 35 ||
                    map[y][x] == 41 || map[y][x] == 43 || map[y][x] == 56 || map[y][x] == 57)
                    && map[y][x] != 0 ) {
                return true;
            }
        }
        return false;
    }
    public int getGridValue (int y, int x){
        return map[y][x];
    }
}
