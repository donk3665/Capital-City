package GUI.Screens.ScreenElements;

import javax.swing.*;
import java.awt.*;

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
        setIcon(new ImageIcon(ImageCreator.getAndScaleImage("/Images/card.jpg", (int) dimension.getWidth(), (int) dimension.getHeight())));
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
