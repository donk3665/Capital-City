package Entities.GUI.Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ImagePanel extends JPanel {

    BufferedImage image;

    public ImagePanel(){
    }
   // public void setImage(ImageIcon image){
//    image = image;
//    }
    public void setImage(String path) {
        this.image = ImageCreator.getImageFromPath(path);
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }

}

