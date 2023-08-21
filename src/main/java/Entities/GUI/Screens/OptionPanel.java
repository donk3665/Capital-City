package Entities.GUI.Screens;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OptionPanel implements ContentPanelInterface{

    private final JPanel contentPanel;

    private final ArrayList<ImageButton> buttonList;


    public OptionPanel(List<String> options){
        contentPanel = new WeightlessPanel();
        contentPanel.setLayout(new GridBagLayout());

        buttonList = new ArrayList<>();
        ImagePathFactory factory = new ImagePathFactory();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;

        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;


        String [] paths = factory.getButtonPaths();
        for (int i = 0; i<options.size(); i++){
            constraints.gridy = i;
            ImageButton button = new ImageButton(options.get(i));
            button.setImage(paths[i]);
            buttonList.add(button);
            contentPanel.add(button, constraints);
        }
    }
    public ArrayList<ImageButton> getButtonList() {
        return buttonList;
    }
    @Override
    public JPanel getContentPanel() {
        return contentPanel;
    }
}
