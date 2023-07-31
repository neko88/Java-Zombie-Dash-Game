package com.company;

import com.company.Audio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AudioTest {
    /**
     * Test method for music at the start of the game.
     */
    @Test
    void SoundFileTest() {
        Audio audio = new Audio("/resources/bgm.wav");
        assertTrue(audio.audioPath == "/resources/bgm.wav");
    }

    /**
     * Test if the correct path has been saved in the audioPath variable.
     */
    @Test
    void AudioPathMatchingTest() {
        Audio audio = new Audio("/resources/bgm.wav");
        assertEquals("/resources/bgm.wav", audio.audioPath);
    }
}