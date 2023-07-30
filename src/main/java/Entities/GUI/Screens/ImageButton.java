package Entities.GUI.Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageButton extends JButton {
    private BufferedImage image;

    public ImageButton(String label){
        super(label);
        setFont(FontCreator.getFontAharoni(60f));
        setForeground(Color.WHITE);
    }

    public void changeFontSizeAndColour(float size, Color color){
        setFont(FontCreator.getFontAharoni(size));
        setForeground(color);
    }

    public void setImage(String path) {
        this.image = ImageCreator.getImageFromPath(path);
        setContentAreaFilled(false);
    }
    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width = image.getWidth();
        size.height = image.getHeight();
        return size;
    }
    @Override
    public void setBounds(int x, int y, int width, int height){
        super.setBounds(x,y,width,height);
        if (width != 0 && height != 0) {
            changeSize(width, height);
        }
    }
    public void changeSize(int width, int height){
        this.image =  ImageCreator.scaleImage(image, width, height);
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, (int) getAlignmentX(), (int)getAlignmentY(), this);
        super.paintComponent(g);
    }

}
