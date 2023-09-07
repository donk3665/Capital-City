package GUI.Screens;

import GUI.Screens.ScreenElements.Popup;
import GUI.Screens.ScreenElements.*;
import Logic.NodeNames;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Collections;

public class ConnectionScreen extends Screen{
    /**
     * Constructor that configures the JFrame
     *
     */
    String[] gameImages = getImagePathFactory().getConnectionImagePaths();
    JLayeredPane optionBox = null;
    JPanel optionPanel = null;
    JLayeredPane chatBox = null;
    JPanel [] iconBackgroundPanelArray;


    JPanel [] iconPanelArray;

    static ChatBox connectionChatBox;

    JPanel startPanel;

    JPanel backButtonPanel;

    ImageButton exitButton;

    JTextArea textArea = new JTextArea();

    public void setName(NodeNames name){
        this.name = name;
    }
    public ConnectionScreen() {

    }


    @Override
    public void attachNonStaticComponents() {
        addOptionBox(startPanel);
        gameFrame.getContentPane().validate();
        setRecentChatBox(connectionChatBox);
    }

    @Override
    public void setUpGamePane() {
        BackgroundPanel mainPanel = new BackgroundPanel();
        mainPanel.setBackgroundImage(getImagePathFactory().getBackgrounds(5));
        gamePane.add(mainPanel, JLayeredPane.FRAME_CONTENT_LAYER);
        mainPanel.setBounds(0,0, (int) Screen.width, (int) Screen.height);


        JPanel bottomHalf = addFirstLayer(mainPanel);
        bottomHalf.setLayout(new GridBagLayout());

        GridBagConstraints bottomHalfConstraints = new GridBagConstraints();
        bottomHalfConstraints.gridx = 0;
        bottomHalfConstraints.gridy = 0;
        bottomHalfConstraints.weighty = 0.6;
        bottomHalfConstraints.weightx = 1;
        bottomHalfConstraints.insets = new Insets(0, 0, 0, 10);
        bottomHalfConstraints.fill = GridBagConstraints.BOTH;

        JPanel middlePanel = new WeightlessPanel();
        middlePanel.setOpaque(false);
        middlePanel.setLayout(new BorderLayout());

        bottomHalf.add(middlePanel, bottomHalfConstraints);

        bottomHalfConstraints.weighty = 1;
        bottomHalfConstraints.gridy = 2;
        JPanel bottomPanel = new WeightlessPanel();
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setOpaque(false);
        bottomHalf.add(bottomPanel, bottomHalfConstraints);

        bottomHalf.doLayout();

        addPlayers(middlePanel);
        addChatAndBox(bottomPanel);
    }

    @Override
    public void handleAsynchronousInput(String input) {
        String [] splitMessage = input.split("\s+");
        switch (splitMessage[0]){
            case "UNLOCK_CHAT" ->{
                connectionChatBox.unlockField();
                connectionChatBox.addMultiplayerSupport(getListener());
                addID(splitMessage[1]);
            }
            case "CURRENT_LOBBY" -> {
                addIcons(Integer.parseInt(splitMessage[1]));
            }
            case "NOT_READY" ->{
                addNotReadyPopup();
            }
        }
    }
    public void addNotReadyPopup(){
        JPanel popupPanel = new WeightlessPanel();
        popupPanel.setBounds(gamePane.getWidth()/4, gamePane.getHeight()/4, gamePane.getWidth()/2, gamePane.getHeight()/2);
        popupPanel.setOpaque(false);
        gamePane.add(popupPanel, JLayeredPane.POPUP_LAYER);

        GUI.Screens.ScreenElements.Popup popup = new Popup(popupPanel,new OptionPanel(Collections.emptyList(), "Not enough people in lobby").getContentPanel(),true);

    }
    public void addID(String id){
        textArea.setText("ID: " + id);
    }
    public void addIcons(int number){
        for (int i = 0; i< iconPanelArray.length; i++){
            iconPanelArray[i].removeAll();
            if (i<number){
                ImageLabel icon = new ImageLabel("");
                icon.setPreserveRatio(true);
                icon.setImage(ImagePathFactory.getPlayerIconPath(i));
                iconPanelArray[i].add(icon);
                iconPanelArray[i].validate();
            }
        }
    }

    public void addChatAndBox(JPanel panel){

        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.BOTH;

        startPanel = new WeightlessPanel();
        startPanel.setOpaque(false);

        panel.add(startPanel,constraints);

        constraints.gridx = 0;
        constraints.weightx = 1;

        WeightlessPanel panel2 = new WeightlessPanel();
        panel.add(panel2,constraints);

        panel.doLayout();

        addChatBox(panel2);
    }


    public void addPlayers(JPanel panel){
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.insets = new Insets(0,10,0,10);

        iconBackgroundPanelArray = new JPanel[8];
        iconPanelArray = new JPanel[8];

        for (int i = 0; i<8; i++){
            constraints.gridx = i;

            constraints.gridy = 1;
            constraints.weighty = 1;
            JLayeredPane layeredPane = new JLayeredPane();

            iconBackgroundPanelArray[i] = new WeightlessPanel();
            iconBackgroundPanelArray[i].setLayout(new BorderLayout());
            iconBackgroundPanelArray[i].setOpaque(false);
            layeredPane.add(iconBackgroundPanelArray[i], JLayeredPane.FRAME_CONTENT_LAYER);

            iconPanelArray[i] = new WeightlessPanel();
            iconPanelArray[i].setOpaque(false);
            iconPanelArray[i].setLayout(new BorderLayout());
            layeredPane.add(iconPanelArray[i], JLayeredPane.DEFAULT_LAYER);



            panel.add(layeredPane, constraints);

            ImageLabel backgroundImage = new ImageLabel("");
            backgroundImage.setImage(gameImages[0]);

            iconBackgroundPanelArray[i].add(backgroundImage);
        }
        panel.doLayout();

        for (int i = 0; i<iconPanelArray.length; i++){
            iconBackgroundPanelArray[i].setBounds(0,0, iconBackgroundPanelArray[i].getParent().getWidth(), iconBackgroundPanelArray[i].getParent().getHeight());
            iconPanelArray[i].setBounds(0,0,iconPanelArray[i].getParent().getWidth(), iconPanelArray[i].getParent().getHeight());
            iconPanelArray[i].setBorder(new EmptyBorder(40,40,40,40));
        }

    }


