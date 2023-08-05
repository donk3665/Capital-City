package Entities.GUI.Screens;

import Logic.NodeNames;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Class to keep track of the frame
 */
public class InitialGameScreen extends Screen{

    /**
     * Constructor that configures the JFrame
     *
     */
    public InitialGameScreen(NodeNames name) {
        super(name);
    }
    public void connectButtons(JPanel connect){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        //c.gridheight = 2;

        c.insets = new Insets(6,0,6,0);
        panel.setBorder(new EmptyBorder(200,200,200,200));
        panel.setOpaque(false);


        String [] paths = getImagePathFactory().getButtonPaths(getName());

        for (int i = 0; i < options.getButtons().size(); i++){
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BorderLayout());
            c.gridy =  3 + 2 * i;
            options.getButtons().get(i).setImage(paths[i]);
            buttonPanel.add(options.getButtons().get(i));
            panel.add(buttonPanel,c );

        }
        panel.validate();
        connect.add(panel, BorderLayout.CENTER);
    }

    // THIS METHOD MUST BE CALLED AFTER ALL BUTTONS AND ELEMENTS ARE ADDED
    @Override
    public void initDisplay() {

        gamePane.removeAll();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBackground(new Color(0,0,0,0));
        backgroundPanel.setBackgroundImage(getImagePathFactory().getBackgrounds(0));

        connectButtons(backgroundPanel);

        backgroundPanel.add(description.getDescription(), BorderLayout.PAGE_START);

        gamePane.setPreferredSize(new Dimension(1920, 1080));
        gamePane.add(backgroundPanel, JLayeredPane.FRAME_CONTENT_LAYER);
        gameFrame.setContentPane(gamePane);

        gameFrame.pack();

        backgroundPanel.setBounds(gamePane.getBounds());

        gameFrame.pack();

    }

}
