package Entities.GUI.Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundPanel extends JPanel {

    ImageIcon bg;
    /**
     * Helper method that sets up the background image
     */
    public void setBackgroundImage(String bgPath){
        BufferedImage image = ImageCreator.getImageFromPath(bgPath);
        bg = new ImageIcon(ImageCreator.scaleImage(image, 1920, 1080));
        setPreferredSize(new Dimension(1920,1080));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg.getImage(), 0, 0, this);
    }

}
