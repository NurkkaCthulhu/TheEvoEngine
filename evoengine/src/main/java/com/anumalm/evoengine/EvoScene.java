package com.anumalm.evoengine;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * The game runs in EvoScenes.
 * 
 * To make your own scene for the game, extend EvoScene to get these methods.
 * 
 * @author      Anu Malm anu.malm@cs.tamk.fi
 * @version     2018.1218
 * @since       3.0
 */
public class EvoScene extends Scene {
    private Group root;

    /**
     * Constructor for EvoScene.
     * 
     * Sends a new Group and background color to the JavaFX Scene-super class.
     */
    public EvoScene() {
        super(new Group(), Color.BLACK);
        this.root = (Group) this.getRoot();
    }

    /**
     * Suggested method for EvoScenes.
     * 
     * Uses EvoBatch to draw images to the game window.
     * 
     * @param batch         EvoBatch that's used to draw images etc.
     */
    public void draw(EvoBatch batch) {}

    /**
     * EvoScene can have a list of Colliders for collision checks.
     * 
     * @return              EvoScene's Collider-list
     */
    public ArrayList<Collider> getSceneColliders() {
        return new ArrayList<Collider>();
    }

    /**
     * Since game runs in the class that extends EvoContainer, EvoScenes can use
     * this method to easily run their own logic during runtime. 
     */
    public void sceneLogic(){}
}