package Entities.GUI.Screens;

import Logic.NodeNames;

import javax.swing.*;
import java.awt.*;

public class CreditsScreen extends Screen{
    /**
     * Constructor that configures the JFrame
     *
     * @param name
     */


    public CreditsScreen(NodeNames name) {
        super(name);
    }

    @Override
    public void initDisplay() {
        backgroundPanel.removeAll();

        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));

        backgroundPanel.setBackgroundImage(getImagePathFactory().getBackgrounds(1));
        options.getButtons().get(0).setImage(getImagePathFactory().getBackButtonPath());
        backgroundPanel.add(options.getButtons().get(0), BorderLayout.PAGE_START);

        backgroundPanel.setOpaque(true);

        gameFrame.setContentPane(backgroundPanel);
        gameFrame.pack();

    }
}
