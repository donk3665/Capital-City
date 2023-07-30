package Entities.GUI.Screens;

import Logic.NodeNames;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Class to keep track of the frame
 */
public class GameScreen extends Screen{
     Rectangle descriptionPosition = new Rectangle(825, 125, 250, 225);
    BackgroundPanel panel = new BackgroundPanel();
    /**
     * Constructor that configures the JFrame
     *
     */
    public GameScreen(NodeNames name) {
        super(name);
    }
    public void connectButtons(JPanel connect){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.1;

        c.insets = new Insets(6,200,6,200);
        panel.setOpaque(false );

        String [] paths = getImagePathFactory().getButtonPaths(getName());
        for (int i = 0; i < options.getButtons().size(); i++){
            c.gridy =  3 + 2 * i;
            options.getButtons().get(i).setImage(paths[i]);
            panel.add(options.getButtons().get(i),c );
            addRemovableComponent(panel, BorderLayout.CENTER, connect);
        }
    }

    // THIS METHOD MUST BE CALLED AFTER ALL BUTTONS AND ELEMENTS ARE ADDED
    @Override
    public void initDisplay() {
        removeComponents(panel);


        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0,0,0,0));
        panel.setBackgroundImage("/backgrounds/bg1.jpg");

        connectButtons(panel);

        description.getDescription().setBounds(descriptionPosition);
        addRemovableComponent(description.getDescription(), BorderLayout.PAGE_START, panel);

        panel.setOpaque(true);
        gameFrame.setContentPane(panel);
        gameFrame.pack();

    }

}
