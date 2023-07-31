// Punishment class
package com.company;

import static com.company.SpriteManager.environmentTiles;

/**
 * @author      Natalie Lo, Rebekah Wong, HuanYu Zhou, Jacky Li
 * @version     1.0
 * @since       1.0
 */

public class Punishment extends GameObject {
    public Punishment() {
        this.sprite = environmentTiles.getSprite(96);
        randPosition();
    }
}

