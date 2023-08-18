package Entities.GUI.Screens;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ImageCreator {
    private static ArrayList<BufferedImage> cachedImages = new ArrayList<>();
    private static ArrayList<String> cachedPaths = new ArrayList<>();
    private static ArrayList<Dimension> cachedDimensions = new ArrayList<>();

    private static int getCachedImage(String path, Dimension dimension){
        for (int i = 0; i<cachedPaths.size(); i++){
            if  (cachedPaths.get(i).equals(path) && cachedDimensions.get(i).equals(dimension)){
                return i;
            }
        }
        return -1;
    }
    public static BufferedImage getAndScaleImage(String path, Dimension dimension){
        int index = getCachedImage(path,dimension);
        if (index != -1){
            return cachedImages.get(index);
        }
        cachedPaths.add(path);
        return scaleImage(getImageFromPath(path), dimension.width, dimension.height);
    }
    public static BufferedImage getAndScaleImage(String path, int width, int height){
        int index = getCachedImage(path,new Dimension(width, height));
        if (index != -1){
            return cachedImages.get(index);
        }
        cachedPaths.add(path);
        return scaleImage(getImageFromPath(path), width, height);
    }
    private static BufferedImage scaleImage(BufferedImage image, int width, int height){
        BufferedImage resized = new BufferedImage(width,height, BufferedImage.TRANSLUCENT);
        Graphics2D g2 = resized.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();

        cachedImages.add(resized);
        cachedDimensions.add(new Dimension(width,height));

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
        cachedPaths.add(path);
        cachedImages.add(image);
        cachedDimensions.add(new Dimension(image.getWidth(), image.getHeight()));
        return image;
    }
}
