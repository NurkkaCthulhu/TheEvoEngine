package com.anumalm.game;

import com.anumalm.evoengine.*;
import java.util.ArrayList;

/**
 * Main EvoScene for running the game.
 * 
 * All actual playing happens here. Main updater-parameter is used to
 * get information from the class that runs the game.
 * 
 * @author      Anu Malm anu.malm@cs.tamk.fi
 * @version     2018.1218
 * @since       3.0
 */
public class GameScene extends EvoScene {

    private Sprite bg;
    private AnimatedSprite monsterTexture;
    private AnimatedSprite playerTexture;
    private Player player;
    private Sprite bulletTexture;
    private int bulletWidth = 10;
    private int bulletHeight = 10;
    private ArrayList<Collider> sceneColliders;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> removableBullets;
    private ArrayList<Monster> monsters;
    private ArrayList<Monster> removableMonsters;
    private float playerSpeed = 4f;
    private float bulletCoolDown = 0.3f;
    private double oldPassedTime = 0;
    private double passedTime = 0;
    private int monsterHealth = 1;
    private float monsterSpawnTimer = 0;
    private float monsterSpawnCoolDown = 2f;
    private int playerLives = 5;
    private int playerScore = 0;
    private Main updater;
    
    /**
     * Constructor for GameScene.
     * 
     * @param updater       the Main-class that updates the game
     */
    public GameScene(Main updater) {
        this.updater = updater;

        bg = new Sprite("background_clean.png");

        monsterTexture = new AnimatedSprite("enemy", ".png", 8, 0.1);

        playerTexture = new AnimatedSprite("player", ".png", 3, 0.1);
        player = new Player(playerTexture, 250f, 680f, 64, 64, 1, true, "player");
        player.addCollider(new Collider(player.getxPos(), player.getyPos(), 64, 64));

        bulletTexture = new Sprite("bullet.png");

        bullets = new ArrayList<>();
        monsters = new ArrayList<>();
        removableBullets = new ArrayList<>();
        removableMonsters = new ArrayList<>();
        sceneColliders = new ArrayList<>();
        sceneColliders.add(player.getCollider());

    }

    /**
     * Draw method from EvoScene-super class.
     * 
     * This is called in Main-class to update the happenings of this EvoScene.
     * 
     * @param batch             EvoBatch that's used to draw stuff to the game window
     */
    @Override
    public void draw(EvoBatch batch) {
        passedTime = batch.getPassedTime();

        //draw background
        batch.draw(bg, 0, 0, 500, 750);

        //draw all bullets
        for(Bullet b : bullets) {
            batch.draw(bulletTexture, b.getxPos(), b.getyPos(), b.getWidth(), b.getHeight() );
        }
        //draw all monsters
        for(Monster m : monsters) {
            batch.draw(monsterTexture.getFrame(passedTime), m.getxPos(), m.getyPos(), m.getWidth(), m.getHeight());
        }
        //draw player
        batch.draw(playerTexture.getFrame(passedTime), player.getxPos(), player.getyPos(), player.getWidth(), player.getHeight());

        //text, font size, x-pos, y-pos
        batch.drawText("Lives left: " + playerLives, 30, 20, 50);
        batch.drawText("Score: " + playerScore, 30, 20, 77);
    }

    /**
     * Returns the Collider-list of the GameScene.
     * 
     * Not all Colliders are added here, just the player.
     * 
     * @return                  list of GameScene's colliders that were added tho the list
     */
    @Override
    public ArrayList<Collider> getSceneColliders() {
        return sceneColliders;
    }

    /**
     * This method is called be the Main-class and holds all the logical stuff
     * that happens in this particular EvoScene.
     */
    @Override
    public void sceneLogic() {
        playerControls();
        bulletLogic();
        monsterLogic();
        collisionCheck();
    }

