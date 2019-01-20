package com.anumalm.game;

import com.anumalm.evoengine.*;

/**
 * Bullet-class extends GameObject to inherit all it's useful methods.
 * 
 * @author      Anu Malm anu.malm@cs.tamk.fi
 * @version     2018.1218
 * @since       3.0
 */
public class Bullet extends GameObject {
    private int damage;
    private int speed = 5;

    /**
     * Constructor for Bullet.
     * 
     * @param sprite            Sprite of the Bullet
     * @param x                 x-position of the Bullet
     * @param y                 y-position of the Bullet
     * @param width             width of the Bullet
     * @param height            height of the Bullet
     * @param scale             scale of the Bullet
     * @param enabled           is the Bullet enabled
     * @param name              Bullet's name tag ("bullet")
     * @param damage            how much damage the Bullet makes
     */
    public Bullet(Sprite sprite, float x, float y, int width, int height, int scale, boolean enabled, String name, int damage) {
        super(sprite, x, y, width, height, scale, enabled, name);
        Collider c = new Collider(x, y, width, height);
        super.addCollider(c);
        setDamage(damage);

    }

    /**
     * Bullet's own update-method.
     * 
     * Used to move Bullet and it's Collider.
     * Also sets the Bullet inactive if it moves outside the screen.
     */
    public void update() {
        setyPos(getyPos() - speed);
        if(getyPos() <= -10) {
            setEnabled(false);
        }
        //update collision position
        getCollider().setxPos(getxPos());
        getCollider().setyPos(getyPos());
    }

    /**
     * Sets the amount of damage the Bullet makes.
     * 
     * @param d             amount of damage
     */
    public void setDamage(int d) {
        if(d >= 0) {
            this.damage = d;
        } else {
            throw new IllegalArgumentException("Bullet's damage must be >=0!");
        }
    }

    /**
     * Gets the amount of damage the Bullet makes.
     * 
     * @return              how much is the Bullet's damage
     */
    public int getDamage() {
        return this.damage;
    }
}