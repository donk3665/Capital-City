package Entities.GUI.Screens.ScreenElements;

import Entities.GUI.Screens.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundPanel extends JPanel {

    ImageIcon bg;
    /**
     * Helper method that sets up the background image
     */
//    public void setBackgroundImage(String bgPath){
//        BufferedImage image = ImageCreator.getImageFromPath(bgPath);
//        bg = new ImageIcon(ImageCreator.scaleImage(image, 1920, 1080));
//        setPreferredSize(new Dimension(1920,1080));
//
//    }
    public BackgroundPanel(){
        setPreferredSize(new Dimension((int) Screen.width,(int) Screen.height));
    }
    public void setBackgroundImage(ImageIcon image){
        bg = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bg != null) {
            g.drawImage(bg.getImage(), 0, 0, this);
        }
    }

}
