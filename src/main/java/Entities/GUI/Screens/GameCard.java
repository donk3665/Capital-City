package Entities.GUI.Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameCard extends JLabel {
    private final String header;
    private String description;

    public GameCard(String header, float fontSize){
        this.header = header;
        setFont(FontCreator.getFontAharoni(fontSize));
        setForeground(Color.YELLOW);
    }

    @Override
    public void setBounds(Rectangle dimension){
        super.setBounds(dimension);
        BufferedImage image = ImageCreator.getImageFromPath("/Images/card.jpg");
        setIcon(new ImageIcon(ImageCreator.scaleImage(image, (int) dimension.getWidth(), (int) dimension.getHeight())));
    }


    public void setDescription(String description){
        this.description = description;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(header, getHorizontalAlignment(), getHeight()/3);
        g.drawString(description, getWidth()/2, getHeight()/2);
    }

}
