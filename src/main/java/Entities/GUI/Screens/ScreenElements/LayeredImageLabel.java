package Entities.GUI.Screens.ScreenElements;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LayeredImageLabel extends ImageLabel{
    public LayeredImageLabel(String label) {
        super(label);
    }
    private BufferedImage image2;
    private String path2;

    public void setImage2(String path){
        this.path2 = path;
        this.image2 = ImageCreator.getImageFromPath(path);
    }
    @Override
    protected void changeSize(int width, int height){
        super.changeSize(width,height);
        this.image2 =  ImageCreator.getAndScaleImage(path2, width, height);
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image2, 0, 0, this);
        super.paintComponent(g);

    }

}
