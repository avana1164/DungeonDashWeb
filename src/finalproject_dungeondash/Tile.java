/*
2022-06-13
Jason Yu
the tile class
 */
package finalproject_dungeondash;

import java.awt.image.BufferedImage;

/**
 *
 * @author matth
 */
public class Tile {

    // instantiate variables for class 
    public BufferedImage image;
    public boolean collision;

    /**
     * constructor- constructs a tile with default values
     */
    public Tile() {
        this.collision = false;
    }

    /**
     * constructor- constructors that constructs a new user with player given
     * values
     *
     * @param collision
     */
    public Tile(boolean collision) {
        this();
        this.collision = collision;
    }

    /**
     * checks if there is a collission
     *
     * @return a boolean representing if there is a collission
     */
    public boolean isCollision() {
        return collision;
    }

    /**
     * Setter- setts the collission variable
     *
     * @param c the input variable
     */
    public void setCollision(boolean c) {
        collision = c;
    }

    /**
     * Tostring- puts the info for the class into a string which can be
     * displayed to the user
     *
     * @return the string of info
     */
    public String toString() {
        return "Collision: " + collision;
    }
    /**
     * checks if a tile is equal to another tile 
     * @param t the tile that you cant to compare with 
     * @return a boolean representing if the 2 variables are the same
     */
    public boolean equals(Tile t) {
        return collision == t.isCollision();
    }

    /**
     * clone method- clones s user
     *
     * @return the cloned user
     */
    public Tile clone() {
        return new Tile(collision);
    }
}
