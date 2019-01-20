package com.anumalm.evoengine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.transform.Rotate;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * EvoBatch is used when we draw images etc. to the game screen.
 * 
 * Also used when drawing text.
 * 
 * @author      Anu Malm anu.malm@cs.tamk.fi
 * @version     2018.1218
 * @since       2.0
 */
public class EvoBatch {
    private GraphicsContext gc;
    private double passedTime;

    /**
     * EvoBatch's contructor.
     * 
     * @param gc            GraphicsContext from EvoContainer's Canvas (used to draw images on the Canvas)
     * 
     */
    public EvoBatch(GraphicsContext gc) {
        this.gc = gc;
    }

    /**
     * Draws text to game screen.
     * 
     * @param textToDraw    text to draw
     * @param size          text font size
     * @param x             text's x-position
     * @param y             text's y-position
     */
    public void drawText(String textToDraw, int size, int x, int y) {

        //set font
        gc.setFont(new Font(size));
        //set fill color
        gc.setFill(Color.WHITE);
        //draw the text
        gc.fillText(textToDraw, x, y);

    }

    /**
     * Simplest drawing method for when you want to just draw Sprites.
     * 
     * @param texture       Sprite that's drawn
     * @param xPos          x-position of where the Sprite is drawn
     * @param yPos          y-position of where the Sprite is drawn
     * @param width         Sprite's width
     * @param height        Sprite's height
     */
    public void draw(Sprite texture, float xPos, float yPos, int width, int height) {
        gc.drawImage(texture, xPos, yPos, width, height);
    }

    /**
     * Drawing method for Colliders. Mainly to be used for debugging.
     * 
     * @param c             Collider that you want to draw
     */
    public void draw(Collider c) {
        gc.setFill(Color.rgb(100, 100, 100, 0.5));
        gc.fillRect(c.getxPos(), c.getyPos(), c.getWidth(), c.getHeight());
    }

    /**
     * Drawing method for rotating Sprites.
     * 
     * @param texture       Sprite that's drawn
     * @param xPos          x-position of where the Sprite is drawn
     * @param yPos          y-position of where the Sprite is drawn
     * @param width         Sprite's width
     * @param height        Sprite's height
     * @param rotation      how much the Sprite is rotated
     * @param pivot         Sprite's pivot point
     */
    public void draw(Sprite texture, float xPos, float yPos, int width, int height, float rotation, PivotPoint pivot) {

        gc.save();
        rotate(rotation, xPos + (width*pivot.getxValue()), yPos + (height*pivot.getyValue()));
        gc.drawImage(texture, xPos, yPos, width, height);
        gc.restore();
    }

    /**
     * Helper method for rotating Sprites.
     * 
     * @param rotation      how much the Sprite is rotated
     * @param xPos          where the rotation's x-position is
     * @param yPos          where the rotation's y-position is
     */
    private void rotate(float rotation, double xPos, double yPos) {
        Rotate r = new Rotate(rotation, xPos, yPos);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /**
     * Sets the current passed time.
     * 
     * Passed time is needed when drawing AnimatedSprites.
     * 
     * @param t             how much time has passed since game was started
     */
    public void setPassedTime(double t) {
        passedTime = t;
    }

    /**
     * Returns how much time has passed since game was started
     * 
     * @return              how much time has passed since game was started
     */
    public double getPassedTime() {
        return passedTime;
    }
}