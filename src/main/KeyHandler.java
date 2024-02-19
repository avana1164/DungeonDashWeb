/*
 * Avinash Anand
 * June 13, 2022
 * KeyHandler will take key inputs into account
 */
package main;

import finalproject_dungeondash.DrawingSurface;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, yPressed, uPressed;
    public int yPressedCounter = 0;
    DrawingSurface ds;

    public KeyHandler(DrawingSurface ds) {
        this.ds = ds;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        if (code == KeyEvent.VK_Y) {
            yPressed = true;
         

        }

        if (code == KeyEvent.VK_P) {
            if (ds.gameState == ds.PLAYSTATE) {
                ds.gameState = ds.PAUSESTATE;
            } else if (ds.gameState == ds.PAUSESTATE){
                ds.gameState = ds.PLAYSTATE;
            }
        }
        
        if (code == KeyEvent.VK_C && ds.gameState == ds.MSGSTATE){
            ds.gameState = ds.ENDSTATE;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_Y) {
            yPressed = false;
        }
    }
}
