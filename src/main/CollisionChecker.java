/*
 * Matthew Yu
 * June 13, 2022
 * CollisionChecker will check for collision between objects with hitboxes
 */
package main;

import GUIs.MenuWindow;
import finalproject_dungeondash.DrawingSurface;
import finalproject_dungeondash.Entity;
import finalproject_dungeondash.FinalProject_DungeonDash;

public class CollisionChecker {

    DrawingSurface ds; //drawing surface
    
    /**
     * Instantiate collision checker
     * @param ds - drawing surface for collision checker lives
     */
    public CollisionChecker(DrawingSurface ds) {
        this.ds = ds;
    }
    
    /**
     * Check for collision between certain tile and entity
     * @param entity - given entity object
     */
    public void checkTile(Entity entity) {
        //x and y coordinates of all 4 corners of entity
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        
        //tile number of all 4 corners of entity
        int entityLeftCol = entityLeftWorldX / DrawingSurface.tileSize;
        int entityRightCol = entityRightWorldX / DrawingSurface.tileSize;
        int entityTopRow = entityTopWorldY / DrawingSurface.tileSize;
        int entityBottomRow = entityBottomWorldY / DrawingSurface.tileSize;
        
        
        char tileChar1, tileChar2; //tile character from text file
        int tileNum1, tileNum2; //tile character converted to integer
        
        
        //each if and else if handles a different side of entity: top, right, left, bottom
        //if entity moving up, find current top position of entity
        if (entity.direction.equals("up")) {
            entityTopRow = (entityTopWorldY - entity.speed) / DrawingSurface.tileSize;
            tileChar1 = MenuWindow.gamePanel.tileM.mapTileChar[entityLeftCol][entityTopRow]; //store top left corner
            tileChar2 = MenuWindow.gamePanel.tileM.mapTileChar[entityRightCol][entityTopRow]; //store top right corner
            //convert to integers
            tileNum1 = MenuWindow.gamePanel.tileM.charToNum(tileChar1);
            tileNum2 = MenuWindow.gamePanel.tileM.charToNum(tileChar2);

            if (MenuWindow.gamePanel.tileM.tileArray[tileNum1].isCollision() == true
                    || MenuWindow.gamePanel.tileM.tileArray[tileNum2].isCollision() == true) { //if tile is solid
                entity.collisionOn = true; //entity is colliding with a solid tile, should not move further
            }

        } else if (entity.direction.equals("down")) {
            entityBottomRow = (entityBottomWorldY + entity.speed) / DrawingSurface.tileSize;
            tileChar1 = MenuWindow.gamePanel.tileM.mapTileChar[entityLeftCol][entityBottomRow];
            tileChar2 = MenuWindow.gamePanel.tileM.mapTileChar[entityRightCol][entityBottomRow];
            tileNum1 = MenuWindow.gamePanel.tileM.charToNum(tileChar1);
            tileNum2 = MenuWindow.gamePanel.tileM.charToNum(tileChar2);

            if (MenuWindow.gamePanel.tileM.tileArray[tileNum1].isCollision() == true
                    || MenuWindow.gamePanel.tileM.tileArray[tileNum2].isCollision() == true) {
                entity.collisionOn = true;
            }
        } else if (entity.direction.equals("left")) {
            entityLeftCol = (entityLeftWorldX - entity.speed) / DrawingSurface.tileSize;
            tileChar1 = MenuWindow.gamePanel.tileM.mapTileChar[entityLeftCol][entityTopRow];
            tileChar2 = MenuWindow.gamePanel.tileM.mapTileChar[entityLeftCol][entityBottomRow];
            tileNum1 = MenuWindow.gamePanel.tileM.charToNum(tileChar1);
            tileNum2 = MenuWindow.gamePanel.tileM.charToNum(tileChar2);

            if (MenuWindow.gamePanel.tileM.tileArray[tileNum1].isCollision() == true
                    || MenuWindow.gamePanel.tileM.tileArray[tileNum2].isCollision() == true) {
                entity.collisionOn = true;
            }
        } else { 
            entityRightCol = (entityRightWorldX + entity.speed) / DrawingSurface.tileSize;
            tileChar1 = MenuWindow.gamePanel.tileM.mapTileChar[entityRightCol][entityTopRow];
            tileChar2 = MenuWindow.gamePanel.tileM.mapTileChar[entityRightCol][entityBottomRow];
            tileNum1 = MenuWindow.gamePanel.tileM.charToNum(tileChar1);
            tileNum2 = MenuWindow.gamePanel.tileM.charToNum(tileChar2);

            if (MenuWindow.gamePanel.tileM.tileArray[tileNum1].isCollision() == true
                    || MenuWindow.gamePanel.tileM.tileArray[tileNum2].isCollision() == true) {
                entity.collisionOn = true;
            }
        }

    }

