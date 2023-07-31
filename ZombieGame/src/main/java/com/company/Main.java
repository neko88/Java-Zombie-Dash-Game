package com.company;

public class Main {

    public static void main(String[] args) {
        Game game = new Game(700, 700);
        game.gInterface.gameScreen.initializeGame(3,20,3,2,3);
        game.gInterface.setVisible(true);
        game.gInterface.repaint();
        game.gInterface.gameScreen.gamePlay.finish.repaint();
    }
}
