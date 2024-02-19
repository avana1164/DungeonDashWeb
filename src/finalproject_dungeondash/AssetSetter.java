/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject_dungeondash;

import Enemy.BigDemon;
import Enemy.BigZombie;
import Enemy.Boss;
import Enemy.Skeleton;
import Enemy.Wizard;
import GUIs.MenuWindow;
import nonmovingobjects.DoorObject;
import nonmovingobjects.HeartObject;
import nonmovingobjects.KeyObject;

/**
 *
 * @author matth
 */
public class AssetSetter {

    DrawingSurface ds;

    public AssetSetter(DrawingSurface ds) {
        this.ds = ds;

    }
    
    /**
     * Instantiate objects in object array and place on map
     */
    public void setObject() { 

        //Create 2 key objects at corner locations
        ds.obj[0] = new KeyObject(); //instantiate object
        ds.obj[0].worldX = 35 * ds.tileSize; //x position of object start
        ds.obj[0].worldY = 31 * ds.tileSize; //y position of object start

        ds.obj[1] = new KeyObject();
        ds.obj[1].worldX = 14 * ds.tileSize;
        ds.obj[1].worldY = 31 * ds.tileSize;
        
        
        //create 2 door objects at entrance to dungeon
        
        ds.obj[2] = new DoorObject();
        ds.obj[2].worldX = 37 * ds.tileSize;
        ds.obj[2].worldY = 20 * ds.tileSize;

        ds.obj[3] = new DoorObject();
        ds.obj[3].worldX = 37 * ds.tileSize;
        ds.obj[3].worldY = 21 * ds.tileSize;

        ds.obj[4] = new HeartObject();
        ds.obj[4].worldX = MenuWindow.gamePanel.player.screenX;
        ds.obj[4].worldY = MenuWindow.gamePanel.player.screenY;
    }

