/*
Anything that moves/has hitbox. We probably need this for hitbox. 
 */
package finalproject_dungeondash;

import GUIs.MenuWindow;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.UtilityTool;

//ABSTRACT CLASS, RYISNOW SAID SO
public class Entity {

    DrawingSurface ds;
    /*sizeX - is the horizontal length of the sprite
    sizeY - is the vertical length of the sprite
    speed - is speed of entity
    sprite number - is an image of entity, combined they form animation
    health - health of entity
    worldX and worldY - entity coordinates in map
    idle 1-4 - idle sprites
    walk 1-4 - walking sprites
    isWalking - if entity is moving
    direction - direction of movement
    spriteDirection - direction of facing(only left or right)
    solidArea - hitbox
    collisionOn - if entity can be collided with
    actionLockCounter - stall for action, so actions are not spammed
     */
    public int worldX, worldY, speed, spriteCounter, spriteNum, health, sizeX, sizeY;
    public BufferedImage idle1, idle2, idle3, idle4, walk1, walk2, walk3, walk4;
    public boolean isWalking;

    public String direction;
    public char spriteDirection;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn;
    public int actionLockCounter = 0;

    //character status
    public int maxLife;
    public int life;

    //character invincibility when attacked
    public boolean invincible = false;
    public int invincibleCounter = 0; //so character invincible for right amount of time

    public Rectangle attackArea; //hitbox of attack

    //enemy damage
    public int enemyDamage;
    public static boolean dead; //if enemy is dead or not(mostly used for boss)

    /**
     * Instantiate entity object
     *
     * @param ds - drawing surface to draw entity on
     */
    public Entity(DrawingSurface ds) {
        this.ds = ds; //so every method can access this 
        collisionOn = false;
        spriteCounter = 0;
        spriteNum = 1;
        collisionOn = false;
        solidArea = new Rectangle(0, 0, 60, 60);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea = new Rectangle(0, 0, 0, 0);
        enemyDamage = 1;
        dead = false;
        direction = "left";
        spriteDirection = 'l';
        generateDirection();
    }

    protected void generateDirection() {
        int randomInt = (int) (Math.random() * 4 + 1); //generate random number from 1 to 4(inclusive) to determine which of 4 direcitons to travel

        if (randomInt == 1) {
            direction = "up";
        } else if (randomInt == 2) {
            direction = "down";
        } else if (randomInt == 3) {
            direction = "left";
            spriteDirection = 'l';
        } else {
            direction = "right";
            spriteDirection = 'r';
        }
    }

    /**
     * Have entities change direction
     */
    public void setAction() {
        actionLockCounter++; //counter to change enemy direction in intervals
        if (actionLockCounter == 240) { //at 4 seconds
            generateDirection();
            actionLockCounter = 0; //reset counter to wait anotehr 4 seconds
        }
    }

    /**
     * Update entity status
     */
    public void update() {
        setAction(); //see if change direction
        collisionOn = false; //set collision to no before check
        //check for collision between tile, objects and other enemies and player
        ds.cChecker.checkTile(this);
        ds.cChecker.checkObject(this, false);
        ds.cChecker.checkEntity(this, ds.enemies);
        boolean contactPlayer = ds.cChecker.checkPlayer(this);

        if (contactPlayer == true) { //if player touches enemy
            if (ds.player.invincible == false) { //and they not in invincible phase
                //they take damage
                ds.player.life -= enemyDamage;
                ds.player.invincible = true; //become invincible
            }

        }

        //if collision in intended direction is false, entity can move in intended direction
        if (collisionOn == false) {
            if (direction.equals("up")) {
                worldY -= speed;
            } else if (direction.equals("down")) {
                worldY += speed;
            } else if (direction.equals("left")) {
                worldX -= speed;
            } else if (direction.equals("right")) {
                worldX += speed;
            }
        }

        spriteCounter++; //switch sprites to give appearance of walking
        if (spriteCounter > 5) {
            switch (spriteNum) {
                case 1:
                    spriteNum = 2;
                    break;
                case 2:
                    spriteNum = 3;
                    break;
                case 3:
                    spriteNum = 4;
                    break;
                case 4:
                    spriteNum = 1;
                    break;
            }
            spriteCounter = 0; //reset to restart loop
        }

        if (invincible == true) { //if enemy is invincible
            invincibleCounter++; //keep track of how long
            if (invincibleCounter > 60) { //once one second passes
                invincible = false; //not invincible anymore
                invincibleCounter = 0; //reset counter
            }
        }
    }

    /**
     * draw enemy on screen
     *
     * @param g2
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        //find screen x and y coordinate of monster based on player position
        int screenX = worldX - MenuWindow.gamePanel.player.getWorldX() + MenuWindow.gamePanel.player.screenX;
        int screenY = worldY - MenuWindow.gamePanel.player.getWorldY() + MenuWindow.gamePanel.player.screenY;

        //if entity is in the camera frame, then draw it
        if (worldX + sizeX > MenuWindow.gamePanel.player.getWorldX() - MenuWindow.gamePanel.player.screenX
                && worldX - sizeX < MenuWindow.gamePanel.player.getWorldX() + MenuWindow.gamePanel.player.screenX
                && worldY + sizeY > MenuWindow.gamePanel.player.getWorldY() - MenuWindow.gamePanel.player.screenY
                && worldY - sizeY < MenuWindow.gamePanel.player.getWorldY() + MenuWindow.gamePanel.player.screenY) {

            //depending on current sprite, draw specific image
            if (spriteNum == 1) {
                image = idle1;
            } else if (spriteNum == 2) {
                image = idle2;
            } else if (spriteNum == 3) {
                image = idle3;
            } else if (spriteNum == 4) {
                image = idle4;
            }

            if (invincible == true) { //if enemy is in invincible phase
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f)); //enemy becomes somewhat transparent when invincible
            }

            if (spriteDirection == 'l') { //if enemy facing left
                g2.drawImage(image, screenX + sizeX, screenY, screenX, screenY + sizeY, 0, 0, sizeX, sizeY, null); //flip right facing sprite
            } else { //otherwise just draw normal sprite
                g2.drawImage(image, screenX, screenY, null);
            }

            //reset tranparency back to normal
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        }

    }

    /*
    abstract method to draw sprite left
     */
    public void drawLeft() {

    }

    /**
     * Setup sprite for use
     *
     * @param imagePath - image path as string
     * @param width - width of image in pixels
     * @param height - height of image in pixels
     * @return usable sprite image
     */
    public BufferedImage setup(String imagePath, int width, int height) {
        BufferedImage image = null;
        UtilityTool uTool = new UtilityTool();
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
