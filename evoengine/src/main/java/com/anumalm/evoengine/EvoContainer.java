package com.anumalm.evoengine;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import javafx.event.EventHandler;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * EvoContainer is The Class you must extend when using EvoEngine.
 * 
 * EvoContainer helps the user to set their game window settings, run the game,
 * check key presses and mouse clicks etc. 
 * 
 * @author      Anu Malm anu.malm@cs.tamk.fi
 * @version     2018.1218
 * @since       1.0
 */
abstract public class EvoContainer extends Application {
    private EvoSettings settings;
    private GraphicsContext gc;
    private Canvas canvas;
    private EvoScene scene;
    private ArrayList<String> input;
    private EvoBatch batch;
    private Stage stage;
    private boolean clickResult = false;
    private MediaPlayer musicPlayer;
    private MediaPlayer soundPlayer;

    /**
     * Application-class's init()-method.
     * 
     * Gets called first when the application (=game) starts.
     */
    @Override
    public void init() {
        System.out.println("Author: Anu Malm");
        settings = new EvoSettings();
        input = new ArrayList<String>();
    }

    /**
     * Application-class's start()-method.
     * 
     * Gets called after the init()-method. Holds the game loop.
     */
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        initSettings(settings);
        initGame();

        scene = new EvoScene();

        stage.setTitle(settings.getTitle());
        stage.setResizable(settings.isResizeable());

        loadScene(scene);
        stage.show();
        
        //make sure that the application closes when the window is closed (MediaPlayer prevented this before)
        stage.setOnCloseRequest(e -> System.exit(0));

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            @Override public void handle(long currentNanoTime) {
                double passedTime = (currentNanoTime - startNanoTime) / 1000000000.0;
                batch.setPassedTime(passedTime);
                //clear the canvas before we start drawing stuff in it
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                //update the game screen (60FPS)
                update(batch);
                
            }
        }.start();

    }

    /**
     * Used to play music in the game.
     *  
     * @param path              music file's path
     * @param loop              true if you want to loop the music, false to play only once
     */
    public void playMusic(String path, boolean loop) {
        Media media = null;
        try {
            media = new Media(getClass().getResource("/" + path).toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        musicPlayer = new MediaPlayer(media);

        if(loop) {
            musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        musicPlayer.play();
    }

    /**
     * Used to play sound effects in the game.
     * 
     * @param path          sound file's path
     */
    public void playSound(String path) {
        Media media = null;
        try {
            media = new Media(getClass().getResource("/" + path).toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        soundPlayer = new MediaPlayer(media);

        soundPlayer.play();
    }

    /**
     * Stop the currently playing music.
     */
    public void stopMusic() {
        musicPlayer.stop();
    }

    /**
     * Stops the currently playing sound.
     * 
     */
    public void stopSound() {
        soundPlayer.stop();
    }

    /**
     * Method for changing between EvoScenes in the update()-method.
     * 
     * @param newScene          the new EvoScene that user wants to load
     */
    public void loadScene(EvoScene newScene) {
        canvas = new Canvas(settings.getWidth(), settings.getHeight());
        gc = canvas.getGraphicsContext2D();
        batch = new EvoBatch(gc);
        Group g = (Group) newScene.getRoot();
        g.getChildren().add(canvas);

        scene = newScene;
        stage.setScene(newScene);
    }

    /**
     * Helps to check which keyboard keys have been pressed.
     * 
     * User's key inputs are added to an ArrayList, and when this method is
     * called the user can do key press checks.
     * 
     * @return      ArrayList of pressed keys.
     */
    public ArrayList<String> keyPressed() {
 
        scene.setOnKeyPressed( new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();

                // Only add the code to the list when the code (=key) is not there yet
                if (!input.contains(code)) {
                    input.add(code);
                }
            }
        });

        scene.setOnKeyReleased( new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                input.remove(code);
            }
        });
        
        return input;
    }

    /**
     * Check if a Collider was clicked.
     * 
     * @param c             Collider that we're checking
     * @return              was the Collider clicked
     */
    public boolean colliderClicked(Collider c) {
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if(c.contains(e.getX(), e.getY())){
                    clickResult = true;
                }
            }
        });
        return clickResult;

    }

    /**
     * Resets the clickResult-boolean to false.
     * 
     * User has to manually call this after using the colliderClicked()-method,
     * otherwise the clickResult will return true after one successfull click check
     * has been made.
     */
    public void clickResultReset() {
        clickResult = false;
    }

    /**
     * Called 60 times per second. Game runs here.
     * 
     * Must be implemented for the game to work.
     * 
     * @param batch     EvoBatch that's used for drawing images
     */
    abstract public void update(EvoBatch batch);

    /**
     * Use to initialize the basic settings of the game.
     *
     * @param settings  EvoSettings for initializing the game
     */
    abstract public void initSettings(EvoSettings settings);

    /**
     * Use to initialize all game objects/sprites.
     */
    abstract public void initGame();

}
