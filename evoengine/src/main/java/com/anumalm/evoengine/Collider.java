package com.anumalm.evoengine;

import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.event.EventHandler;

/**
 * Collider is used when checking collisions between Colliders.
 * 
 * Colliders are rectangular and can't be rotated.
 * Mouse clicks etc. go through Colliders too.
 * 
 * @author      Anu Malm anu.malm@cs.tamk.fi
 * @version     2018.1204
 * @since       2.0
 */
public class Collider extends Rectangle {
    private boolean result = false;

    /**
     * Constructor for Collider.
     * 
     * @param xPos              Collider's x-position
     * @param yPos              Collider's y-position
     * @param width             Collider's width
     * @param height            Collider's height
     */
    public Collider(float xPos, float yPos, double width, double height) {
        super(xPos, yPos, width, height);
    }

    /**
     * Checks if this Collider collides with the given Collider.
     * 
     * @param c                 Collider that we're checking the collision with
     * @return                  if the Colliders collided
     */
    public boolean collidesWith(Collider c) {
        return this.getBoundsInParent().intersects(c.getBoundsInParent());
    }
    
    /**
     * Return the current x-position of the collider.
     * 
     * This method is made so that the getter aligns with EvoEngine's naming conventions. 
     * 
     * @return          collider's x-position
     */
    public double getxPos() {
        return this.getX();
    }

    /**
     * Set a x-position to the collider.
     * 
     * This method is made so that the setter aligns with EvoEngine's naming conventions. 
     * 
     * @param xPos      new desired x-position
     */
    public void setxPos(float xPos) {
        this.setX(xPos);
    }

    /**
     * Returns the current y-position of collider.
     * 
     * This method is made so that the getter aligns with EvoEngine's naming conventions. 
     * 
     * @return          collider's y-position
     */
    public double getyPos() {
        return this.getY();
    }

    /**
     * Set a y-position to the collider.
     * 
     * This method is made so that the setter aligns with EvoEngine's naming conventions. 
     * 
     * @param yPos      new desired y-position.
     */
    public void setyPos(float yPos) {
        this.setY(yPos);
    }

}