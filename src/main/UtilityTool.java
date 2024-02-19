/*
 * Avinash Anand
 * June 13, 2022
 * The UtilityTool class contains one static method responsible for rescaling images to whatever dimensions are desired.
 */
package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {   
    /**
     * will rescale the desired image
     * @param original is the original unscaled image
     * @param width is the new width
     * @param height is the new height
     * @return the rescaled image
     */
    public static BufferedImage scaleImage(BufferedImage original, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(original, 0, 0, width, height, null);
        g2d.dispose();
        return scaledImage;
    }
}
