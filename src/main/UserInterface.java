/*
 * Avinash Anand
 * June 13, 2022
 * The UserInterface class will draw the player's hearts at the top of the screen and is responsible for pausing the screen and the game over screen
 */
package main;

import GUIs.GameOverWindow;
import GUIs.MenuWindow;
import finalproject_dungeondash.DrawingSurface;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import nonmovingobjects.HeartObject;
import nonmovingobjects.SuperObject;

/**
 *
 * @author matth
 */
public class UserInterface {

    BufferedImage fullHeartHealthSprite, HalfFullHeartSprite, EmptyHeartSprite;
    DrawingSurface ds;
    Font pauseFont, winFont;  
    Graphics2D g2;
    public GameOverWindow gameOver;

    /**
     * This is a constructor responsible for creating a UserInterface object
     * @param ds is the DrawingSurface object 
     */
    public UserInterface(DrawingSurface ds) {
        this.ds = ds;
        pauseFont = new Font("Poor Richard", Font.BOLD, 50);
        winFont = new Font("Poor Richard", Font.BOLD, 49);
        // create hud object
        //All three heart sprite variables have been assigned the values of the full heart, half heart, and empty heart sprites
        SuperObject heart = new HeartObject();
        fullHeartHealthSprite = heart.image1;
        HalfFullHeartSprite = heart.image2;
        EmptyHeartSprite = heart.image3;

    }
    
    /**
     * Will display the text for the boss dying
     */
    public void gameWon() {
        g2.setColor(Color.GREEN);
        g2.setFont(winFont);
        g2.drawString("YOU KILLED THE BOSS! PRESS 'C' TO CONTINUE", 80, 350);

    }
    
    /**
     * Will display the text for the player dying
     */
    public void gameLoss() {
        g2.setColor(Color.RED);
        g2.drawString("YOU DIED! PRESS 'C' TO CONTINUE", 200, 350);

    }
    
    /**
     * The panel will be switched when a button is pressed
     */
    public void switchPanel() {
        MenuWindow.window.setVisible(false);
    }
    
    /**
     * will pause the game
     * @param g2d is the Graphics2D library variable responsible for drawing the sprites  
     */
    public void pause(Graphics2D g2d) {
        g2 = g2d;
        g2.setFont(pauseFont);
        g2.setColor(Color.WHITE);

        if (ds.gameState == ds.PAUSESTATE) {
            g2.drawString("GAME  PAUSED:  PRESS  'P'  TO  CONTINUE", 130, 375); //The text is displayed to the user
        } else if (ds.gameState == ds.PLAYSTATE) {
            drawPlayerLife(g2); //if the game isn't paused, draw the life 
        } else if (ds.gameState == ds.MSGSTATE) {
            if (MenuWindow.gamePanel.enemies[MenuWindow.gamePanel.enemies.length - 1].dead == true) {
                gameWon();
            } else {
                gameLoss();
            }

        } else if (ds.gameState == ds.ENDSTATE) { //change to else
            MenuWindow.window.setVisible(false);
            if (gameOver == null){
                gameOver = new GameOverWindow();
            }
            gameOver.getScore();
            gameOver.setResizable(false);
            gameOver.setVisible(true);
        }
    }
    
    /**
     * Draws the heart sprites for the player
     * @param g2 is the Graphics2D library variable responsible for drawing the sprites 
     */
    public void drawPlayerLife(Graphics2D g2) {
        int x = DrawingSurface.tileSize / 2;
        int y = DrawingSurface.tileSize / 2;
        for (int i = 0; i < MenuWindow.gamePanel.player.maxLife / 2; i++) {
            g2.drawImage(EmptyHeartSprite, x, y, ds.tileSize / 2, ds.tileSize / 2, null); //if the player has lost a full heart, the empty heart sprite is drawn
            x += DrawingSurface.tileSize / 2;
        }
        x = DrawingSurface.tileSize / 2;
        y = DrawingSurface.tileSize / 2;
        for (int i = 0; i < Math.ceil(((double) (MenuWindow.gamePanel.player.life) / 2)); i++) {
            g2.drawImage(HalfFullHeartSprite, x, y, ds.tileSize / 2, ds.tileSize / 2, null); //if the player has lost half a heart, the half heart sprite is drawn
            if (i < MenuWindow.gamePanel.player.life / 2) {
                g2.drawImage(fullHeartHealthSprite, x, y, ds.tileSize / 2, ds.tileSize / 2, null); //if the player has a full heart, the full heart sprite is drawn

            }
            x += DrawingSurface.tileSize / 2;

        }

    }

}
