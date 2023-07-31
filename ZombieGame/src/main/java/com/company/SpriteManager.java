package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author      Natalie Lo
 * @version     1.0
 * @since       1.0
 *
 * Sprites obtained from opengameart.org
 */

public class SpriteManager {
    private ArrayList<BufferedImage> canvas;             // List to store multiple sprite canvases
    public static TileList charTiles;
    public static TileList enemyTiles;
    public static  TileList pathTiles;
    public static TileList rewardTiles;
    public static BufferedImage bonus;
    public static TileList punishments;
    public static TileList environmentTiles;
    public static BufferedImage damage;
    public static BufferedImage heart;
    public static BufferedImage star;

    public SpriteManager() {
        canvas = new ArrayList<>(); // holds all sprite images used in the game
        // instantiate tile lists
        charTiles = instantiateTiles (canvas,"/Male 01-1.png", 1, 1, 32, 32);
        enemyTiles = instantiateTiles (canvas,"/ENEMY1.png", 1, 1, 32, 32);
        addToTileList (canvas,enemyTiles,"/ENEMY2.png", 1, 1, 32, 32);;
        pathTiles = instantiateTiles (canvas,"/PATHS.png", 8, 2, 32, 32);
        rewardTiles = instantiateTiles (canvas,"/FOOD.png", 8, 8, 32, 32);
        bonus =  (new Sprite("/STAR.png").getSprite());
        punishments = instantiateTiles (canvas,"/PUNISHMENT.png", 1, 1, 32, 32);
        environmentTiles = instantiateTiles (canvas,"/ENVIRONMENT.png", 8, 13, 32, 32);
        damage = (new Sprite("/DAMAGE.png").getSprite());
        heart = (new Sprite("/HEART.png").getSprite());
        star = (new Sprite("/STAR.png").getSprite());
    }

    public TileList instantiateTiles (ArrayList<BufferedImage> canvas, String imgPath, int numColsCanvas, int numRowsCanvas, int spriteW, int spriteH) {
        int lastIndex = canvas.size();
        TileList tl = new TileList();
        addImgToCanvas(canvas,imgPath);
        saveCanvasToTile(tl.getList(), canvas.get(lastIndex), numColsCanvas, numRowsCanvas, spriteW, spriteH );
        return tl;
    }

    public void addToTileList (ArrayList<BufferedImage> canvas, TileList tl, String imgPath, int numColsCanvas, int numRowsCanvas, int spriteW, int spriteH){
        if (tl.getList().size() == 0){
            tl = instantiateTiles(canvas, imgPath, numColsCanvas, numRowsCanvas, spriteW, spriteH);
        }
        int lastIndex = canvas.size();
        addImgToCanvas(canvas, imgPath);
        saveCanvasToTile(tl.getList(), canvas.get(lastIndex), numColsCanvas, numRowsCanvas, spriteW, spriteH);
    }

    // store image input into a canvas
    public void addImgToCanvas(ArrayList<BufferedImage> canvas, String imgPath) {
        InputStream is = getClass().getResourceAsStream(imgPath);
        try {
            canvas.add(ImageIO.read(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load a canvas into a tile array at a specific grid
    public void saveCanvasToTile(ArrayList<Sprite> tileList, BufferedImage canvas, int numColCanvas, int numRowCanvas, int w, int h){
        for (int row = 0; row < numRowCanvas; row++){
            for (int col = 0; col < numColCanvas; col++){
                tileList.add(new Sprite (canvas, col, row, w, h));
            }
        }
        // System.out.println("\n Canvas successfully converted to sprites and saved to tile");
    }







}
