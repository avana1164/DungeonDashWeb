/*
jason, matthew, avinash
 * May 25, 2022
wizard class
 */
package Enemy;

import finalproject_dungeondash.DrawingSurface;
import finalproject_dungeondash.Entity;

public class Wizard extends Entity {
// instatntiate nessisary variables
/**
 * constructs a new wizard
 * @param ds the drawing surface variables 
 */
    public Wizard(DrawingSurface ds) {
        super(ds);
        //set all atributes of the wizard to its required values
        enemyDamage = 3;
        maxLife = 2;
        sizeX = 48;
        sizeY = 84;
        life = maxLife;
        speed = 2;
        solidArea.x = 9;
        solidArea.y = 69;
        solidArea.width = 33;
        solidArea.height = 15;
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
        idle1 = setup("/meleeEnemySprites/wizzard_m_idle_anim_f0", sizeX, sizeY);
        idle2 = setup("/meleeEnemySprites/wizzard_m_idle_anim_f1", sizeX, sizeY);
        idle3 = setup("/meleeEnemySprites/wizzard_m_idle_anim_f2", sizeX, sizeY);
        idle4 = setup("/meleeEnemySprites/wizzard_m_idle_anim_f3", sizeX, sizeY);
    }


}
