package com.anumalm.evoengine;

import javafx.scene.paint.Color;

/**
 * EvoSettings can be used to set information for the game.
 * 
 * The information can then be easily getted from the settings.
 * 
 * @author      Anu Malm anu.malm@cs.tamk.fi
 * @version     2018.1218
 * @since       1.0
 */
public class EvoSettings {
    private String title;
    private int width;
    private int height;
    private boolean resizeable;
    private Color bgColor;

    public EvoSettings() {
        resizeable = false;
        bgColor = Color.MAGENTA;
    }

    /**
     * Return the player's desired title for the game.
     *
     * @return          title of the game
     */
    public String getTitle() {
        return title;
    }

    /**
     * Change the title of the game.
     * @param title     new title for the game
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Return the width of the game window.
     * 
     * @return          game window's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set a width for the game window. Should be called only once before game starts.
     * 
     * @param width     width that's added for the game window
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Return the height of the game window.
     * 
     * @return          game window's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set a height for the game window. Should be called only once before game starts.
     * 
     * @param height    height that's added for the game window
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Check if the game window is resizable.
     * 
     * @return          can the user resize the window
     */
    public boolean isResizeable() {
        return resizeable;
    }

    /**
     * Set if user can resize the game window.
     * 
     * @param resizeable    desired value for resizing action
     */
    public void setResizeable(boolean resizeable) {
        this.resizeable = resizeable;
    }

    /**
     * Returns the background color.
     *
     * @return      color of the background
     */
    public Color getBgColor() {
        return bgColor;
    }

    /**
     * Set the color for the background.
     * 
     * @param bgColor   new color for the background
     */
    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }
}
