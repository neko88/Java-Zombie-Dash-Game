package com.company;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Audio {
    String audioPath;
    public Audio(String soundFile) {
        audioPath = soundFile;
    }
    public void playAudio(String soundFile){
        audioPath = soundFile;
        // Reference: https://www.youtube.com/watch?v=qPVkRtuf9CQ
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(audioPath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }
        catch (Exception e) {
        }
    }
    public void playAudio(){
        // Reference: https://www.youtube.com/watch?v=qPVkRtuf9CQ
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(audioPath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }
        catch (Exception e) {
        }
    }
}
