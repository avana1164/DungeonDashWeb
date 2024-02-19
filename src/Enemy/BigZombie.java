/*
2022-06-07
Matthew, Avinash, Jason
big zombie  class
 */
package Enemy;

import finalproject_dungeondash.Entity;
import finalproject_dungeondash.DrawingSurface;

/**
 *
 * @author anusoumyaganapathy
 */
public class BigZombie extends Entity {

    /**
     * constructs a new boss
     *
     * @param ds the drawing surface variables
     */
    public BigZombie(DrawingSurface ds) {
        super(ds);
        //set all atributes of the big zombie to its required values
        maxLife = 5;
        life = maxLife;
        sizeX = 64;
        sizeY = 68;
        speed = 1;
        solidArea.x = 14;
        solidArea.y = 50;
        solidArea.width = 36;
        solidArea.height = 18;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        // invoke the get sprites method
        getSprites();
    }

    /**
     * load the images into the file
     */
    public void getSprites() {
        // load all 4 sprites into netbeans 
        idle1 = setup("/meleeEnemySprites/big_zombie_idle_anim_f0", sizeX, sizeY);
        idle2 = setup("/meleeEnemySprites/big_zombie_idle_anim_f1", sizeX, sizeY);
        idle3 = setup("/meleeEnemySprites/big_zombie_idle_anim_f2", sizeX, sizeY);
        idle4 = setup("/meleeEnemySprites/big_zombie_idle_anim_f3", sizeX, sizeY);
    }

    public void setAction() {
        actionLockCounter++; //counter to change enemy direction in intervals
        if (actionLockCounter == 420) { //at 7 seconds
            generateDirection();
            actionLockCounter = 0; //reset counter to wait anotehr 4 seconds
        }
    }

}
