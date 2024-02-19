/*
 * B Cutten
    Nov 2021
    A class which allows drawing, because it extends JPanel, by way of the 
    Graphics2D class
    Because this class is only going to ever be used by the Graphics2DTester, it could be 
    included in the same file (Graphics2DTester.java)
 */
package finalproject_dungeondash;

import main.UserInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import main.KeyHandler;
import main.CollisionChecker;
import nonmovingobjects.SuperObject;

public class DrawingSurface extends JPanel implements Runnable {
    
    Thread gameThread; //smooth animations
    KeyHandler keyH = new KeyHandler(this); //create key listener object
    public Player player; //instantiate player object
    
    public static int tileSize = 60, playerSpeed = 4; //tiles are squares 60 pixels big, player moves at relatively fast speed
    public CollisionChecker cChecker = new CollisionChecker(this); //instantiate collision checker object to check for collisions between things

    //Settings for screen
    public static final int maxScreenCol = 20; //columns on screeen
    public static final int maxScreenRow = 12; //rows on screen
    public static final int screenWidth = tileSize * maxScreenCol; //tiles on screen width
    public static final int screenHeight = tileSize * maxScreenRow; //tiles screen height wise
    public TileManager tileM = new TileManager(this); //create a tile manager class to manage tiles

    //Settings for world:
    public static final int maxWorldCol = 74; //columns in world
    public static final int maxWorldRow = 42; //rows in world
    public final int worldWidth = DrawingSurface.tileSize * maxWorldCol; //pixels on screen width
    public final int worldHeight = DrawingSurface.tileSize * maxWorldRow; //pixels on screen height
    
    public AssetSetter aSetter = new AssetSetter(this); //create asset setter to set position of objects and enemies
    public SuperObject[] obj = new SuperObject[5]; //ADJUST SIZE LATER

    //create object to handle on screen game panel text
    public UserInterface ui = new UserInterface(this);

    public Entity enemies[] = new Entity[30]; //create array of 30 enemies

    //GAME STATE
    public int gameState;
    final public int PLAYSTATE = 0; //display game
    final public int PAUSESTATE = 1; //display pause screen
    final public int MSGSTATE = 2; //display end message
    final public int ENDSTATE = 3; //leave screen

    /**
     * Create surface to draw on
     */
    public DrawingSurface() {
        
        //set panel settings
        this.setPreferredSize(new Dimension(1200, 720));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        //gameState = PLAYSTATE; //start playing game
    }

    /**
     * Instantiate enemies and objects for games
     */
    public void setupGame() {
        player = new Player(this, keyH); //create player object
        aSetter.setObject();
        aSetter.setEnemies();

    }

    /**
     * Start game thread for animation
     */
    public void startGameThread() {
        
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    /**
     * Overrides paintComponent in JPanel class so that we can do our own custom
     * painting
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);//does the necessary work to prepare the panel for drawing
        //the Graphics2D class is the class that handles all the drawing
        //must be casted from older Graphics class in order to have access to some newer methods
        Graphics2D g2d = (Graphics2D) g;

        //tile stuff
        tileM.draw(g2d);
        
        //draw each enemy
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {//as long as enemy exists
                enemies[i].draw(g2d); //draw it
            }
        }

        //draw each object
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) { //as long as object exists
                obj[i].draw(g2d, this); //draw it
            }
        }
        
        if (player.isAttacking() == true) { //if player is attacking
            player.drawSword(g2d); //draw them with their sword
        } else { //otherwise just draw the player
            player.drawPlayer(g2d);
        }
        ui.pause(g2d); //check the game state

        g2d.dispose(); //saves memory

    }

    /**
     * Code for animation
     */
    @Override
    public void run() {
        double drawInterval = 1000000000 / 60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {

                update();
                repaint();
                delta--;
            }
        }
    }

    /**
     * Update screen to see animations
     */
    public void update() {
        if (gameState == PLAYSTATE) { //if game is currently being played
            //update player animation
            player.update(); 
            
            for (int i = 0; i < enemies.length; i++) { //update enemy animation
                if (enemies[i] != null) {
                    enemies[i].update();
                }
            }

            if (player.life <= 0 || (enemies[enemies.length - 1].dead == true)) { //if player is out of health of boss is dead        
                gameState = MSGSTATE; //display game over message
            }
        }
    }
}
