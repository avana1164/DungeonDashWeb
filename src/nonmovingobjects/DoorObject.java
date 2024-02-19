/*
2022-06-13
Jason Yu
door object class
 */
package nonmovingobjects;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author matth
 */
public class DoorObject extends SuperObject{
        public DoorObject(){
        name  = "Door";// set the name of the object to door 
        try{// create the try to the try catch
            // load in the image sprite for the door 
            image = ImageIO.read(getClass().getResourceAsStream("/nonmovingobjects/doors_leaf_closed.png"));
           
        }catch(IOException e){// create the catch to the try catch 
            e.printStackTrace();// pint out the error 
        }
        collision = true;// set collision to true 
    
    }
    
}
