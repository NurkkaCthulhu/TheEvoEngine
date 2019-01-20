package com.anumalm.game;

import com.anumalm.evoengine.*;
import java.util.ArrayList;

/**
 * MainMenu is an EvoScene that holds all the menu-related drawing and logical information. 
 * 
 * @author      Anu Malm anu.malm@cs.tamk.fi
 * @version     2018.1218
 * @since       3.0
 */
public class MainMenu extends EvoScene {

    private Sprite bg;
    private Sprite playButtonSprite;
    private Sprite logo;
    private GameObject playButton;
    private Collider playCollider;
    private ArrayList<Collider> sceneColliders;

    /**
     * Constructor for MainMenu.
     */
    public MainMenu() {
        bg = new Sprite("background.png");
        playButtonSprite = new Sprite("playbtn.png");
        logo = new Sprite("logo.png");
        playButton = new GameObject(playButtonSprite, 76f, 400f, 345, 103, 1, true, "playButton");
        playButton.addCollider(new Collider(playButton.getxPos(), playButton.getyPos(), 345, 103));

        sceneColliders = new ArrayList<>();
        sceneColliders.add(playButton.getCollider());

    }

    /**
     * Draw-method overridden from EvoScene.
     * 
     * Used to draw everything to game screen.
     * 
     * @param batch             EvoBatch that's used for drawing
     */
    @Override
    public void draw(EvoBatch batch) {
        batch.draw(bg, 0f, 0f, 500, 750);
        batch.draw(logo, 50f, 70f, 416, 155);
        batch.draw(playButtonSprite, 76f, 400f, 345, 103);
    }

    /**
     * List of Colliders that were added to this EvoScene's colliders-list.
     * 
     * Used to check whether PLAY-button was pressed.
     */
    @Override
    public ArrayList<Collider> getSceneColliders() {
        return sceneColliders;
    }


}