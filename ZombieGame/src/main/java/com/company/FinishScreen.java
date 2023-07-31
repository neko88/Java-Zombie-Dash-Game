package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static com.company.GamePlay.isLose;
import static com.company.GamePlay.isWin;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * @author      Rebekah Wong
 * @version     1.0
 * @since       1.0
 */

public class FinishScreen extends JFrame{
    WinScreen ws;
    GameOver ls;
    int x,y,w,h;
    public FinishScreen() {
        setVisible(false);
        x=100;y=100;w=420;h=400;
    }

    public void paintWin(){
        ws = new WinScreen(x,y);
        setBounds(x, y, w, h);
        setLocationRelativeTo(null);
        setVisible(true);
        ws.setVisible(true);
        add(ws);
        ws.repaint();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void paintLose(){
        ls = new GameOver(x,y);
        setBounds(x, y, w, h);
        setLocationRelativeTo(null);
        setVisible(true);
        ls.setVisible(true);
        add(ls);
        ls.repaint();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class WinScreen extends JPanel {
    JPanel winScreen;
    int X;
    int Y;
    JButton playAgainButton;
    Color color=new Color(200,255,255);

    public WinScreen(int x, int y) {
        X = x;
        Y = y;
        winScreen = new JPanel(new BorderLayout());
        winScreen.setBackground(color);
        GamePlay.scoreboard.addScore(GamePlay.player);
        GamePlay.scoreboard.paintScoreboard();
        Game.isStart = false;
        repaint();
    }
    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Sprite title = new Sprite("/WIN.png");
        g2d.drawImage(title.getSprite(), X / 2, Y / 2, null);
    }

}
class GameOver extends JPanel {
    JPanel loseScreen;
    int X;
    int Y;
    Color color=new Color(66,67,65);

    public GameOver(int x, int y) {
        X = x;
        Y = y;
        loseScreen = new JPanel(new BorderLayout());
        loseScreen.setBackground(color);
        GamePlay.scoreboard.addScore(GamePlay.player);
        GamePlay.scoreboard.paintScoreboard();
        Game.isStart = false;
        repaint();
    }
    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Sprite title = new Sprite("/GAMEOVER.png");
        g2d.drawImage(title.getSprite(), X / 2, Y / 2, null);
    }

}






