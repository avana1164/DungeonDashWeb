/*
Test
 */
package Enemy;

import finalproject_dungeondash.DrawingSurface;
import finalproject_dungeondash.Entity;

/**
 *
 * @author matth
 */
public class BigDemon extends Entity { 
    /**
     * constructs a new demon
     *
     * @param ds the drawing surface variables
     */
    public BigDemon(DrawingSurface ds) {
        super(ds);
        //set all atributes of the demon to its required values

        enemyDamage = 2;
        maxLife = 3;
        life = maxLife;
        speed = 2;
        sizeX = 64;
        sizeY = 72;
        solidArea.x = 16;
        solidArea.y = 48;
        solidArea.width = 32;
        solidArea.height = 24;
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
        idle1 = setup("/meleeEnemySprites/big_demon_idle_anim_f0", sizeX, sizeY);
        idle2 = setup("/meleeEnemySprites/big_demon_idle_anim_f1", sizeX, sizeY);
        idle3 = setup("/meleeEnemySprites/big_demon_idle_anim_f2", sizeX, sizeY);
        idle4 = setup("/meleeEnemySprites/big_demon_idle_anim_f3", sizeX, sizeY);
    }

    /*
    public void drawDemon(Graphics2D g2d){
        BufferedImage image = null;
        if(spriteNum == 1){
            image = idle1;
        } else if(spriteNum == 2){
            image = idle2;
        } else if(spriteNum == 3){
            image = idle3;
        } else if(spriteNum == 4){
            image = idle4;
        }
        
        switch(spriteDirection){
            case 'l':
                g2d.drawImage(image, FinalProject_DungeonDash.gamePanel.player.getWorldX() + 64, FinalProject_DungeonDash.gamePanel.player.getWorldY(), FinalProject_DungeonDash.gamePanel.player.getWorldX(), FinalProject_DungeonDash.gamePanel.player.getWorldY() + 72, 0, 0, 64, 72, null);
            case 'r':
                g2d.drawImage(image, FinalProject_DungeonDash.gamePanel.player.getWorldX(), FinalProject_DungeonDash.gamePanel.player.getWorldY(), null);
        }
    }*/
}
