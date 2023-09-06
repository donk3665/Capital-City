package GUI.SettingsController;

import GUI.Screens.ScreenElements.ImageButton;
import GUI.Screens.ScreenElements.OptionPanel;
import GUI.Screens.ScreenElements.Popup;
import GUI.Screens.Screen;
import GUI.Screens.ScreenElements.SliderPanel;
import GUI.SettingsBranch.GUINodeInterface;
import GUI.SettingsBranch.SettingsMenuUseCase;
import GUI.SettingsBranch.SoundGameUseCase;

import javax.swing.*;
import java.util.List;


public class SettingsFunction {

    private final JPanel panel;
    private int settingsCounter;

    private GUINodeInterface guiNode;

    private Screen currentScreen;

    public SettingsFunction(JPanel panel, Screen currentScreen){
        this.panel = panel;
        this.currentScreen = currentScreen;
        this.settingsCounter = 0;
        guiNode = new SettingsMenuUseCase(currentScreen);
    }
    public void createPopup(String choice){
        panel.removeAll();
        if (choice != null){
            guiNode = guiNode.performInput(choice);
        }
        if (guiNode == null){
        panel.removeAll();
        guiNode = new SettingsMenuUseCase(currentScreen);
        panel.getParent().repaint();
        return;
        }
        OptionPanel optionPanel = new OptionPanel(List.of(guiNode.getOptions()));
        JPanel contentPanel = optionPanel.getContentPanel();

        if (guiNode instanceof SoundGameUseCase){
            SliderPanel sliderPanel = new SliderPanel(List.of("Sound effects", "Music"), List.of(guiNode.getOptions()));
            contentPanel = sliderPanel.getContentPanel();
            for (ImageButton button: sliderPanel.getButtonArray()){
                button.addActionListener(e->{
                    createPopup(button.getText());
                });
            }
            sliderPanel.getSliderArray()[0].setValue((int) (Screen.getSoundController().getEffectVolume()*100));
            sliderPanel.getSliderArray()[1].setValue((int) (Screen.getSoundController().getMusicVolume()*100));
            sliderPanel.getSliderArray()[0].addChangeListener(e ->{
                Screen.getSoundController().changeEffectVolume(sliderPanel.getSliderArray()[0].getValue()/100.0);
            });
            sliderPanel.getSliderArray()[1].addChangeListener(e ->{
                Screen.getSoundController().changeMusicVolume(sliderPanel.getSliderArray()[1].getValue()/100.0);
            });
        }


        GUI.Screens.ScreenElements.Popup popup = new Popup(panel, contentPanel,false);

        for (ImageButton button: optionPanel.getButtonList()){
            button.addActionListener(e->{
                Screen.getSoundController().playClip(0);
                createPopup(button.getText());
            });
        }

    }
}
