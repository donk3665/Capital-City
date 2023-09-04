package Entities.GUI.Screens.ScreenElements;

import Entities.ExternalDataTransfer.BasicPlayer;
import Entities.GUI.Screens.ContentPanelInterface;

import javax.swing.*;
import java.awt.*;

public class PlayerShower implements ContentPanelInterface {
    private JPanel contentPanel;
    private BasicPlayer player;

    public PlayerShower(BasicPlayer player){
        this.player = player;
        contentPanel = new JPanel();
        generateContent(player.getPlayerIndex());
    }

    private void generateContent(int index){

        ImageLabel label  = new ImageLabel("");
        label.setPreserveRatio(true);
        label.setImage("/Images/InGameAssets/Icons/Picture" + (index) + ".png");
        contentPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;

        contentPanel.add(label, constraints);
        constraints.weightx = 1;
        constraints.gridx = 1;
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        createText(panel);
        contentPanel.add(panel, constraints);
    }
    private void createText(JPanel panel){
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setRows(3);
        area.setFont(FontCreator.getFontAharoni(24));

        String text = "Player: " + player.getName() + "\nMoney: " + player.getMoney() + "\nProperty: ";
        for (int i = 0; i<player.getProperties().size(); i++){
            text = text.concat(player.getProperties().get(i).getName()  + "\n");
        }
        area.setText(text);
        panel.add(area);

    }
    @Override
    public JPanel getContentPanel() {
        return contentPanel;
    }
}
