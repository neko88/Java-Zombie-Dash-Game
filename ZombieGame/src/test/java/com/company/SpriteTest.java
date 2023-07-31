package com.company;

import com.company.Sprite;
import org.junit.jupiter.api.*;
import java.awt.image.RasterFormatException;
import static org.junit.jupiter.api.Assertions.*;

class SpriteTest {

    Sprite sprite;

    @BeforeEach
    void setUp() {
        sprite = new Sprite("/STAR.png");
    }

    /**
     * Test if new sprite returns null
     */
    @Test
    void getSprite() {
        assertFalse(sprite == null);
    }

    /**
     * Test for throw error in event of a negative image spec passed through the parameter
     */
    @Test
    void negativeImageParameterTest() {
        assertThrows(RasterFormatException.class, () -> new Sprite("/STAR.png", -2, -4, 2, 2));
        assertThrows(RasterFormatException.class, () -> new Sprite("/STAR.png", 2, 4, -2, -2));
    }
}
