package com.anumalm.evoengine;

import javafx.scene.image.Image;


/**
 * AnimatedSprites hold a set of separate Sprites that can be stitched together for animation.
 * 
 * AnimatedSprites must follow the proper naming convention: player1.png for example is the first frame of an AnimatedSprite.
 * 
 * @author      Anu Malm anu.malm@cs.tamk.fi
 * @version     2018.1218
 * @since       2.0
 */
public class AnimatedSprite extends Sprite {

    Sprite[] sprites;
    double duration;
    /**
     * Constructor for AnimatedSprite.
     * 
     * Gets Sprites from a resources-folder that's in ../_project_name_/src/main/-path.
     * Example of good file names: player1.png, player2.png, player3.png
     * 
     * @param path          image's name (for example "player")
     * @param fileExtension Sprite image's file extension, incase someone wants to use .jpg instead of.png
     * @param amount        how many frames there are
     * @param duration      how long one frame is shown
     */
    public AnimatedSprite(String path, String fileExtension, int amount, double duration) {
        sprites = new Sprite[amount];
        setDuration(duration);
        for(int i = 0; i < amount; i++) {
            sprites[i] = new Sprite(path + (i+1) + fileExtension);
        }
    }

    /**
     * Sets the duration fow long each frame is shown.
     * 
     * Every frame's duration is the same.
     * 
     * @param d         how long each frame is shown
     */
    public void setDuration(double d) {
        duration = d;
    }

    /**
     * Returns how long a frame is shown.
     * 
     * @return          how long each frame is shown
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Returns the Sprite of the current frame.
     * 
     * @param time      how much time has passed since game started
     * @return          current frame's Sprite
     */
    public Sprite getFrame(double time) {
        int index = (int)((time % (sprites.length * duration))/duration);
        return sprites[index];
    }

}