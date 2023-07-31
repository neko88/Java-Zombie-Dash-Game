// Bonus reward class
package com.company;

import static com.company.SpriteManager.bonus;

/**
 * @author      Natalie Lo, Rebekah Wong, HuanYu Zhou, Jacky Li
 * @version     1.0
 * @since       1.0
 */

public class Bonus extends GameObject {
    public boolean isCollected = false;
    public Bonus(){
        this.sprite = bonus;
        randPosition();
        isCollected = false;
    }
}
