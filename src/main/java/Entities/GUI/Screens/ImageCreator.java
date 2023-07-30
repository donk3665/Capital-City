package Entities.GUI.Screens;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageCreator {

    public static BufferedImage scaleImage(Image image, int width, int height){
        BufferedImage resized = new BufferedImage(width,height, BufferedImage.TRANSLUCENT);
        Graphics2D g2 = resized.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();

        return resized;
    }
    public static BufferedImage getImageFromPath(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(ImageCreator.class.getResource(path)));
        }
        catch (IOException e){
            System.err.println("IO ERROR");
            System.exit(0);
        }
        return image;
    }
}
