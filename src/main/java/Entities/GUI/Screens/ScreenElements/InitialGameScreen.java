package Entities.GUI.Screens.ScreenElements;

import Entities.GUI.Screens.Screen;
import Entities.GUI.Screens.ScreenElements.BackgroundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Class to keep track of the frame
 */
public class InitialGameScreen extends Screen {


    private BackgroundPanel mainPanel = new BackgroundPanel();
    /**
     * Constructor that configures the JFrame
     *
     */
    public InitialGameScreen() {
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


        String [] paths = getImagePathFactory().getButtonPaths();

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


    public void setUpGamePane(){
        mainPanel.setLayout(new BorderLayout());

        mainPanel.setBackground(new Color(0,0,0,0));
        mainPanel.setBackgroundImage(getImagePathFactory().getBackgrounds(0));

        gamePane.add(mainPanel, JLayeredPane.FRAME_CONTENT_LAYER);
        mainPanel.setBounds(gamePane.getBounds());

    }

    @Override
    public void handleAsynchronousInput(String input) {

    }

    @Override
    public void attachNonStaticComponents() {
        mainPanel.removeAll();

        mainPanel.add(description.getDescription(), BorderLayout.PAGE_START);
        connectButtons(mainPanel);

        mainPanel.validate();
        mainPanel.getParent().repaint();


    }

}
