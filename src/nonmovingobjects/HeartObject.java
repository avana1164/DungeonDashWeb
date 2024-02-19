/*
2022-06-13
Jason Yu
heart object class
 */
package nonmovingobjects;

import finalproject_dungeondash.DrawingSurface;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author jason
 */
public class HeartObject extends SuperObject {

    
    public HeartObject() {
        name = "Heart";// set the name of the object to heart 
        try {// create the try to the try catch
            // load in the image sprites for the hearts 
            image1 = ImageIO.read(getClass().getResourceAsStream("/nonmovingobjects/ui_heart_full.png"));          
            image2 = ImageIO.read(getClass().getResourceAsStream("/nonmovingobjects/ui_heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/nonmovingobjects/ui_heart_empty.png"));
        } catch (IOException e) {// create the catch to the try catch 
            e.printStackTrace();// print out the error 
        }

    }
}