    /**
     * All the player control checks.
     * 
     * Updates player's Collider too.
     */
    private void playerControls() {

        //Check that player can only move when they're inside the game window

        if(player.getxPos() >= 0) {
            if(updater.keyPressed().contains("LEFT")) {
                player.setxPos(player.getxPos() - (1 * playerSpeed));
            }
        }
        if(player.getxPos() <= (updater.WIDTH - player.getWidth())) {
            if(updater.keyPressed().contains("RIGHT")) {
                player.setxPos(player.getxPos() + (1 * playerSpeed));
            }
        }
        if(player.getyPos() >= 0) {
            if(updater.keyPressed().contains("UP")) {
                player.setyPos(player.getyPos() - (1 * playerSpeed));
            }
        }
        if(player.getyPos() <= (updater.HEIGHT - player.getHeight())) {
            if(updater.keyPressed().contains("DOWN")) {
                player.setyPos(player.getyPos() + (1 * playerSpeed));
            }
        }
        //SPACE to shoot
        if(updater.keyPressed().contains("SPACE") && canShoot()) {
            oldPassedTime = passedTime;
            //play shoot sound
            updater.playSound("shoot.wav");
            bullets.add(new Bullet(bulletTexture, player.getxPos()+player.getWidth()/2-bulletWidth/2, player.getyPos(), bulletWidth, bulletHeight, 1, true, "bullet", 1));
        }

        //update player's collision position
        player.getCollider().setxPos(player.getxPos());
        player.getCollider().setyPos(player.getyPos());
    }

    /**
     * Check whether the player can shoot
     * 
     * @return              can the player shoot
     */
    private boolean canShoot() {
        boolean result = false;

        if((passedTime - oldPassedTime) >= bulletCoolDown) {
            result = true;
        }
        return result;
    }

    /**
     * Holds all the logic concerning the Bullets that Player shoots.
     */
    private void bulletLogic() {
        //update bullets and check if we have some that we can remove
        for(Bullet b : bullets) {
            b.update();
            //if the Bullet is not enabled anymore, add it to the removableBullets-list.
            if(!b.isEnabled()) {
                removableBullets.add(b);
            }
        }
        //remove removableBullets from the bullets-ArrayList
        if(removableBullets.size() > 0) {
            bullets.removeAll(removableBullets);
            removableBullets.clear();
        }
    }

    /**
     * Holds all the monster specific logic of this EvoScene.
     */
    private void monsterLogic() {
        if(passedTime - monsterSpawnTimer >= monsterSpawnCoolDown) {
            monsterSpawnTimer = (float)passedTime;
            monsters.add(new Monster(monsterTexture, (float)(Math.random()*(500-64)), -64f, 64, 64, 1, true, "monster", monsterHealth));
        }

        for(Monster m : monsters) {
            m.update();

            //add any monsters that are not enabled into removableMonsters-list
            if(!m.isEnabled()) {
                removableMonsters.add(m);
            }

            //if monster gets past player, remove lives from player
            if(m.gotToBase()) {
                playerLives -= 1;
                updater.playSound("explosion.wav");
            }
        }
        
        if(removableMonsters.size() > 0) {
            monsters.removeAll(removableMonsters);
            removableMonsters.clear();
        }

        if(monsterSpawnCoolDown >= 0.4f) {
            monsterSpawnCoolDown -= 0.005f;
        }
    }

    /**
     * Check whether a bullet has collided with a monster Collider.
     * 
     * If there was a collision, the Bullet and Monster are marked inactive and can
     * be removed from bullets- and mosnters-ArrayLists.
     */
    private void collisionCheck() {
        for(Bullet b: bullets) {
            for(Monster m : monsters) {
                if(b.getCollider().collidesWith(m.getCollider())) {
                    m.setHealth(m.getHealth()-b.getDamage());
                    b.setEnabled(false);
                    updater.playSound("spiderdie.mp3");
                    playerScore += 1;
                }
            }
        }
    }

    /**
     * Check whether the game is running.
     * 
     * Returns false if player's lives have reached 0.
     * 
     * @return                  does player still have lives
     */
    public boolean gameOver() {
        return (playerLives <= 0);
    }

    /**
     * Return the player's score.
     * 
     * @return          player's score
     */
    public int getPlayerScore() {
        return playerScore;
    }

}