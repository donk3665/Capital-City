package Entities.GUI.Screens.ScreenElements;

import javax.swing.*;
import java.awt.*;

public class BoardGrid {

    private WeightlessPanel[] panelGrid;
    private WeightlessPanel[] innerPanelGrid;
    private ImageButton[] buttonGrid;
    private ImageButton[] innerButtonGrid;
    private JPanel centerPanel = new JPanel();
    private JPanel masterPanel;
    private JLayeredPane boardPane;
    private boolean isLabel;

    public WeightlessPanel[] getPanelGrid() {
        return panelGrid;
    }
    public ImageButton[] getButtonGrid() {
        return buttonGrid;
    }

    public BoardGrid(JPanel masterPanel, boolean isLabel, JLayeredPane boardPane){

        this.isLabel = isLabel;
        this.boardPane = boardPane;
        this.masterPanel = masterPanel;
        centerPanel.setOpaque(false);

        centerPanel.setLayout(new GridBagLayout());
        //System.err.println(masterPanel.getBounds());
        if (isLabel) {
            panelGrid = new WeightlessPanel[40];
            innerPanelGrid = new WeightlessPanel[4];
            for (int i = 0; i< panelGrid.length; i++){
                panelGrid[i] = new WeightlessPanel();
                panelGrid[i].setOpaque(false);
                if (i< innerPanelGrid.length){
                    innerPanelGrid[i] = new WeightlessPanel();
                    innerPanelGrid[i].setOpaque(false);
                }
            }
            componentGenerateGrid(panelGrid);
            componentGenerateInterior(innerPanelGrid);
        }
        else {
            buttonGrid = new ImageButton[40];
            innerButtonGrid = new ImageButton[40];
            for (int i = 0; i<buttonGrid.length; i++){
                buttonGrid[i] = new ImageButton("");
                buttonGrid[i].setContentAreaFilled(false);
                buttonGrid[i].setFocusPainted(false);
                if (i< innerButtonGrid.length){
                    innerButtonGrid[i] = new ImageButton("");
                    innerButtonGrid[i].setContentAreaFilled(false);
                    innerButtonGrid[i].setFocusPainted(false);
                }
            }

            componentGenerateGrid(buttonGrid);
            componentGenerateInterior(innerButtonGrid);
        }
    }
    private void componentGenerateGrid(Component[] array){
        int counter = 0;
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        // Middle Pane
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 9;
        c.weighty = 9;
        c.gridwidth = 9;
        c.gridheight = 9;
        masterPanel.add(centerPanel, c);

        //Bottom-right corner
        c.weightx = 2;
        c.weighty = 2;
        c.gridx = 11;
        c.gridy = 11;
        c.gridwidth = 2;
        c.gridheight = 2;
        masterPanel.add(array[counter], c);
        counter += 1;


        //Bottom straight
        c.weightx = 1;
        c.gridwidth = 1;
        for (int i = 0; i<9; i++){
            c.gridx = c.gridx - 1;
            masterPanel.add(array[counter], c);
            counter += 1;
        }

        //Bottom-left corner
        c.gridx = 0;
        c.gridy = 11;
        c.weightx = 2;
        c.gridwidth = 2;
        c.gridheight = 2;
        masterPanel.add(array[counter], c);
        counter += 1;

        //Left straight
        c.weighty = 1;
        c.gridheight = 1;
        for (int i = 0; i<9; i++){
            c.gridy = c.gridy - 1;
            masterPanel.add(array[counter], c);
            counter += 1;
        }

        //Top-left corner
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 2;
        c.gridwidth = 2;
        c.gridheight = 2;
        masterPanel.add(array[counter], c);
        counter += 1;

        //Top straight
        c.gridx = 1;
        c.weightx = 1;
        c.gridwidth = 1;
        for (int i = 0; i<9; i++){
            c.gridx = c.gridx + 1;
            masterPanel.add(array[counter], c);
            counter += 1;
        }

        //Top-right corner
        c.gridx = 11;
        c.gridy = 0;
        c.weightx = 2;
        c.gridwidth = 2;
        c.gridheight = 2;
        masterPanel.add(array[counter], c);
        counter += 1;

        //Right straight
        c.weighty = 1;
        c.gridheight = 1;
        c.gridy = 1;
        for (int i = 0; i<9; i++){
            c.gridy = c.gridy + 1;
            masterPanel.add(array[counter], c);
            counter += 1;
        }

        masterPanel.validate();
    }
    private void componentGenerateInterior(Component[] array){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 3;
        c.weighty = 2;
        c.gridwidth = 3;
        c.gridheight = 2;

        // Top-left
        c.gridx = 1;
        c.gridy = 1;
        centerPanel.add(array[0], c);

        // Top-right
        c.gridx = 5;
        c.gridy = 1;
        //centerPanel.add(array[1], c);
        centerPanel.add(new ImageLabel(""));

        //Bottom-left
        c.gridx = 1;
        c.gridy = 6;
//        centerPanel.add(array[2], c);
        centerPanel.add(new ImageLabel(""));

        //Bottom-Right
        c.gridx = 5;
        c.gridy = 6;
        centerPanel.add(array[3], c);

        //Filler right
        ImageLabel tempPanel = new ImageLabel("");
        c.gridx = 8;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 9;
        c.gridwidth = 1;
        c.gridheight = 9;

        centerPanel.add(tempPanel, c);

        //Filler bottom
        ImageLabel tempPanel2 = new ImageLabel("");
        c.gridx = 0;
        c.gridy = 8;
        c.weightx = 8;
        c.weighty = 1;
        c.gridwidth = 8;
        c.gridheight = 1;

        centerPanel.add(tempPanel2, c);

        //Filler top
        ImageLabel tempPanel3 = new ImageLabel("");
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 8;
        c.weighty = 1;
        c.gridwidth = 8;
        c.gridheight = 1;

        centerPanel.add(tempPanel3, c);

        //Filler left
        ImageLabel tempPanel4 = new ImageLabel("");
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 7;
        c.gridwidth = 1;
        c.gridheight = 7;

        centerPanel.add(tempPanel4, c);

        //Filler middle
        ImageLabel tempPanel5 = new ImageLabel("");
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 7;
        c.weighty = 3;
        c.gridwidth = 7;
        c.gridheight = 3;

        centerPanel.add(tempPanel5, c);

        //Filler middle
        ImageLabel tempPanel6 = new ImageLabel("");
        c.gridx = 4;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 2;
        c.gridwidth = 1;
        c.gridheight = 2;

        centerPanel.add(tempPanel6, c);

        //Filler middle
        ImageLabel tempPanel7 = new ImageLabel("");
        c.gridx = 4;
        c.gridy = 6;
        c.weightx = 1;
        c.weighty = 2;
        c.gridwidth = 1;
        c.gridheight = 2;

        centerPanel.add(tempPanel7, c);

        centerPanel.validate();


    }

}
