package com.anumalm.game;

import com.anumalm.evoengine.*;

/**
 * Monster is a basic enemy in Epic Space Game.
 * 
 * Extends GameObject to get all it's handy methods.
 * 
 * @author      Anu Malm anu.malm@cs.tamk.fi
 * @version     2018.1218
 * @since       3.0
 */
public class Monster extends GameObject {
    private int health;
    private boolean baseWin = false;

    /**
     * Constructor for Monster.
     * 
     * @param sprite            Sprite of the Monster
     * @param x                 x-position of the Monster
     * @param y                 y-position of the Monster
     * @param width             width of the Monster
     * @param height            height of the Monster
     * @param scale             scale of the Monster
     * @param enabled           is the Monster enabled
     * @param name              Monster's name tag ("monster")
     * @param health            Monster's health
     */
    public Monster(Sprite sprite, float x, float y, int width, int height, int scale, boolean enabled, String name, int health) {
        super(sprite, x, y, width, height, scale, enabled, name);
        Collider c = new Collider(x, y, width, height);
        super.addCollider(c);

        setHealth(health);
    }

    /**
     * Monster's update-method.
     * 
     * Used to update Monster's position and Collider position.
     * Also checks whether Monster can be disabled (= took too much damage or moved outside the screen).
     */
    public void update() {

        setyPos(getyPos() + 3);
        if(getyPos() >= 760 || getHealth() <= 0) {
            setEnabled(false);
            if(getyPos() >= 760) {
                baseWin = true;
            }
        }
        //update collision position
        getCollider().setxPos(getxPos());
        getCollider().setyPos(getyPos());

    }

    /**
     * Set's the health of the Monster.
     * 
     * Must be above 0.
     * 
     * @param h             health value
     */
    public void setHealth(int h) {
        if (h >= 0) {
            this.health = h;
        } else {
            throw new IllegalArgumentException("MOnster health must be 0 or higher!");
        }

    }

    /**
     * Checks whether the monster "won" and got to player's base
     * 
     * @return              did Monster move all the way across the game window to the bottom
     */
    public boolean gotToBase() {
        return this.baseWin;
    }

    /**
     * Returns the health of the Monster.
     * 
     * @return              Monster's current health
     */
    public int getHealth() {
        return this.health;
    }
}