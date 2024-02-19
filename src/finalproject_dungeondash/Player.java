/*
 * Avinash Anand, Jason Yu
 * June 13, 2022
 * The player class holds all the player sprites, their health, their walking, speed, etc
 */
package finalproject_dungeondash;

import Enemy.Boss;
import GUIs.MenuWindow;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.KeyHandler;

public class Player extends Entity {
// instantiate nessesary variables

    public int projSpriteCounter, swordSpriteCounter, idleSpriteCounter, swordSpriteNum, swingSpeed, bladeCooldown;
    private boolean attacking, projectileFiring, cooldownStarted;
    private BufferedImage walk5, walk6, sword1, sword2, sword3, sword4, idle5, idle6;
    private final KeyHandler keyH;
    public final int screenX, screenY;
    int counter = 0;// delete later

    //OBJECT STUFF
    int objIndex;
    int numKeys = 0;

    /**
     * construct a new player
     *
     * @param ds drawing surface
     * @param keyH key handler
     */
    public Player(DrawingSurface ds, KeyHandler keyH) {
        super(ds);
        this.keyH = keyH;
        // set screen x and y to the middle of the screen
        screenX = DrawingSurface.screenWidth / 2 - (DrawingSurface.tileSize / 2);
        screenY = DrawingSurface.screenHeight / 2 - (DrawingSurface.tileSize / 2);
        // invoke methods
        setDefaultValues();
        getSprites();
    }