    /**
     * Instantiate enemies in enemy array and place on map
     */
    public void setEnemies() { 
        
        //demons(in courtyard)
        ds.enemies[0] = new BigDemon(ds);
        ds.enemies[0].worldX = 20 * ds.tileSize;
        ds.enemies[0].worldY = 15 * ds.tileSize;
        ds.enemies[1] = new BigDemon(ds);
        ds.enemies[1].worldX = 23 * ds.tileSize;
        ds.enemies[1].worldY = 18 * ds.tileSize;
        ds.enemies[2] = new BigDemon(ds);
        ds.enemies[2].worldX = 26 * ds.tileSize;
        ds.enemies[2].worldY = 21 * ds.tileSize;
        ds.enemies[3] = new BigDemon(ds);
        ds.enemies[3].worldX = 29 * ds.tileSize;
        ds.enemies[3].worldY = 24 * ds.tileSize;
        ds.enemies[4] = new BigDemon(ds);
        ds.enemies[4].worldX = 32 * ds.tileSize;
        ds.enemies[4].worldY = 27 * ds.tileSize;
        ds.enemies[20] = new BigDemon(ds);
        ds.enemies[20].worldX = 35 * ds.tileSize;
        ds.enemies[20].worldY = 26 * ds.tileSize;
        ds.enemies[21] = new BigDemon(ds);
        ds.enemies[21].worldX = 23 * ds.tileSize;
        ds.enemies[21].worldY = 26 * ds.tileSize;
        ds.enemies[26] = new BigDemon(ds);
        ds.enemies[26].worldX = 17 * ds.tileSize;
        ds.enemies[26].worldY = 26 * ds.tileSize;
        
         //zombies(in courtyard)
        ds.enemies[5] = new BigZombie(ds);
        ds.enemies[5].worldX = 21 * ds.tileSize;
        ds.enemies[5].worldY = 15 * ds.tileSize;
        ds.enemies[6] = new BigZombie(ds);
        ds.enemies[6].worldX = 24 * ds.tileSize;
        ds.enemies[6].worldY = 18 * ds.tileSize;
        ds.enemies[7] = new BigZombie(ds);
        ds.enemies[7].worldX = 27 * ds.tileSize;
        ds.enemies[7].worldY = 21 * ds.tileSize;
        ds.enemies[8] = new BigZombie(ds);
        ds.enemies[8].worldX = 30 * ds.tileSize;
        ds.enemies[8].worldY = 24 * ds.tileSize;
        ds.enemies[9] = new BigZombie(ds);
        ds.enemies[9].worldX = 33 * ds.tileSize;
        ds.enemies[9].worldY = 27 * ds.tileSize;
        ds.enemies[22] = new BigZombie(ds);
        ds.enemies[22].worldX = 36 * ds.tileSize;
        ds.enemies[22].worldY = 30 * ds.tileSize;
        ds.enemies[23] = new BigZombie(ds);
        ds.enemies[23].worldX = 36 * ds.tileSize;
        ds.enemies[23].worldY = 32 * ds.tileSize;
        ds.enemies[27] = new BigZombie(ds);
        ds.enemies[27].worldX = 36 * ds.tileSize;
        ds.enemies[27].worldY = 24 * ds.tileSize;
        
        //skeletons(in dungeon)
        ds.enemies[10] = new Skeleton(ds);
        ds.enemies[10].worldX = 39 * ds.tileSize;
        ds.enemies[10].worldY = 25 * ds.tileSize;
        ds.enemies[11] = new Skeleton(ds);
        ds.enemies[11].worldX = 41 * ds.tileSize;
        ds.enemies[11].worldY = 25 * ds.tileSize;
        ds.enemies[12] = new Skeleton(ds);
        ds.enemies[12].worldX = 43 * ds.tileSize;
        ds.enemies[12].worldY = 25 * ds.tileSize;
        ds.enemies[13] = new Skeleton(ds);
        ds.enemies[13].worldX = 45 * ds.tileSize;
        ds.enemies[13].worldY = 25 * ds.tileSize;
        ds.enemies[14] = new Skeleton(ds);
        ds.enemies[14].worldX = 47 * ds.tileSize;
        ds.enemies[14].worldY = 25 * ds.tileSize;
        ds.enemies[24] = new Skeleton(ds);
        ds.enemies[24].worldX = 49 * ds.tileSize;
        ds.enemies[24].worldY = 11 * ds.tileSize;
        ds.enemies[25] = new Skeleton(ds);
        ds.enemies[25].worldX = 51 * ds.tileSize;
        ds.enemies[25].worldY = 11 * ds.tileSize;
        ds.enemies[28] = new Skeleton(ds);
        ds.enemies[28].worldX = 53 * ds.tileSize;
        ds.enemies[28].worldY = 11 * ds.tileSize;

         //wizards(in dungeon)
        ds.enemies[15] = new Wizard(ds);
        ds.enemies[15].worldX = 40 * ds.tileSize;
        ds.enemies[15].worldY = 30 * ds.tileSize;
        ds.enemies[16] = new Wizard(ds);
        ds.enemies[16].worldX = 42 * ds.tileSize;
        ds.enemies[16].worldY = 11 * ds.tileSize;
        ds.enemies[17] = new Wizard(ds);
        ds.enemies[17].worldX = 44 * ds.tileSize;
        ds.enemies[17].worldY = 30 * ds.tileSize;
        ds.enemies[18] = new Wizard(ds);
        ds.enemies[18].worldX = 46 * ds.tileSize;
        ds.enemies[18].worldY = 11 * ds.tileSize;
        ds.enemies[19] = new Wizard(ds);
        ds.enemies[19].worldX = 48 * ds.tileSize;
        ds.enemies[19].worldY = 11 * ds.tileSize;
        
        //boss knight in dungeon
        ds.enemies[29] = new Boss(ds);
        ds.enemies[29].worldX = 52 * ds.tileSize;
        ds.enemies[29].worldY = 11 * ds.tileSize;
    }

}
