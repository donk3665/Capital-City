package Entities.GUI.Screens.ScreenElements;

import Entities.GUI.Screens.ContentPanelInterface;
import Entities.Game.Cell;
import Entities.Game.CellEnum;
import Entities.Game.Property;

import javax.swing.*;
import java.awt.*;

public class CardShower implements ContentPanelInterface {
    private JPanel contentPanel;

    private static CardPathFactory factory = new CardPathFactory();

    private Cell currentCell;
    private int numPlayersTile;

    public CardShower(int tile, Cell currentCell, int numPlayersTile){
        this.currentCell = currentCell;
        this.numPlayersTile = numPlayersTile;
        contentPanel = new JPanel();
        generateContent(factory.getCard(tile));
    }
    private void generateContent(String path){

        ImageLabel label  = new ImageLabel("");
        label.setPreserveRatio(true);
        label.setImage(path);
        contentPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;

        contentPanel.add(label, constraints);
        constraints.weightx = 0.45;
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
        switch (currentCell.getType()){
            case PROPERTY ->{
                Property property =  (Property) currentCell;
                String propertyOwner = "No one";
                if (property.getOwner()!= null){
                    propertyOwner = property.getOwner().getName();
                }
                if (((Property) currentCell).getSpecialType() == CellEnum.RAILROAD){
                    area.setText("Owner: " + propertyOwner + "\nOther Railroads: " + property.getHouses() + "\nPlayers on: " + numPlayersTile + "\nMortgaged?: "+ property.getMortgageStatus());
                }
                else if (((Property) currentCell).getSpecialType() == CellEnum.UTILITY){
                    area.setText("Owner: " + propertyOwner + "\nOther Utility: " + property.getHouses() + "\nPlayers on: " + numPlayersTile + "\nMortgaged?: "+ property.getMortgageStatus());
                }
                else {
                    area.setText("Owner: " + propertyOwner + "\nHouses: " + property.getHouses() + "\nPlayers on: " + numPlayersTile + "\nMortgaged?: "+ property.getMortgageStatus());
                }
            }
            case CORNER_TILE, ACTION_SPACE -> {
                area.setText("Players on: " + numPlayersTile);
            }
        }
        panel.add(area);

    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
