package Entities.GUI.Screens;

import Entities.GUI.Screens.Functions.BoardGrid;
import Logic.NodeNames;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class GameScreen extends Screen{
    /**
     * Constructor that configures the JFrame
     *
     * @param name
     */
    String[] gameImages = getImagePathFactory().getUIImagePaths();
    private int renderMode = 0;

    JLayeredPane optionBox = null;
    JPanel optionPanel = null;
    public void setRenderMode(int renderMode){
        this.renderMode = renderMode;
    }
    public GameScreen(NodeNames name) {
        super(name);
    }
    public void setName(NodeNames name){
        this.name = name;
    }
    public void connectButtons(JLayeredPane connect){
        if (optionPanel != null) {
            connect.remove(optionPanel);
        }
        optionPanel = new JPanel();
        optionPanel.setBounds(connect.getBounds());
        optionPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.weightx = 1;
        c.ipady = 24;
        optionPanel.setBorder(new EmptyBorder(30,40,30,40));

        c.insets = new Insets(6, 6,6,6);
        optionPanel.setOpaque(false);

        String [] paths = getImagePathFactory().getButtonPaths(getName());

        JPanel[] panelList = new JPanel[options.getButtons().size()];
        for (int i = 0; i < options.getButtons().size(); i++){
            panelList[i] = new JPanel();
            panelList[i].setLayout(new BorderLayout());
            c.gridy = i%4;
            c.gridx =  i/4;
            options.getButtons().get(i).setImage(paths[i]);
            panelList[i].add(options.getButtons().get(i));
            options.getButtons().get(i).setFontSizeAndColour(24, Color.white);
            optionPanel.add(panelList[i],c );
        }
        optionPanel.validate();
//        for (JPanel i: panelList){
//            i.validate();
//            System.err.println(i.getBounds());
//        }
        connect.add(optionPanel);
    }

    public JPanel addFirstLayer(BackgroundPanel backgroundPanel){

        JPanel infoLabelPanel = new JPanel();
        infoLabelPanel.setOpaque(false);
        infoLabelPanel.setLayout(new BorderLayout());
        ImageLabel infoLabel = new ImageLabel("Current Player");
        infoLabel.setImage(gameImages[3]);
        infoLabel.setFontSizeAndColour(24, Color.white);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0,0,50,100);
        c.weightx = 0.5;
        c.weighty = 0.2;

        backgroundPanel.add(infoLabelPanel, c);
        infoLabelPanel.add(infoLabel);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;

        JPanel descriptionLabelPanel = new JPanel();
        descriptionLabelPanel.setOpaque(false);
        descriptionLabelPanel.setLayout(new BorderLayout());

        ImageLabel descriptionLabel = new ImageLabel(description.getDescription().getText());
        descriptionLabel.setImage(gameImages[4]);

        backgroundPanel.add(descriptionLabelPanel, c);
        descriptionLabelPanel.add(descriptionLabel);

        descriptionLabel.setFontSizeAndColour(24, Color.white);

        descriptionLabelPanel.validate();


        c.gridx = 2;
        c.weightx = 0.2;

        JPanel settingsButtonPanel = new JPanel();
        settingsButtonPanel.setOpaque(false);
        settingsButtonPanel.setLayout(new BorderLayout());
        ImageButton settingsButton = new ImageButton("");
        settingsButton.setImage(gameImages[5]);
        c.insets = new Insets(0,0,50,0);

        backgroundPanel.add(settingsButtonPanel, c);
        settingsButtonPanel.add(settingsButton);

        c.insets = new Insets(0,0,0,0);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.weighty = 1;
        c.weightx = 1;

        JPanel bottomHalf = new JPanel();
        bottomHalf.setOpaque(false);
        bottomHalf.setLayout(new BorderLayout());

        JPanel actualBottomHalf = new JPanel();
        actualBottomHalf.setOpaque(false);
        actualBottomHalf.setLayout(new GridBagLayout());

        backgroundPanel.add(bottomHalf, c);
        bottomHalf.add(actualBottomHalf);

        backgroundPanel.validate();
        return actualBottomHalf;
    }

    private JLayeredPane addLayeredPaneAndBackground(JPanel panel, String imagePath){
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(panel.getBounds());
        //addResizeListener(layeredPane);
        JPanel jPanel = new JPanel();
        jPanel.setOpaque(false);
        jPanel.setLayout(new BorderLayout());
        layeredPane.add(jPanel, JLayeredPane.FRAME_CONTENT_LAYER);
        panel.add(layeredPane);
        ImageLabel image = new ImageLabel("");
        image.setImage(imagePath);
        jPanel.add(image);


        jPanel.setBounds(layeredPane.getBounds());
        jPanel.validate();

        return layeredPane;
    }

    public void addBoard(JPanel panel){
        JLayeredPane board = addLayeredPaneAndBackground(panel, gameImages[0]);
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridBagLayout());
        gridPanel.setBounds(board.getBounds());
        board.add(gridPanel, JLayeredPane.PALETTE_LAYER);
        gridPanel.setOpaque(false);
        BoardGrid grid = new BoardGrid(gridPanel, false, board);

        //generateBoard(gridPanel);
    }
    public void generateBoard(JPanel panel){
        JButton [] tileArray;


    }

    public void addOptionChatBox(JPanel panel){

        GridBagConstraints bottomRightConstraints = new GridBagConstraints();
        bottomRightConstraints.gridx = 0;
        bottomRightConstraints.gridy = 0;
        bottomRightConstraints.weighty = 1;
        bottomRightConstraints.weightx = 1;
        bottomRightConstraints.insets = new Insets(0,0,60,0);
        bottomRightConstraints.fill = GridBagConstraints.BOTH;

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setOpaque(false);
        panel.add(topPanel, bottomRightConstraints);

        bottomRightConstraints.gridy = 1;
        bottomRightConstraints.weighty = 0.7;
        bottomRightConstraints.insets = new Insets(0,0,20,0);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setOpaque(false);
        panel.add(bottomPanel, bottomRightConstraints);

        panel.validate();

        addOptionBox(topPanel);

        addChatBox(bottomPanel);

    }
    public void addChatBox(JPanel panel){
        panel.validate();

        JPanel infoLabelPanel = new JPanel();
        infoLabelPanel.setOpaque(false);
        infoLabelPanel.setLayout(new BorderLayout());
        ImageLabel infoLabel = new ImageLabel("");
        infoLabel.setImage(gameImages[1]);

        infoLabelPanel.add(infoLabel);
        panel.add(infoLabelPanel);

    }
    public void addOptionBox(JPanel panel){
        optionBox = addLayeredPaneAndBackground(panel, gameImages[2]);
        connectButtons(optionBox);
    }



    @Override
    public void initDisplay() {
  //      if (renderMode == 0) {
            gamePane.setDoubleBuffered(true);
            gamePane.removeAll();
            backgroundPanel.removeAll();
            backgroundPanel.setLayout(new GridBagLayout());
            backgroundPanel.setBackgroundImage(getImagePathFactory().getBackgrounds(2));
            gamePane.add(backgroundPanel, JLayeredPane.FRAME_CONTENT_LAYER);
            gameFrame.setContentPane(gamePane);

            backgroundPanel.setBounds(gamePane.getBounds());

            JPanel boardOptionPanel = addFirstLayer(backgroundPanel);

            backgroundPanel.validate();

            GridBagConstraints bottomHalfConstraints = new GridBagConstraints();
            bottomHalfConstraints.gridx = 0;
            bottomHalfConstraints.gridy = 0;
            bottomHalfConstraints.weighty = 1;
            bottomHalfConstraints.weightx = 1;
            bottomHalfConstraints.insets = new Insets(0, 0, 0, 10);
            bottomHalfConstraints.fill = GridBagConstraints.BOTH;

            JPanel leftPanel = new JPanel();
            leftPanel.setOpaque(false);
            leftPanel.setLayout(new BorderLayout());

            boardOptionPanel.add(leftPanel, bottomHalfConstraints);

            bottomHalfConstraints.gridx = 1;
            bottomHalfConstraints.weightx = 0.7;
            bottomHalfConstraints.insets = new Insets(0, 0, 0, 0);
            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new GridBagLayout());
            rightPanel.setOpaque(false);
            boardOptionPanel.add(rightPanel, bottomHalfConstraints);

            boardOptionPanel.validate();

            addBoard(leftPanel);

            addOptionChatBox(rightPanel);

            //gameFrame.pack();


    }
}
