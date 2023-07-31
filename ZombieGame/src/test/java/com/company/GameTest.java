package com.company;

import com.company.Audio;
import com.company.Game;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;

    @BeforeEach
    void Setup() {
        game = new Game(700, 700);
    }

    /**
     * Test method for Game instantiation.
     */
    @Test
    void GameCreationTest() {
        assertFalse(game == null);
    }

    /**
     * Test if game start and screens are not null.
     */
    @Test
    void GameStartTest() {
        assertFalse(game.gInterface == null);
    }
}
