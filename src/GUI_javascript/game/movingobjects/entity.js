/**
 * Instantiate entity object
 *
 * @param ds - drawing surface to draw entity on
 */
function Entity(ds) {
    this.ds = ds; //so every method can access this 
    let worldX, worldY, speed, spriteCounter, spriteNum, health, sizeX, sizeY;
    let idle1, idle2, idle3, idle4, walk1, walk2, walk3, walk4;
    let isWalking, isMonster;
    this.direction = "left";
    this.spriteDirection = 'l';
    this.actionLockCounter = 0;
    this.collisionOn = false;
    this.spriteCounter = 0;
    this.spriteNum = 1;
    this.maxLife = 100;
    this.life = 100;
    this.invincible = false;
    this.invincibleCounter = 0;
    let solidArea = {
        solidX: 60,
        solidY: 60,
        solidWidth: 0,
        solidHeight: 0
    }; //hitbox of player
    
    let attackArea = {
        attackX: 0,
        attackY: 0,
        attackWidth: 0,
        attackHeight: 0,
    }
    this.damage = 1;
    this.dead = false;
    
    generateDirection();
}

function generateDirection() {
    let randomInt = Math.floor(Math.random() * 4) + 1; //generate random number from 1 to 4(inclusive) to determine which of 4 direcitons to travel

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
function setAction() {
    actionLockCounter++; //counter to change enemy direction in intervals
    if (actionLockCounter == 240) { //at 4 seconds
        generateDirection();
        actionLockCounter = 0; //reset counter to wait anotehr 4 seconds
    }
}

/**
 * Update entity status
 */
function update() {
    setAction(); //see if change direction
    collisionOn = false; //set collision to no before check
    //check for collision between tile, objects and other enemies and player
    ds.cChecker.checkTile(this);
    ds.cChecker.checkObject(this, false);
    ds.cChecker.checkEntity(this, ds.enemies);
    let contactPlayer = ds.cChecker.checkPlayer(this);

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
function draw(g2) {
    let image = null;

    //find screen x and y coordinate of monster based on player position
    let screenX = worldX - MenuWindow.gamePanel.player.getWorldX() + MenuWindow.gamePanel.player.screenX;
    let screenY = worldY - MenuWindow.gamePanel.player.getWorldY() + MenuWindow.gamePanel.player.screenY;

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