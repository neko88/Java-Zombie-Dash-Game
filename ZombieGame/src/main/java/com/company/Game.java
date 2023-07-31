package com.company;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author      Natalie Lo, Rebekah Wong
 * @version     1.0
 * @since       1.0
 */

public class Game {
    Interface gInterface;
    static boolean isStart = false;
    static long starttime=0;
    Audio bgMusic;
    public static SpriteManager gm;

    public Game(int width, int height) {
        gm = new SpriteManager();
        gInterface = new Interface(width, height);
        bgMusic = new Audio("resources/bgm.wav");
        bgMusic.playAudio();
    }

}






