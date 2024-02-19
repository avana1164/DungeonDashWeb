/*
2022-06-13
Jason Yu
superclass for non moving objects object class
 */
package nonmovingobjects;

import GUIs.MenuWindow;
import finalproject_dungeondash.DrawingSurface;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SuperObject {
    
    public BufferedImage image1, image2, image3, image; // instantiate buffered images
    public String name;// instantiate a string for the name
    public boolean collision = false;// instantiate a boolean variable collission and set it to false  
    public int worldX, worldY;// instantiat integers for the world x and y variables in the world 
    public Rectangle solidArea;// instantiat a rectangle object solid area
    // create new solid area defaults "x" and "y" and set them to 0
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    /**
     * constructor- consturcts a new super object
     */
    public SuperObject() {
        // creates a rectangle called solid area 
        solidArea = new Rectangle(0, 0, 48, 48);
    }

    public void draw(Graphics2D g2, DrawingSurface ds) {
        // creates 2 variables: screenX and ScreenY which represent where the character is reletave to the screen
        int screenX = worldX - MenuWindow.gamePanel.player.getWorldX() + MenuWindow.gamePanel.player.screenX;
        int screenY = worldY - MenuWindow.gamePanel.player.getWorldY() + MenuWindow.gamePanel.player.screenY;

        //if statement only draws tiles that are on the screen to improve rendering
        if (worldX + DrawingSurface.tileSize > MenuWindow.gamePanel.player.getWorldX() - MenuWindow.gamePanel.player.screenX
                && worldX - DrawingSurface.tileSize < MenuWindow.gamePanel.player.getWorldX() + MenuWindow.gamePanel.player.screenX
                && worldY + DrawingSurface.tileSize > MenuWindow.gamePanel.player.getWorldY() - MenuWindow.gamePanel.player.screenY
                && worldY - DrawingSurface.tileSize < MenuWindow.gamePanel.player.getWorldY() + MenuWindow.gamePanel.player.screenY) {
           // draws the tile
            g2.drawImage(image, screenX, screenY, ds.tileSize, ds.tileSize, null);
        }

    }

}
