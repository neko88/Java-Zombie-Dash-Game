package com.company;

import com.company.SpriteManager;
import com.company.TileList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.RasterFormatException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SpriteManagerTest {

    static SpriteManager sm;
    static ArrayList<BufferedImage> canvas;
    static TileList tl;

    @BeforeAll
    static void Setup(){
        sm = new SpriteManager();
        canvas = new ArrayList();
        tl = new TileList();
    }

    /**
     * Test that a new canvas has been added to the canvas list with correct parameters.
     */
    @Test
    void instantiateTilesTest(){
        sm.instantiateTiles(canvas, "/ENVIRONMENT.png", 8, 13, 32, 32);
        assertTrue(canvas.get(0) != null);
    }

    /**
     * Test for error thrown when passed a value larger than the size of the canvas.
     */
    @Test
    void instantiateTilesTestBeyondSize(){
        assertThrows(RasterFormatException.class, ()-> sm.instantiateTiles(canvas, "/ENVIRONMENT.png", 10, 13, 32, 32));
    }

    /**
     * Test if adding to canvas (holds all images used in game) is not null when
     * first image is added.
     */
    @Test
    void testAddImgToCanvas(){
        sm.addImgToCanvas(canvas, "/ENVIRONMENT.png");
        assertTrue(canvas.get(0) != null);
    }

    /**
     * Test to another canvas to the canvas list
     * If null, then it was not added.
     */
    @Test
    void addToTileListTest(){
        sm.addToTileList(canvas, tl, "/ENVIRONMENT.png", 8,13,32,32);
        assertTrue(canvas.get(1) != null);
    }

    /**
     * Test for error thrown when passed a value larger than the size of the canvas.
     */
    @Test
    void addToTileListTestBeyondBoundary(){
        assertThrows(RasterFormatException.class, ()-> sm.addToTileList(canvas,tl, "/ENVIRONMENT.png", 10, 13, 32, 32));
    }


}