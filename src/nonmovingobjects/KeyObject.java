/*
2022-06-13
Jason Yu
key object class
 */
package nonmovingobjects;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author matth
 */
public class KeyObject extends SuperObject {
    public KeyObject(){
        name  = "Key";// set the name of the object to key
        try{// create the try to the try catch
            // load in the image sprites for the hearts
            image = ImageIO.read(getClass().getResourceAsStream("/nonmovingobjects/keySprite.png"));
        
        }catch(IOException e){// create the catch to the try catch 
            e.printStackTrace();// pint out the error 
        }
        
    }   
}