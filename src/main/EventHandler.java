/*
 * Avinash Anand
 * June 13, 2022
 * EventHandler will check for any hit collision and will take any player health away if the player has collided with an enemy
 */
package main;

import GUIs.MenuWindow;
import finalproject_dungeondash.DrawingSurface;

import java.awt.Rectangle;

/**
 *
 * @author jason
 */
public class EventHandler {

    public DrawingSurface ds;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(DrawingSurface ds) {
        this.ds = ds;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        if (hit(MenuWindow.gamePanel.player.worldX,MenuWindow.gamePanel.player.worldX,"right")==true) {
            MenuWindow.gamePanel.player.life-=1;
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;
        MenuWindow.gamePanel.player.solidArea.x = MenuWindow.gamePanel.player.worldX + MenuWindow.gamePanel.player.solidArea.x;
        MenuWindow.gamePanel.player.solidArea.y = MenuWindow.gamePanel.player.worldY + MenuWindow.gamePanel.player.solidArea.y;
        eventRect.x = eventCol * DrawingSurface.tileSize + eventRect.x;
        eventRect.y = eventRow * DrawingSurface.tileSize + eventRect.y;
        if (MenuWindow.gamePanel.player.solidArea.intersects(eventRect)) {
            if (MenuWindow.gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.equals("any")) {
                hit = true;
            }
        }
        MenuWindow.gamePanel.player.solidArea.x = MenuWindow.gamePanel.player.solidAreaDefaultX;
        MenuWindow.gamePanel.player.solidArea.y = MenuWindow.gamePanel.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        return hit;

    }

}
