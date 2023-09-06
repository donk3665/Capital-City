package GUI.Screens;

import GUI.Screens.ScreenElements.BackgroundPanel;

import javax.swing.*;
import java.awt.*;

public class CreditsScreen extends Screen{
    /**
     * Constructor that configures the JFrame
     *
     */
    private BackgroundPanel mainPanel = new BackgroundPanel();


    public CreditsScreen() {

    }


    @Override
    public void attachNonStaticComponents() {
        mainPanel.removeAll();
        options.getButtons().get(0).setImage(getImagePathFactory().getBackButtonPath());
        mainPanel.add(options.getButtons().get(0), BorderLayout.PAGE_START);

    }

    @Override
    public void setUpGamePane() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackgroundImage(getImagePathFactory().getBackgrounds(1));
        mainPanel.setOpaque(true);

        gamePane.add(mainPanel, JLayeredPane.FRAME_CONTENT_LAYER);
        mainPanel.setBounds(gamePane.getBounds());

    }

    @Override
    public void handleAsynchronousInput(String input) {

    }
}
