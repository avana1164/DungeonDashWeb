/*
Matthew Yu,Jason Yu
01/06/2022
OPEN WORLD TEXT FILE LEGEND:
a - grass
b - tree
c - water
d - door
e - dungeon floor normal
f - dungeon floor cracked
g - dungeon wall normal
h - dungeon wall goo
i - dirt path
j - boss lair floor
 */
package finalproject_dungeondash;

import GUIs.MenuWindow;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class TileManager {

    // create variables for the class
    public DrawingSurface ds;
    public Tile[] tileArray;
    public char[][] mapTileChar;
    BufferedImage fullHeartHealthSprite, HalfFullHeartSprite, EmptyHeartSprite;

    /**
     * constructor- constructs a new tilemanager object with default values
     *
     * @param dr the drawing surface
     */
    public TileManager(DrawingSurface dr) {
        this.ds = dr;
        tileArray = new Tile[10]; //how many tiles we use
        mapTileChar = new char[DrawingSurface.maxWorldCol][DrawingSurface.maxWorldRow];
        getTileImage();
        loadMap();

    }

    /**
     * load the map from the text file
     */
    public void loadMap() {
        try {
            // create a new input stream for the file
            InputStream is = TileManager.class.getResourceAsStream("/GUIs/TextFiles/OpenWorld1.txt");
            // create a buffered reader to read teh file
            Scanner br = new Scanner(is);
            // create a stiring to represent each line and an array of characters
            String line;
            String[] charactersOnLine;
            char character;
            // use a for loop to read through the matrix
            for (int row = 0; row < (DrawingSurface.maxWorldRow); row++) {
                line = br.nextLine();// read the line
                // use the second for loop to read through the line
                for (int col = 0; col < (DrawingSurface.maxWorldCol); col++) {
                    // split the line into characters reletave to where the spaces are 
                    charactersOnLine = line.split(" ");
                    character = charactersOnLine[col].charAt(0);
                    //add the tile to the tile array
                    mapTileChar[col][row] = character;
                }
            }
// close the buffered reader 
            br.close();

        } catch (Exception e) {
            System.out.println("ERROR:" + e);
        }
    }

    /**
     * gets all the images and puts them into the tile array
     */
    public void getTileImage() {
        try {
            // create all the tiles to be put into the tile arrat
            tileArray[0] = new Tile();
            tileArray[1] = new Tile();
            tileArray[2] = new Tile();
            tileArray[3] = new Tile();
            tileArray[4] = new Tile();
            tileArray[5] = new Tile();
            tileArray[6] = new Tile();
            tileArray[7] = new Tile();
            tileArray[8] = new Tile();
            tileArray[9] = new Tile();
            //read all the images and put them into the tile array
            tileArray[0].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/grasstile.jpg"));
            tileArray[1].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/treeSprite.png"));
            tileArray[2].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/Water3.9.png"));
            tileArray[3].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/lilypad.png"));
            tileArray[4].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/floorNormal.png"));
            tileArray[5].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/DungeonFloorCracked.png"));
            tileArray[6].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/wall_mid.png"));
            tileArray[7].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/wall_goo.png"));
            tileArray[8].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/pathway3.9.png"));
            tileArray[9].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/BossFloor.png"));
            // set collission to true for some tiles so that you cannot walk over them
            tileArray[1].setCollision(true);
            tileArray[2].setCollision(true);
            //tileArray[3].setCollision(true); //doors dont block for now
            tileArray[6].setCollision(true);
            tileArray[7].setCollision(true);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * turns a character to a number
     *
     * @param c
     * @return
     */
    public int charToNum(char c) {
        // general format 
        // if the input is equal to a certain letter, set tht letter to a number
        if (c == 'a') {
            return 0;
        } else if (c == 'b') {
            return 1;
        } else if (c == 'c') {
            return 2;
        } else if (c == 'd') {
            return 3;
        } else if (c == 'e') {
            return 4;
        } else if (c == 'f') {
            return 5;
        } else if (c == 'g') {
            return 6;
        } else if (c == 'h') {
            return 7;
        } else if (c == 'i') {
            return 8;
        } else if (c == 'j') {
            return 9;
        } else if (c == 'k') {
            return 10;
        } else {
            return -1;
        }

    }

    /**
     * draws the tiles
     *
     * @param g2 the graphics 2d used
     */
    public void draw(Graphics2D g2) {
        // instantiate nessisary variables 
        int worldCol = 0;
        int worldRow = 0;

        char tileChar;
        int indexOfTileArray;
        //while the world row and colunm are less that the max
        while (worldCol < DrawingSurface.maxWorldCol && worldRow < DrawingSurface.maxWorldRow) {
            // set the tile character to the item at world row and colunm
            tileChar = mapTileChar[worldCol][worldRow];
            //set the index of the tile array to the numer returned by the char to num method 
            indexOfTileArray = charToNum(tileChar);
            // set world x to the world column times the tiles size
            int worldX = worldCol * DrawingSurface.tileSize;
            // set the world y to the world row time s tile size 
            int worldY = worldRow * DrawingSurface.tileSize;
            //set screenx to equal where the user is reletive to the screen on the x postion  
            int screenX = worldX - MenuWindow.gamePanel.player.getWorldX() + MenuWindow.gamePanel.player.screenX;
            //set screeny to equal where the user is reletive to the screen on the y postion  

            int screenY = worldY - MenuWindow.gamePanel.player.getWorldY() + MenuWindow.gamePanel.player.screenY;

            //if statement only draws tiles that are on the screen to improve rendering
            if (worldX + DrawingSurface.tileSize > MenuWindow.gamePanel.player.getWorldX() - MenuWindow.gamePanel.player.screenX
                    && worldX - DrawingSurface.tileSize < MenuWindow.gamePanel.player.getWorldX() + MenuWindow.gamePanel.player.screenX
                    && worldY + DrawingSurface.tileSize > MenuWindow.gamePanel.player.getWorldY() - MenuWindow.gamePanel.player.screenY
                    && worldY - DrawingSurface.tileSize < MenuWindow.gamePanel.player.getWorldY() + MenuWindow.gamePanel.player.screenY) {
                //draws all the tiles that need to be under first 
                if (tileChar == 'b') {
                    g2.drawImage(tileArray[0].image, screenX, screenY, ds.tileSize, ds.tileSize, null);
                } else if (tileChar == 'd') {
                    g2.drawImage(tileArray[2].image, screenX, screenY, ds.tileSize, ds.tileSize, null);
                }
                // draws the rest of the tiles 
                g2.drawImage(tileArray[indexOfTileArray].image, screenX, screenY, ds.tileSize, ds.tileSize, null);
            }
            // increacest he colunm num
            worldCol++;
            // if the counter reaches the end of the line, then reset it and incrument the row
            if (worldCol == DrawingSurface.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }

        }

    }

}
