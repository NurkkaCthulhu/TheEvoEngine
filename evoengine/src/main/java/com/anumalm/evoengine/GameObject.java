package com.anumalm.evoengine;

/**
 * GameObjects are the fundamental objects in EvoEngine that can be used to make game characters etc.
 * 
 * @author      Anu Malm anu.malm@cs.tamk.fi
 * @version     2018.1218
 * @since       1.0
 */
public class GameObject {
    private int width;
    private int height;
    private float xPos;
    private float yPos;
    private boolean enabled;
    private int scale;
    private String name;
    private Sprite sprite;
    private Collider collider;

    /**
     * Constructor for the GameObject-object.
     * 
     * @param sprite        Sprite of the GameObject
     * @param xPos          x-position of the GameObject
     * @param yPos          y-position of the GameObject
     * @param width         width of the GameObject
     * @param height        height of the GameObject
     * @param scale         scale of the GameObject
     * @param enabled       is the GameObject enabled
     * @param name          name for the GameObject
     */
    public GameObject(Sprite sprite, float xPos, float yPos, int width, int height, int scale, boolean enabled, String name) {
        setSprite(sprite);
        setxPos(xPos);
        setyPos(yPos);
        setWidth(width);
        setHeight(height);
        setScale(scale);
        setEnabled(enabled);
        setName(name);
        collider = null;
    }
    
    /**
     * Adds a Collider to the GameObject.
     * 
     * @param collider          Collider for the GameObject
     */
    public void addCollider(Collider collider) {
        this.collider = collider;
    }

    /**
     * Returns the Collider of the GameObject.
     * 
     * @return                  GameObject's Collider
     */
    public Collider getCollider() {
        return collider;
    }

    /**
     * Return the current Sprite of the GameObject.
     * 
     * @return          GameObject's prite
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Set a Sprite to the GameObject.
     * 
     * @param sprite      new desired Sprite
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Return the name of the GameObject
     * 
     * @return          GameObject's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set a name for the GameObject.
     * 
     * @param name     desired name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the width of the GameObject.
     * 
     * @return          GameObject's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set a width for the GameObject.
     * 
     * @param width     desired width
     */
    public void setWidth(int width) {
        if(width >= 0) {
            this.width = width;
        } else {
            throw new IllegalArgumentException("Width can't be a negative value!");
        }
    }

    /**
     * Return the height of the GameObject.
     * 
     * @return          GameObject's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set a height for the GameObject.
     * 
     * @param height     desired height
     */
    public void setHeight(int height) {
        if(height >= 0) {
            this.height = height;
        } else {
            throw new IllegalArgumentException("Height can't be a negative value!");
        }
    }

    /**
     * Return the current x-position of the GameObject.
     * 
     * @return          GameObject's x-position
     */
    public float getxPos() {
        return xPos;
    }

    /**
     * Set a x-position to the GameObject.
     * 
     * @param xPos      new desired x-position
     */
    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    /**
     * Return the current y-position of the GameObject.
     * 
     * @return          GameObject's y-position
     */
    public float getyPos() {
        return yPos;
    }

    /**
     * Set a y-position to the GameObject.
     * 
     * @param yPos      new desired y-position.
     */
    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    /**
     * Return whether the GameObject is enabled.
     * 
     * @return          is the GameObject enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Set the GameObject to be enabled or not.
     * 
     * @param enabled   new desired enabled state (true/false)
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Return the scale of the GameObject.
     * 
     * @return          GameObject's scale
     */
    public int getScale() {
        return scale;
    }

    /**
     * Set a scale to the GameObject.
     * 
     * @param scale     new desired scale
     */
    public void setScale(int scale) {
        if(scale >= 0) {
            this.scale = scale;
        } else {
            throw new IllegalArgumentException("Scale can't be a negative value!");
        }

    }
}
