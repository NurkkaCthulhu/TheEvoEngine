package com.anumalm.game;

import javafx.scene.paint.Color;
import com.anumalm.evoengine.*;

import java.util.ArrayList;

/**
 * Simple game made using EvoEngine. 
 * 
 * @author      Anu Malm anu.malm@cs.tamk.fi
 * @version     2018.1204
 * @since       1.0
 */
public class Main extends EvoContainer{
    
    private EvoScene currentScene;
    private MainMenu menu;
    private GameScene levelOne;
    public final int WIDTH = 500;
    public final int HEIGHT = 750;
    private int playerScore = 0;
    private boolean loadLevelOne = true;
    private boolean loadMenu = false;

    /**
     * Game starts here.
     * 
     * Launch(args)-call is mandatory.
     * @param args      Commandline arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * EvoContainer's method that is used to init the game window's settings.
     */
    @Override
    public void initSettings(EvoSettings settings) {
        settings.setTitle("Schmuppi");
        settings.setHeight(HEIGHT);
        settings.setWidth(WIDTH);
    }

    /**
     * EvoContainer's init method for game objects/sprites/textures etc.
     */
    @Override
    public void initGame() {
        menu = new MainMenu();
        levelOne = new GameScene(this);
        currentScene = menu;

        playMusic("bgmusic.mp3", true);
    }

    /**
     * Game runs in this update loop.
     * 
     * Aims to be called 60 times a second.
     */
    @Override
    public void update(EvoBatch batch) {
        currentScene.draw(batch);
        currentScene.sceneLogic();

        //if play-button collider is clicked, move to game scene
        if(colliderClicked(currentScene.getSceneColliders().get(0)) && loadLevelOne) {
            loadScene(levelOne);
            currentScene = levelOne;
            loadLevelOne = false;
            loadMenu = true;
            clickResultReset();
        }

        //change to menu screen if game is over
        if(levelOne.gameOver() && loadMenu) {
            currentScene = menu;
            loadLevelOne = true;
            loadMenu = false;
            playerScore = levelOne.getPlayerScore();
            levelOne = new GameScene(this);
        }
        //if menu is shown, show player's last run's score
        if(currentScene == menu) {
            batch.drawText("Score last time: " + playerScore, 40, 80, 380);
        }
    }
}