    /**
     * Check if player and enemy collide
     * @param entity - given enemy
     * @param player - player
     * @return number indicating which enemy of array collides with player
     */
    public int checkObject(Entity entity, boolean player) {
        int index = 999; //set enemy number to no value(only 30 enemies)

        for (int i = 0; i < ds.obj.length; i++) { //for each object
            if (ds.obj[i] != null) { //if object exists
                //get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get object solid area position
                ds.obj[i].solidArea.x = ds.obj[i].worldX + ds.obj[i].solidArea.x;
                ds.obj[i].solidArea.y = ds.obj[i].worldY + ds.obj[i].solidArea.y;

                //reposition to account for speed    
                if (entity.direction.equals("up")) {
                    entity.solidArea.y -= entity.speed;

                } else if (entity.direction.equals("down")) {
                    entity.solidArea.y += entity.speed;
                } else if (entity.direction.equals("left")) {
                    entity.solidArea.x -= entity.speed;
                } else{ 
                    entity.solidArea.x += entity.speed;
                }
                if (entity.solidArea.intersects(ds.obj[i].solidArea)) { //check if entity hitbox crosses object hitbox can use intersect method of rectangle class as limited objects in array
                    if (ds.obj[i].collision == true) { //if object is solid
                        entity.collisionOn = true; //collision happens
                    }
                    if (player == true) { //if entity is player
                        index = i; //return index of enemy
                    }
                }
                //reset entity and object hitbox
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                ds.obj[i].solidArea.x = ds.obj[i].solidAreaDefaultX;
                ds.obj[i].solidArea.y = ds.obj[i].solidAreaDefaultY;

            }

        }

        return index;
    }

    /**
     * Check entity collision with each other
     * @param entity given entity
     * @param target all other entities
     * @return index of hitbox of enemy array that intersects with hitbox of given entity 
     */
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) { //for each enemy
            if (target[i] != null) { //if enemy exists
                //get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get object solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
                
                //reposition to account for speed   
                if (entity.direction.equals("up")) {
                    entity.solidArea.y -= entity.speed;

                } else if (entity.direction.equals("down")) {
                    entity.solidArea.y += entity.speed;

                } else if (entity.direction.equals("left")) {
                    entity.solidArea.x -= entity.speed;

                } else { //change to ELSE
                    entity.solidArea.x += entity.speed;

                }
                if (entity.solidArea.intersects(target[i].solidArea)) { //if enemies collide
                    if (target[i] != entity) { //entity doesn't collide with itself
                        entity.collisionOn = true; //collision happens
                        index = i; //save enemy index it colllides with
                    }
                }

                //reset entity and object hitbox
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }

        }

        return index;

    }
    
    /**
     * Check player collision with entity
     * @param entity - given enemy
     * @return if collision happens
     */
    public boolean checkPlayer(Entity entity) {
        
        boolean contactPlayer = false; //assume no collision to start
        //get entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        //get object solid area position
        ds.player.solidArea.x = ds.player.worldX + ds.player.solidArea.x;
        ds.player.solidArea.y = ds.player.worldY + ds.player.solidArea.y;
        
        //reposition to account for speed
        if (entity.direction.equals("up")) {
            entity.solidArea.y -= entity.speed;

        } else if (entity.direction.equals("down")) {
            entity.solidArea.y += entity.speed;

        } else if (entity.direction.equals("left")) {
            entity.solidArea.x -= entity.speed;

        } else { 
            entity.solidArea.x += entity.speed;

        }
        if (entity.solidArea.intersects(ds.player.solidArea)) { //check if enemy collides with player, can use intersect method of rectangle class as limited objects in array
            entity.collisionOn = true;
            contactPlayer = true;
        }
        //reset enemy and player hitbox
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        ds.player.solidArea.x = ds.player.solidAreaDefaultX;
        ds.player.solidArea.y = ds.player.solidAreaDefaultY;
        
        return contactPlayer;
    }

}
