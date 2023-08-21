package Entities.GUI.SettingsController;

import Entities.GUI.Screens.ImageButton;
import Entities.GUI.Screens.OptionPanel;
import Entities.GUI.Screens.Popup;
import Entities.GUI.Screens.Screen;
import Entities.GUI.SettingsBranch.GUINodeInterface;
import Entities.GUI.SettingsBranch.SettingsMenuUseCase;

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
        Popup popup = new Popup(panel, optionPanel.getContentPanel(),false);

        for (ImageButton button: optionPanel.getButtonList()){
            button.addActionListener(e->{
                createPopup(button.getText());
            });
        }

    }
}
