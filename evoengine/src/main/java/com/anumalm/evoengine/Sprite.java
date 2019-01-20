package com.anumalm.evoengine;

import javafx.scene.image.Image;


/**
 * Sprite is a computer graphic which may be displayed on-screen and added to a GameObject.
 * 
 * @author      Anu Malm anu.malm@cs.tamk.fi
 * @version     2018.1218
 * @since       1.0
 */
public class Sprite extends Image {

    /**
     * Constructor for Sprite.
     * 
     * Gets textures from a resources-folder that's in ../_project_name_/src/main/-path.
     * 
     * @param path      image's name (for example "flower.png")
     */
    public Sprite(String path) {
        super("/" + path);
    }

    /**
     * Default contructor for Sprite.
     * 
     * Displays the evoengine default image if nothing else is applied.
     */
    public Sprite() {
        super("https://i.imgur.com/lpckgxI.png");
    }
}