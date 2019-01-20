package com.anumalm.game;

import com.anumalm.evoengine.*;

/**
 * Player class extends GameObject to inherit all it's useful methods.
 * 
 * @author      Anu Malm anu.malm@cs.tamk.fi
 * @version     2018.1218
 * @since       3.0
 */
public class Player extends GameObject {

    /**
     * Player's constructor.
     * 
     * @param sprite            Sprite of the Player
     * @param x                 x-position of the Player
     * @param y                 y-position of the Player
     * @param width             width of the Player
     * @param height            height of the Player
     * @param scale             scale of the Player
     * @param enabled           is the Player enabled
     * @param name              Player's name tag ("player")
     */
    public Player(Sprite sprite, float x, float y, int width, int height, int scale, boolean enabled, String name) {
        super(sprite, x, y, width, height, scale, enabled, name);
    }
}