package Entities.GUI.Screens;

import Entities.GUI.Screens.ScreenElements.BackgroundPanel;
import Entities.GUI.Screens.ScreenElements.SliderPanel;
import Logic.NodeNames;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Collections;
import java.util.List;

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
        panel.setBorder(new EmptyBorder(200, 200, 200, 200));
        panel.setOpaque(false);
        String[] paths = getImagePathFactory().getButtonPaths();
        if (getState().getId() == NodeNames.SOUND_INITIAL){
            panel.setLayout(new BorderLayout());

            SliderPanel sliderPanel = new SliderPanel(List.of("Sound effects", "Music"), Collections.emptyList());


            options.getButtons().get(0).setImage(paths[0]);
            sliderPanel.addComponent(options.getButtons().get(0));

            JPanel contentPanel = sliderPanel.getContentPanel();

            sliderPanel.getSliderArray()[0].setValue((int) (Screen.getSoundController().getEffectVolume()*100));
            sliderPanel.getSliderArray()[1].setValue((int) (Screen.getSoundController().getMusicVolume()*100));
            sliderPanel.getSliderArray()[0].addChangeListener(e ->{
                Screen.getSoundController().changeEffectVolume(sliderPanel.getSliderArray()[0].getValue()/100.0);
            });
            sliderPanel.getSliderArray()[1].addChangeListener(e ->{
                Screen.getSoundController().changeMusicVolume(sliderPanel.getSliderArray()[1].getValue()/100.0);
            });
            panel.add(contentPanel);
        }
        else {
            panel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1;
            c.weighty = 1;

            c.insets = new Insets(6, 0, 6, 0);

            for (int i = 0; i < options.getButtons().size(); i++) {
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new BorderLayout());
                c.gridy = 3 + 2 * i;
                options.getButtons().get(i).setImage(paths[i]);
                buttonPanel.add(options.getButtons().get(i));
                panel.add(buttonPanel, c);

            }

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

        musicController.playMenuMusic();
    }

}