    public void connectButtonsHost(JLayeredPane layeredPanel){
        if (optionPanel != null) {
            layeredPanel.remove(optionPanel);
        }
        optionPanel = new WeightlessPanel();
        optionPanel.setOpaque(false);
        optionPanel.setBounds(0,0,layeredPanel.getWidth(), layeredPanel.getHeight());
        optionPanel.setLayout(new BorderLayout());

        String [] paths = getImagePathFactory().getButtonPaths();

        optionPanel.setBorder(new EmptyBorder(100,100,100,100));

        ImageButton startButton = new ImageButton("START");
        startButton.setImage(paths[0]);
        startButton.setFontSizeAndColour(24, Color.white);
        startButton.addActionListener(e -> {
            getListener().write("CAN_START?");
        });
        optionPanel.add(startButton);

        optionPanel.validate();

        layeredPanel.add(optionPanel);


        options.getButtons().get(0).setImage(paths[1]);
        options.getButtons().get(0).setFontSizeAndColour(24, Color.white);

        backButtonPanel.setLayout(new BorderLayout());
        backButtonPanel.setBorder(new EmptyBorder(10,0,30, 30));
        backButtonPanel.add(options.getButtons().get(0));
    }

    public void connectButtonsJoin(JLayeredPane layeredPanel){

        String [] paths = getImagePathFactory().getButtonPaths();
        options.getButtons().get(0).setImage(paths[0]);
        options.getButtons().get(0).setFontSizeAndColour(24, Color.white);

        backButtonPanel.setLayout(new BorderLayout());
        backButtonPanel.setBorder(new EmptyBorder(10,0,30, 30));
        backButtonPanel.add(options.getButtons().get(0));
    }

    public JPanel addFirstLayer(BackgroundPanel backgroundPanel){
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0,0,0, 30);
        c.weightx = 1;
        c.weighty = 0.2;

        backButtonPanel = new WeightlessPanel();
        backButtonPanel.setOpaque(false);
        backgroundPanel.add(backButtonPanel, c);

        c.gridx = 1;
        WeightlessPanel descriptionPanel = new WeightlessPanel();
        descriptionPanel.setOpaque(false);
        backgroundPanel.add(descriptionPanel,c);
        descriptionPanel.setLayout(new BorderLayout());
        descriptionPanel.add(textArea);
        textArea.setFont(FontCreator.getFontAharoni(64));
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setForeground(Color.GREEN);


        c.insets = new Insets(0,0,0,0);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.weighty = 1;
        c.weightx = 1;

        WeightlessPanel bottomHalf = new WeightlessPanel();
        bottomHalf.setOpaque(false);
        bottomHalf.setLayout(new BorderLayout());

        backgroundPanel.add(bottomHalf, c);

        backgroundPanel.doLayout();

        return bottomHalf;
    }

    private JLayeredPane addLayeredPaneAndBackground(JPanel panel, String imagePath){
        panel.setLayout(new BorderLayout());

        JLayeredPane layeredPane = new JLayeredPane();

        layeredPane.setBounds(0,0, panel.getWidth(), panel.getHeight());
        JPanel jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(20,20,2,20));
        jPanel.setOpaque(false);
        jPanel.setLayout(new BorderLayout());
        layeredPane.add(jPanel, JLayeredPane.FRAME_CONTENT_LAYER);
        panel.add(layeredPane);
        ImageLabel image = new ImageLabel("");
        image.setImage(imagePath);
        jPanel.add(image);


        jPanel.setBounds(0,0,layeredPane.getWidth(),layeredPane.getHeight());
        jPanel.validate();

        return layeredPane;
    }

    public void addChatBox(JPanel panel){
        panel.setOpaque(false);
        chatBox = addLayeredPaneAndBackground(panel, gameImages[1]);

        JPanel chatPanel = new WeightlessPanel();
        chatPanel.setBounds(0,0,chatBox.getWidth(),chatBox.getHeight());
        chatPanel.setOpaque(false);
        chatBox.add(chatPanel, JLayeredPane.DEFAULT_LAYER);

        chatBox.doLayout();
        if (connectionChatBox == null) {
            connectionChatBox = new ChatBox(chatPanel,70, true);
        }
        else{
            connectionChatBox.attachPanel(chatPanel,70);
        }

    }
    public void addOptionBox(JPanel panel){
        panel.removeAll();
        optionBox = addLayeredPaneAndBackground(panel, gameImages[2]);
        exitButton = options.getButtons().get(0);
        if (getState().getId() == NodeNames.HOST_GAME) {
            connectButtonsHost(optionBox);
        }
        else {
            connectButtonsJoin(optionBox);
        }
        addDisconnectListener(exitButton);
    }

}
