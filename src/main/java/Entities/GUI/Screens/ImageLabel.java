package Entities.GUI.Screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageLabel extends JLabel {
    private BufferedImage image;



    public ImageLabel(String label){
        super(label);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setFont(FontCreator.getFontAharoni(60f));
        setForeground(Color.WHITE);
        setBorder(new EmptyBorder(0,30,0,30));

    }

    public void setFontSizeAndColour(float size, Color color){
        setFont(FontCreator.getFontAharoni(size));
        setForeground(color);
    }

    public void setImage(String path) {
        this.image = ImageCreator.getImageFromPath(path);
    }
    @Override
    public Dimension getPreferredSize() {
        //return new Dimension(image.getWidth(), image.getHeight());
        return new Dimension(0,0);
    }

    @Override
    public void setBounds(int x, int y, int width, int height){
        super.setBounds(x,y,width,height);
        if (width != 0 && height != 0) {
            changeSize(width, height);
        }
    }
    @Override
    public void setBounds(Rectangle rectangle){
        super.setBounds(rectangle);
        if (rectangle.width != 0 && rectangle.height != 0) {
            changeSize(rectangle.width, rectangle.height);
        }
    }

    @Override
    public void setPreferredSize(Dimension preferredSize){

    }

    private void changeSize(int width, int height){
        this.image =  ImageCreator.scaleImage(image, width, height);
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
        super.paintComponent(g);

    }

}
