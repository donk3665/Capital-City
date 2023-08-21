package Entities.GUI.Screens;

import Entities.ExternalDataTransfer.BasicBoard;
import Entities.ExternalDataTransfer.BasicPlayer;
import Entities.Game.Cell;
import Entities.Game.CellEnum;
import Entities.Game.Property;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class GridOverlayCreator {

    private List<Cell> cells;
    public GridOverlayCreator(WeightlessPanel[] panels, BasicBoard board){
        cells = board.getCells();
        cornerSquareOverlay(panels[0], getPlayerTrackerTile(board,0));
        for (int i = 1; i<10; i++){
            rectangleTileOverlay(panels[i],getPlayerTrackerTile(board,i), cells.get(i), 0);
        }
        cornerSquareOverlay(panels[10], getPlayerTrackerTile(board,10));
        for (int i = 11; i<20; i++){
            rectangleTileOverlay(panels[i],getPlayerTrackerTile(board,i), cells.get(i), 1);
        }
        cornerSquareOverlay(panels[20], getPlayerTrackerTile(board,20));
        for (int i = 21; i <30; i++){
            rectangleTileOverlay(panels[i],getPlayerTrackerTile(board,i), cells.get(i), 2);
        }
        cornerSquareOverlay(panels[30], getPlayerTrackerTile(board,30));
        for (int i = 31; i <40; i++){
            rectangleTileOverlay(panels[i],getPlayerTrackerTile(board,i), cells.get(i), 3);
        }
    }
    private void rectangleTileOverlay(WeightlessPanel panel, boolean[] playersArray, Cell cell, int direction){
        panel.removeAll();
        panel.setLayout(new GridBagLayout());

        if (cell.getType() == CellEnum.PROPERTY){
            WeightlessPanel topPanel = new WeightlessPanel();
            topPanel.setBackground(MyColor.getColor(((Property)cell).getColour()));

            WeightlessPanel bottomPanel = new WeightlessPanel();

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;

            if (direction%2 == 0) {
                constraints.weighty = 0.2;
                constraints.weightx = 1;
                if (direction == 2){
                    constraints.gridy = 1;
                }
            }
            else{
                constraints.weighty = 1;
                constraints.weightx = 0.2;

                if (direction == 1){
                    constraints.gridx = 1;
                }
            }

            constraints.fill = GridBagConstraints.BOTH;

            panel.add(topPanel, constraints);
            drawHouses(topPanel, ((Property) cell).getHouses(), direction%2 == 0);


            constraints.weighty = 1;
            constraints.weightx = 1;
            if (direction==0){
                constraints.gridy = 1;
            }
            else if (direction==1){
                constraints.gridx = 0;
            }
            else if (direction==2){
                constraints.gridy = 0;
            }
            else{
                constraints.gridx = 1;
            }

            panel.add(bottomPanel, constraints);
            bottomPanel.setOpaque(false);
            drawBottomPart(bottomPanel, cell, direction, playersArray);
        }
        else{
            drawPropertyIcons(panel, direction%2 == 0, playersArray);
        }
        panel.validate();
    }
    private void drawBottomPart(WeightlessPanel panel, Cell cell, int direction, boolean[] playersArray){
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;

        if (direction%2 == 0){
            constraints.weighty = 0.5;
        }
        else {
            constraints.weighty = 0.4;
        }

        WeightlessPanel panel1 = new WeightlessPanel();
        panel1.setLayout(new BorderLayout());
        panel1.setBackground(Color.BLUE);
        panel.add(panel1, constraints);

        JTextArea area = new JTextArea();
        area.setLineWrap(true);
        area.setEditable(false);
        area.setWrapStyleWord(true);
        if (direction%2 == 0){
            area.setRows(2);
        }
        else{
            area.setRows(1);
        }
        area.setFont(FontCreator.getFontAharoni(12));
        area.setText(((Property) cell).getName());

        panel1.add(area);

        if (direction%2 == 0){
            constraints.weighty = 0.25;
        }
        else{
            constraints.weighty = 0.4;
        }
        WeightlessPanel panel2 = new WeightlessPanel();
        panel2.setLayout(new BorderLayout());
        constraints.gridy = 2;


        panel.add(panel2, constraints);

        JTextField area2 = new JTextField();
        area2.setHorizontalAlignment(SwingConstants.CENTER);
        area2.setBorder(new EmptyBorder(0,0,0,0));
        area2.setBackground(new Color(0,0,0,0)); ;
        area2.setEditable(false);
        area2.setFont(FontCreator.getFontAharoni(12));
        area2.setText(String.valueOf( ((Property) cell).getPrice()));

        panel2.add(area2);


        constraints.gridy = 1;
        constraints.weighty = 1;


        WeightlessPanel panel3 = new WeightlessPanel();
        panel3.setBackground(Color.WHITE);
        drawPropertyIcons(panel3, direction%2 == 0, playersArray);
        panel.add(panel3, constraints);

        panel.validate();

    }

    private void drawHouses(WeightlessPanel panel, int houses, boolean isCellVertical){
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        if (houses == 5){
            ImageLabel hotel = new ImageLabel("");
            hotel.setPreserveRatio(true);
            hotel.setImage("/Images/InGameAssets/PropertyIcons/Hotel.png");
            panel.add(hotel,constraints);
        }
        else {
            for (int i = 0; i < houses; i++) {
                if (isCellVertical) {
                    constraints.gridx = i;
                } else {
                    constraints.gridy = i;
                }
                ImageLabel house = new ImageLabel("");
                house.setImage("/Images/InGameAssets/PropertyIcons/House.png");
                house.setPreserveRatio(true);
                panel.add(house,constraints);
            }
        }
    }

    private void drawPropertyIcons(WeightlessPanel panel, boolean isCellVertical, boolean[] playersArray){
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;

        int columnCount = 2;
        if (isCellVertical){
            columnCount = 4;
        }
        drawIcons(constraints, columnCount, playersArray, panel);




    }
    private void drawIcons(GridBagConstraints constraints, int columnCount, boolean [] playersArray, WeightlessPanel panel ){
        for (int i = 0; i<playersArray.length; i++){
            if (playersArray[i]) {
                constraints.gridx = i % columnCount;
                constraints.gridy = i / columnCount;
                ImageLabel image = new ImageLabel("");
                image.setImage("/Images/InGameAssets/Icons/Picture" + (i) + ".png");
                image.setPreserveRatio(true);
                panel.add(image, constraints);
            }
        }
    }



    private boolean[] getPlayerTrackerTile(BasicBoard board, int tile){
        List<BasicPlayer> players = board.getBasicPlayers();
        boolean [] tracker = new boolean[players.size()];
        for (int i = 0; i< players.size(); i++){
            tracker[i] = players.get(i).getPosition() == tile;
        }
        return tracker;
    }

    private void cornerSquareOverlay(WeightlessPanel panel, boolean[] playersArray){
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(5,5,5,5));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;

        drawIcons(constraints, 3, playersArray, panel);
    }
}