    /**
     * sets the players default values
     */
    public void setDefaultValues() {
        worldX = DrawingSurface.tileSize * 15; //*14
        worldY = DrawingSurface.tileSize * 9; //*9
        speed = 4;

        //format for rectangle: top left x coor, top left y coor, width, height.
        solidArea.x = 12;
        solidArea.y = 39;
        solidArea.width = 24;
        solidArea.height = 24;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        direction = "right";
        spriteDirection = 'r';
        spriteCounter = 0;
        spriteNum = 1;
        swordSpriteNum = 1;
        attacking = false;
        swingSpeed = 10;
        bladeCooldown = 50;
        cooldownStarted = false;
        isWalking = false;
        idleSpriteCounter = 0;
        //player status 
        maxLife = 10;
        life = maxLife;

        //attack based on player weapon
        attackArea.width = 40;
        attackArea.height = 30;
    }
/**
 * checks if projectile is firing 
 * @return if the projectile is firing 
 */
    public boolean isProjectileFiring() {
        return projectileFiring;
    }
/**
 * chicks if attacking 
 * @return  if the player is attacking
 */
    public boolean isAttacking() {
        return attacking;
    }
/**
 * gets the x variable for the world 
 * @return the world x variable
 */
    public int getWorldX() {
        return worldX;
    }
/**
 * gets the y variable for the world 
 * @return the world y variable
 */
    public int getWorldY() {
        return worldY;
    }
/**
 * gets the direction of the sprite 
 * @return the directin 
 */
    public String getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean c) {
        collisionOn = c;
    }

    public void getSprites() {
        walk1 = setup("/walkingSprites/walking-1", 45, 69);
        walk2 = setup("/walkingSprites/walking-2", 45, 69);
        walk3 = setup("/walkingSprites/walking-3", 45, 69);
        walk4 = setup("/walkingSprites/walking-4", 45, 69);
        walk5 = setup("/walkingSprites/walking-5", 45, 69);
        walk6 = setup("/walkingSprites/walking-6", 45, 69);
        sword1 = setup("/attackingSprites/sword-1", 102, 69);
        sword2 = setup("/attackingSprites/sword-2", 102, 69);
        sword3 = setup("/attackingSprites/sword-3", 102, 69);
        sword4 = setup("/attackingSprites/sword-4", 102, 69);
        idle1 = setup("/idleSprites/idle-1", 45, 69);
        idle2 = setup("/idleSprites/idle-2", 45, 69);
        idle3 = setup("/idleSprites/idle-3", 45, 69);
        idle4 = setup("/idleSprites/idle-4", 45, 69);
        idle5 = setup("/idleSprites/idle-5", 45, 69);
        idle6 = setup("/idleSprites/idle-6", 45, 69);
    }

    public void update() {
        isWalking = false;
        idleSpriteCounter++;
        if (idleSpriteCounter > 8) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 4;
            } else if (spriteNum == 4) {
                spriteNum = 5;
            } else if (spriteNum == 5) {
                spriteNum = 6;
            } else if (spriteNum == 6) {
                spriteNum = 1;
            }
            idleSpriteCounter = 0;
        }
        if (attacking == true) {
            swordAttack();
        }
        if (cooldownStarted == true) {
            bladeCooldown--;
            if (bladeCooldown == 0) {
                bladeCooldown = 50;
                cooldownStarted = false;
            }
        }
        if (ds.keyH.yPressed == true && attacking == false && cooldownStarted == false) {
            attacking = true;
        } else if ((keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) && (attacking == false) && (projectileFiring == false)) {
            isWalking = true;
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
                spriteDirection = 'l';
            } else if (keyH.rightPressed == true) {
                direction = "right";
                spriteDirection = 'r';

            }

            //CHECK TILE COLLISION
            collisionOn = false;
            MenuWindow.gamePanel.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            objIndex = ds.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK ENEMY COLLISION
            int enemyIndex = ds.cChecker.checkEntity(this, ds.enemies);
            attackedByEnemy(enemyIndex);

            //if collision is false, player can move
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

            spriteCounter++;
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
                        spriteNum = 5;
                        break;
                    case 5:
                        spriteNum = 6;
                        break;
                    case 6:
                        spriteNum = 1;
                        break;
                }
                spriteCounter = 0;
            }
        }

        if (ds.keyH.uPressed == true && keyH.yPressed == false) {
            projectileFiring = true;
        }

        //player invincibility
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) { //1 second shielf of invinsibility
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void swordAttack() {
        swingSpeed--;
        swordSpriteCounter++;
        if (swordSpriteCounter == 5) {
            String filepathSword = "src\\finalproject_dungeondash\\SwordSound.wav";// create a string for the filepath
            musicClass musicObject = new musicClass();// create an incedence of the music class
            musicObject.playMusicNonLoop(filepathSword);//pass the filepath throught the method of 'playMusic' F

            //make sure code goes in right spot
            //Make player's hitbox that of the sword area temporarily to see if he deals damage
            //save player's current hitbox and position in world
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //change player position to that of attack area box
            if (spriteDirection == 'l') {
                worldX -= attackArea.width;

            } else if (spriteDirection == 'r') {
                worldX += attackArea.width;
            }

            worldY = (worldY + solidArea.height - attackArea.height);

            //attack area becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //cehck monster collision with updated player hitbox(sword hitbox)
            int enemyIndex = ds.cChecker.checkEntity(this, ds.enemies);
            damageEnemy(enemyIndex);

            //change player's position and hitbox back to original values
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

            switch (swordSpriteNum) {
                case 1:
                    swordSpriteNum = 2;
                    break;
                case 2:
                    swordSpriteNum = 3;
                    break;
                case 3:
                    swordSpriteNum = 4;
                    break;
                case 4:
                    swordSpriteNum = 1;
                    break;
            }
            swordSpriteCounter = 0;
        }

        if (swingSpeed == 0) {
            swordSpriteNum = 1;
            cooldownStarted = true;
            swingSpeed = 20;
            attacking = false;
        }

    }

    public void drawSword(Graphics2D g2d) {

        BufferedImage swordImage = null;
        int shiftedX = screenX;
        int shiftedY = screenY;
        if (attacking == true) {

            if (swordSpriteNum == 1) {
                swordImage = sword1;
            }

            if (swordSpriteNum == 2) {
                swordImage = sword2;
            }

            if (swordSpriteNum == 3) {
                swordImage = sword3;
            }

            if (swordSpriteNum == 4) {
                swordImage = sword4;
            }
        }
        switch (spriteDirection) {
            case 'l':
                shiftedX -= 48;
                shiftedY += 8;
                g2d.drawImage(swordImage, shiftedX + 102, shiftedY, shiftedX, shiftedY + 69, 0, 0, 102, 69, null);
                break;
            case 'r':
                shiftedX -= 13;
                shiftedY += 10;
                g2d.drawImage(swordImage, shiftedX, shiftedY, null);
                break;
        }

    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = ds.obj[i].name;

            if (objectName.equals("Key")) {
                numKeys++;
                ds.obj[i] = null;
            } else if (objectName.equals("Door")) {
                if (numKeys > 0) {
                    ds.obj[i] = null;
                    numKeys--;
                }
            }
        }

    }

    public void attackedByEnemy(int index) {
        if (index != 999) {
            if (invincible == false) {
                life -= ds.enemies[index].enemyDamage;
                invincible = true;
            }
        }
    }

    public void damageEnemy(int index) {
        if (index != 999) {
            if (ds.enemies[index].invincible == false) {
                ds.enemies[index].life--;
                ds.enemies[index].invincible = true;

                if (ds.enemies[index].life <= 0) {
                    ds.enemies[index] = null;
                    if (index == ds.enemies.length - 1) {
                        Boss.dead = true;
                    }

                }
            }

        }

    }

    public void drawPlayer(Graphics2D g2d) {
        BufferedImage image = null;
        if (isWalking == false) {
            if (spriteNum == 1) {
                image = idle1;
            } else if (spriteNum == 2) {
                image = idle2;
            } else if (spriteNum == 3) {
                image = idle3;
            } else if (spriteNum == 4) {
                image = idle4;
            } else if (spriteNum == 5) {
                image = idle5;
            } else if (spriteNum == 6) {
                image = idle6;
            }
        }

        if (isWalking == true) {
            if (spriteNum == 1) {
                image = walk1;
            } else if (spriteNum == 2) {
                image = walk2;
            } else if (spriteNum == 3) {
                image = walk3;
            } else if (spriteNum == 4) {
                image = walk4;
            } else if (spriteNum == 5) {
                image = walk5;
            } else if (spriteNum == 6) {
                image = walk6;
            }
        }

        if (invincible == true) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f)); //player becomes somewhat transparent when invincible
        }

        switch (spriteDirection) {
            case 'l':
                g2d.drawImage(image, screenX + 45, screenY, screenX, screenY + 69, 0, 0, 45, 69, null);
                break;
            case 'r':
                g2d.drawImage(image, screenX, screenY, null);
                break;
        }

        //reset tranparency back to normal
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

    }
}
