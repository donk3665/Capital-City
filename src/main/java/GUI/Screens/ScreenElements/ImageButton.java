package GUI.Screens.ScreenElements;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageButton extends JButton {
    private BufferedImage image;
    private String path;
    private String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setSuperPreservedSize(boolean superPreservedSize) {
        this.superPreservedSize = superPreservedSize;
    }

    private boolean superPreservedSize = false;

    public ImageButton(String label){
        super(label);
        setFont(FontCreator.getFontAharoni(60f));
        setForeground(Color.WHITE);
    }

    public void setFontSizeAndColour(float size, Color color){
        setFont(FontCreator.getFontAharoni(size));
        setForeground(color);
    }

    public void setImage(String path) {
        this.path = path;
        this.image = ImageCreator.getImageFromPath(path);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }
    @Override
    public Dimension getPreferredSize() {
        if (superPreservedSize){
            return super.getPreferredSize();
        }
        return new Dimension(0,0);
    }
    @Override
    public void setBounds(int x, int y, int width, int height){
        super.setBounds(x,y,width,height);
        if (width != 0 && height != 0 && path!=null) {
            changeSize(width, height);
        }
    }
    private void changeSize(int width, int height){
        this.image =  ImageCreator.getAndScaleImage(path, width, height);
    }


    @Override
    protected void paintComponent(Graphics g) {
        //if (image != null)
        g.drawImage(image, (int) getAlignmentX(), (int)getAlignmentY(), this);
        super.paintComponent(g);
    }

}
