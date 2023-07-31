package com.company;

import javax.swing.*;
import java.awt.*;
import static com.company.Game.gm;
import static com.company.GamePlay.player;

/**
 * @author      Natalie Lo
 * @version     1.0
 * @since       1.0
 */

public class GameScreen extends JPanel implements Runnable {
    static Environment environment;
    KeyHandler keyH = new KeyHandler();
    static Thread gameThread;
    GamePlay gamePlay;
    int FPS = 60;

    public GameScreen() {
        environment = new Environment();
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void initializeGame(int playerSpeed, int numRewards,
                               int numBonus, int numEnemies, int numPunishment){
        gamePlay = new GamePlay(playerSpeed,numRewards,numBonus,numEnemies,numPunishment);
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){
            if (gamePlay!=null)
                update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                double aTime = remainingTime;
                remainingTime /= 1000000;        // note zeros for coordinates
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                nextDrawTime += drawInterval;
                gameThread.sleep((long) remainingTime);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        if (Game.isStart) {
            if (keyH.upPressed == true) {
                if (environment.checkValidMove((player.getPosX()), player.getPosY() - 1)) {
                    player.setPosY(player.getPosY() - 1);
                }
            } else if (keyH.downPressed == true) {
                if (environment.checkValidMove(player.getPosX(), player.getPosY() + 1)) {
                    player.setPosY(player.getPosY() + 1);
                }
            } else if (keyH.leftPressed == true) {
                if (environment.checkValidMove(player.getPosX() - 1, player.getPosY())) {
                    player.setPosX(player.getPosX() - 1);
                }
            } else if (keyH.rightPressed == true) {
                if (environment.checkValidMove(player.getPosX() + 1, player.getPosY())) {
                    player.setPosX(player.getPosX() + 1);
                }
            }
            gamePlay.setEnemyMovement(player.getX(), player.getY());
            gamePlay.checkObject(gamePlay.randReward);
            gamePlay.checkObject(gamePlay.randBonus);
            gamePlay.checkObject(gamePlay.randPunishment);
            gamePlay.checkObject(gamePlay.enemies);
            gamePlay.isAllRewardsCollected();
            gamePlay.checkFinish(player.getX(), player.getY());
        }

    }

    // paint image to the JFrame screen
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintBackground(g, environment.getNumX(), environment.getNumY());
        paintTiles(g, gm.environmentTiles, environment.getNumX(), environment.getNumY());
        gamePlay.paintPlayer(g , player.getPosX(), player.getPosY());
        gamePlay.paintEnemy(g);
        gamePlay.paintReward(g);
        gamePlay.paintBonus(g);
        gamePlay.paintPunishment(g);
        paintTimer(g);
        paintGameStats(g);
        g.dispose();
    }
    public void paintTimer(Graphics g){
        long endTime = System.currentTimeMillis();
        g.setFont(new Font("System", Font.BOLD, 14));
        int timer = (int) ((endTime-Game.starttime)/1000);
        int minutes = timer/60;
        int seconds = timer%60;
        String time = String.format("%02d:%02d", minutes, seconds);
        if(Game.isStart==false) {
            String str = "Time: 00:00";
            g.drawString(str, 550, 50);
        } else if(Game.isStart==true) {
            String str = "Time: " + time;
            g.drawString(str, 550, 50);
        }
    }
    public void paintGameStats(Graphics g){
        String score ="Score: " + player.getScore();
        String reward ="Rewards: " + player.getReward();
        String bonus ="Bonus: " + player.getBonus();
        g.drawString(score, 550, 75);
        g.drawString(reward, 550, 100);
        g.drawString(bonus , 550, 125);
    }

    public void paintBackground(Graphics g, int tileRow, int tileCol){
        Sprite grass = new Sprite ("/GRASS.png");
        //int index = 0;
        for (int x = 0; x < tileRow; x++){
            for (int y = 0; y < tileCol; y++) {
                g.drawImage(grass.getSprite(), y * 32, x * 32, null);  // draw sprite at row,col of the jFrame
                //index++;
            }
        }
    }

    // method to load tiles from a tile list and loops the tile image
    public void paintTiles(Graphics g, TileList tl , int tileRow, int tileCol){
        for (int x = 0; x < tileRow; x++){
            for (int y = 0; y < tileCol; y++) {
                g.drawImage(tl.getSprite(environment.map[x][y]), y * 32, x * 32, null);  // draw sprite at row,col of the jFrame
            }
        }

    }



}
