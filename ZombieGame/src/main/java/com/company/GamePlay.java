package com.company;


import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.lang.*;
import static com.company.SpriteManager.*;
import static com.company.GameScreen.*;

/**
 * @author      Natalie Lo
 * @version     1.0
 * @since       1.0
 */

public class GamePlay extends JPanel {
    static Scoreboard scoreboard;
    static Player player;
    Audio rewardSound = new Audio("resources/zapsplat_reward.wav");
    Audio winSound = new Audio("resources/zapsplat_reward.wav");
    Audio loseSound = new Audio("resources/zapsplat_punishment.wav");
    FinishScreen finish;
    protected ArrayList<GameObject> randReward = new ArrayList<>();
    protected ArrayList<GameObject> randPunishment = new ArrayList<>();
    protected ArrayList<GameObject> randBonus = new ArrayList<>();
    protected ArrayList<GameObject> enemies = new ArrayList<>();
    static boolean isWin = false;
    static boolean isLose = false;
    static boolean collectedAllRewards = false;

    double scaleFPS = 5.25;
    int posDistance = 2;
    int negDistance = -2;

    public GamePlay(int playerSpeed, int numRewards, int numBonus, int numEnemies, int numPunishment) {
        scoreboard = new Scoreboard();
        finish = new FinishScreen();
        player = new Player();

        try {
            setRandObject(numRewards ,randReward, Reward.class);
            setRandObject(numBonus, randBonus, Bonus.class);
            setRandObject(numPunishment,randPunishment, Punishment.class);
            setRandObject(numEnemies,enemies,Enemy.class);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        player.speed = playerSpeed;
    }


    /**
     *  ############# Game Play Methods ###############
     *
     */

    /**
     * Method to check if player collected all rewards.
     * @return if all rewards collected
     */
    public boolean isAllRewardsCollected(){
        if (randReward.size() == player.getReward()){
            collectedAllRewards = true;
            return true;
        }
        return false;
    }

    /**
     * Method to set & initialize a GameObjects within its ArrayList.
     * @param n number of objects to instantiate
     * @param objectList the ArrayList holding the GameObject
     * @param c the child class of the GameObject
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void setRandObject(int n, ArrayList objectList, Class c) throws InstantiationException, IllegalAccessException {
        for (int i = 0; i < n; i++) {
            try {
                objectList.add(c.getDeclaredConstructor().newInstance());
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Helper method to loop through all enemies in the ArrayList
     * setEnemyFollowPlayer changes movement of the enemy by reading
     * where the player's current position is and redirects itself ingame
     * to follow and attack the player.
     * @param pPosX - players current X position
     * @param pPosY - players current Y position
     */
    public void setEnemyMovement(int pPosX,int pPosY){
        for (int i = 0; i < enemies.size(); i++){
            setEnemyFollowPlayer(enemies.get(i), pPosX, pPosY);
        }
    }
    public void setEnemyFollowPlayer(GameObject enemy, int playerPosX, int playerPosY) {
        if(Game.isStart==true&& !isWin) {
            int goalX = (int) (playerPosX * scaleFPS); // Max is 640
            int goalY = (int) (playerPosY * scaleFPS); // Max is 640
            if (enemy.getPosX() > goalX) {
                enemy.setPosX(enemy.getPosX() - 1);
            }
            if (enemy.getPosX() < goalX) {
                enemy.setPosX(enemy.getPosX() + 1);
            }
            if (enemy.getPosY() > goalY) {
                enemy.setPosY(enemy.getPosY() - 1);
            }
            if (enemy.getPosY() < goalY) {
                enemy.setPosY(enemy.getPosY() + 1);
            }
        }
    }

    /**
     * Method working with 'checkObject' to implement different results
     * depending on the GameObject (child class) which the player is nearby/hits.
     * @param o: the GameObject
     */
    public void resultForObject(GameObject o) {
        Class c = o.getClass();
        if (c == Reward.class) {
            player.winScore();
            player.winReward();
            rewardSound.playAudio();
        } else if (c == Bonus.class) {
            player.winScore();
            player.winBonus();
            rewardSound.playAudio();
        } else if (c == Enemy.class) {
            if (player.getScore() < 0) {
                isLose = true;
                loseSound.playAudio();
                finish.paintLose();
            }else{
                loseSound.playAudio();
                player.loseScore();
            }
        } else if (c == Punishment.class) {
            isLose = true;
            loseSound.playAudio();
            finish.paintLose();
        }
    }

    /**
     * Method used in Update() for detecting if a player hits a certain GameObject.
     * The result is implemented by 'resultForObject' depending on what type of
     * subclass the GameObject is. Example: Reward will add scores to the Player.
     * @param o the GameObject ArrayList
     * @return true if the player hits the object, false otherwise.
     */
    public boolean checkObject (ArrayList <GameObject> o) {
        if (Game.isStart)
            for (int i = 0; i < o.size(); i++) {
                if ((player.getLocation() == o.get(i).jPos.getLocation()) ||
                        (((player.getLocation().x - o.get(i).jPos.getLocation().x) <= posDistance) && ((player.getLocation().y - o.get(i).jPos.getLocation().y) <= posDistance)) &&
                                (((player.getLocation().x - o.get(i).jPos.getLocation().x) >= negDistance) && ((player.getLocation().y - o.get(i).jPos.getLocation().y) >= negDistance))) {
                    o.get(i).hitObject = true;
                    resultForObject(o.get(i));
                    return true;
                } else {
                    o.get(i).hitObject = false;
                }
            }
        return false;
    }

    /**
     * Used in Update() to check player's current position if it is at the
     * finish point (house). It checks if all rewards are collected, if true
     * then the player wins.
     * @param x: player's x position
     * @param y: player's y position
     * @return: true if player collected all rewards, player wins game
     */
    public boolean checkFinish (int x, int y){
        if (Game.isStart) {
            y /= 6; x /= 6;
            if (x == environment.endX && y == environment.endY) {
                if (collectedAllRewards == true) {
                    if (Player.getScore() > 0) {
                        isWin = true;
                        finish.paintWin();
                        Game.isStart = false;
                        winSound.playAudio();
                        return true;
                    }
                    return true;
                }else{
                    if (Player.getScore() <= 0){
                        isLose = true;
                        finish.paintLose();
                        Game.isStart = false;
                    }
                }
            }
            isWin = false;
            return false;
        }
        return false;
    }

    /**
     *  ### All Painting Methods: ####
     *
     */

    public void paintPlayer (Graphics g, int x , int y){
        g.drawImage(player.sprite, (int) (x*scaleFPS), (int)(y*scaleFPS) , null);  // draw sprite at row,col of the jFrame
        player.setLocation(x,y);
        if (isWin){g.drawImage(star, (int)(x*scaleFPS), (int)(y*scaleFPS-25), null);}
    }

    public void paintEnemy (Graphics g){
         for (int i = 0; i < enemies.size(); i++){
             int x = enemies.get(i).getPosX();
             int y = enemies.get(i).getPosY();
            g.drawImage(enemies.get(i).sprite, x, y , null);  // draw sprite at row,col of the jFrame
            enemies.get(i).jPos.setLocation((int)(x/scaleFPS),(int)(y/scaleFPS));
            if (enemies.get(i).hitObject){g.drawImage(damage, (int)(player.getX()*scaleFPS), (int)(player.getY()*scaleFPS-25), null);}
        }
     }
    public void paintReward(Graphics g){
        paintObject(g, randReward);
    }
    public void paintBonus(Graphics g) {
        paintObject(g, randBonus);
    }
    public void paintPunishment(Graphics g){
        for (int i = 0; i < randPunishment.size(); i++ ) {
            g.drawImage(randPunishment.get(i).sprite , randPunishment.get(i).getPosX()*32 , randPunishment.get(i).getPosY()*32 , null);// draw sprite at row,col of the jFrame
            randPunishment.get(i).jPos.setLocation ((int) (randPunishment.get(i).getPosX()*32/scaleFPS),((int) (randPunishment.get(i).getPosY()*32/scaleFPS)));
            if (randPunishment.get(i).hitObject){g.drawImage(damage, (int)(player.getX()*scaleFPS), (int)(player.getY()*scaleFPS-25), null);}
        }
    }
    public void paintObject(Graphics g, ArrayList<GameObject> randObject) {
        for (int i = 0; i < randObject.size(); i++) {
            if (randObject.get(i).isCollected == false) {
                g.drawImage(randObject.get(i).sprite, randObject.get(i).getPosX() * 32, randObject.get(i).getPosY() * 32, null);// draw sprite at row,col of the jFrame
                randObject.get(i).jPos.setLocation((int) (randObject.get(i).getPosX() * 32 / scaleFPS), ((int) (randObject.get(i).getPosY() * 32 / scaleFPS)));
            }
            if (randObject.get(i).hitObject){
                g.drawImage(heart, (int)(player.getX()*scaleFPS), (int)(player.getY()*scaleFPS-25), null);
                randObject.get(i).isCollected = true;
                randObject.remove(i);}
        }
    }

}
