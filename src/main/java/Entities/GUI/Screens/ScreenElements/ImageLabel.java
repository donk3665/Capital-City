package Entities.GUI.Screens.ScreenElements;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageLabel extends JLabel {
    private BufferedImage image;
    private String path;


    private boolean preserveRatio = false;

    public ImageLabel(String label){
        super(label);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setFont(FontCreator.getFontAharoni(60f));
        setForeground(Color.WHITE);
        setBorder(new EmptyBorder(0,45,0,30));

    }
    public void setPreserveRatio(boolean preserveRatio) {
        this.preserveRatio = preserveRatio;
    }

    public void setFontSizeAndColour(float size, Color color){
        setFont(FontCreator.getFontAharoni(size));
        setForeground(color);
    }

    public void setImage(String path) {
        this.path = path;
        this.image = ImageCreator.getImageFromPath(path);
    }
    @Override
    public Dimension getPreferredSize() {
        if (preserveRatio){
            return new Dimension(image.getWidth(), image.getHeight());
        }
        return new Dimension(0,0);
    }

    @Override
    public void setBounds(int x, int y, int width, int height){
        super.setBounds(x,y,width,height);
        changeImageSize(width,height);
    }
    public void changeImageSize(int width, int height){
        if (width != 0 && height != 0 && path!=null) {
            if (preserveRatio) {
                double widthDifference = width * 1.0 / image.getWidth();
                double heightDifference = height * 1.0 / image.getHeight();
                changeSize((int) (image.getWidth() * Math.min(widthDifference, heightDifference)), (int) (image.getHeight() * Math.min(widthDifference, heightDifference)));
            } else {
                changeSize(width, height);
            }
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
        super.setPreferredSize(preferredSize);
    }
    public Dimension getImageSize(){
        return new Dimension(image.getWidth(), image.getHeight());
    }

    protected void changeSize(int width, int height){
        this.image =  ImageCreator.getAndScaleImage(path, width, height);
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
        super.paintComponent(g);

    }

}
