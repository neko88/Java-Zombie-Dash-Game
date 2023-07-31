package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author      Rebekah Wong, HuanYu Zhou
 * @version     1.0
 * @since       1.0
 */

public class Scoreboard extends JFrame {
    ScoreWindow sw;
    int x,y,w,h;
    ArrayList<Score> scoreboard;
    public Scoreboard (){
        scoreboard = new ArrayList<>();
        setVisible(false);
        x=100;y=100;w=400;h=600;
    }
    public void addScore(Player player){
        Score newScore = new Score(player);
        scoreboard.add(newScore);
    }

    class Score{
        String name;
        int score, bonus, reward;
        public Score(Player player){
            this.name = player.name;
            this.score = player.getScore();
            this.bonus = player.getBonus();
            this.reward = player.getReward();
        }
    }

    public void paintScoreboard(){
        sw = new ScoreWindow(x,y);
        setBounds(x, y, w, h);
        setLocationRelativeTo(null);
        setVisible(true);
        sw.setVisible(true);
        add(sw);
        sw.repaint();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class ScoreWindow extends JPanel{
        int X;
        int Y;
        JPanel scoreScreen;
        Color color=new Color(200,255,255);
        public ScoreWindow(int x, int y) {
            X = x; Y = y;
            scoreScreen = new JPanel(new BorderLayout());
            scoreScreen.setBackground(color);
            Game.isStart = false;
            repaint();
        }
        @Override
        public void paintComponent (Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            Sprite title = new Sprite("/SCOREBOARD.png");
            g2d.drawImage(title.getSprite(), X/2, 0, null);

            int Y = 200;
            int space = 20;
            for (int i = 0; i < scoreboard.size(); i++) {
                String rank = "RANK: " + i+1;
                String player ="Player: " + scoreboard.get(i).name;
                String score ="Score: " + scoreboard.get(i).score;
                String reward ="Reward: " + scoreboard.get(i).reward;
                String bonus ="Bonus: " + scoreboard.get(i).bonus;

                g.drawString(rank, X+20, Y); Y += space;
                g.drawString(player, X+20, Y); Y += space;
                g.drawString(score, X+20, Y); Y += space;
                g.drawString(reward, X+20, Y); Y += space;
                g.drawString(bonus, X+20, Y); Y += space;
                Y+=space;
                g.dispose();
            }
        }
    }


}
