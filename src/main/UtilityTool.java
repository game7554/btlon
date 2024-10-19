package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        BufferedImage scaledImange = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImange.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImange;
    }
}
