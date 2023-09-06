package GUI.Screens.ScreenElements;

import GUI.Screens.Screen;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    ImageIcon bg;
    /**
     * Helper method that sets up the background image
     */
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
