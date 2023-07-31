package com.company;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author      Natalie Lo
 * @version     1.0
 * @since       1.0
 */

public class TileList {

    private ArrayList<Sprite> objects;        // store character sprites

    public TileList(){
        objects = new ArrayList<>();
    }

    // the following methods are getters for a sprite list or a specific sprite.
    public ArrayList<Sprite> getList(){
        return this.objects;
    }
    public BufferedImage getSprite(int index){
        return this.objects.get(index).getSprite();
    }



}
