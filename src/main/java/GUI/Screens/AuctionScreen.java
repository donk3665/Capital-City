package GUI.Screens;

import GUI.Screens.ScreenElements.*;
import Logic.NodeNames;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class AuctionScreen extends Screen{
    /**
     * Constructor that configures the JFrame
     *
     */
    String[] gameImages = getImagePathFactory().getAuctionUIImagePaths();
    JLayeredPane optionBox = null;
    JPanel optionPanel = null;

    JLayeredPane chatBox = null;
    JTextArea textArea = null;
    JPanel [] arrowPanelArray;
    JPanel [] iconBackgroundPanelArray;
    JPanel [] iconOverlayPanelArray;
    JPanel middlePanel;
    JPanel secondMiddlePanel;
    JPanel [] iconPanelArray;

    static ChatBox auctionChatBox;

    JPanel cardPanel;



    public void setName(NodeNames name){
        this.name = name;
    }
    public AuctionScreen() {

    }


    @Override
    public void attachNonStaticComponents() {
        textArea.setText("");
        textArea.setText(description.getDescription().getText());
        addPlayers(middlePanel);
        addOptionBox(secondMiddlePanel);
        addCard(cardPanel);
        gameFrame.getContentPane().validate();
        setRecentChatBox(auctionChatBox);
        getSoundController().playAuctionMusic();
    }

    @Override
    public void setUpGamePane() {
        BackgroundPanel mainPanel = new BackgroundPanel();
        mainPanel.setBackgroundImage(getImagePathFactory().getBackgrounds(3));
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

        middlePanel = new WeightlessPanel();
        middlePanel.setOpaque(false);
        middlePanel.setLayout(new BorderLayout());

        bottomHalf.add(middlePanel, bottomHalfConstraints);

        secondMiddlePanel = new WeightlessPanel();

        secondMiddlePanel.setOpaque(false);
        bottomHalfConstraints.weighty = 0.5;
        bottomHalfConstraints.gridy = 1;
        bottomHalf.add(secondMiddlePanel, bottomHalfConstraints);

        bottomHalfConstraints.weighty = 1;
        bottomHalfConstraints.gridy = 2;
        JPanel bottomPanel = new WeightlessPanel();
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setOpaque(false);
        bottomHalf.add(bottomPanel, bottomHalfConstraints);

        bottomHalf.doLayout();

        addCardAndChat(bottomPanel);
    }

    @Override
    public void handleAsynchronousInput(String input) {
        String [] splitMessage = input.split("\s+");
        switch (splitMessage[0]){
            case "UNLOCK_CHAT" ->{
                auctionChatBox.addMultiplayerSupport(getListener());
            }
        }
    }

    public void addCardAndChat(JPanel panel){

        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 0.3;
        constraints.fill = GridBagConstraints.BOTH;

        cardPanel = new WeightlessPanel();

        panel.add(cardPanel,constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;

        WeightlessPanel panel2 = new WeightlessPanel();
        panel.add(panel2,constraints);

        panel.doLayout();

        addChatBox(panel2);
    }
    public void addCard(JPanel panel){
        panel.setLayout(new BorderLayout());

        CardPathFactory cardPathFactory = new CardPathFactory();

        ImageLabel card = new ImageLabel("");
        card.setImage(cardPathFactory.getCard(getCellIndex(getState().getBiddingProperty())));

        panel.add(card);



    }



    public void addPlayers(JPanel panel){



        if (arrowPanelArray == null){
            panel.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();



            constraints.fill = GridBagConstraints.BOTH;
            //constraints.insets = new Insets(0,0,20,0);
            constraints.weightx = 1;

            arrowPanelArray = new JPanel[getCurrentBoard().getBasicPlayers().size()];
            iconBackgroundPanelArray = new JPanel[getCurrentBoard().getBasicPlayers().size()];
            iconOverlayPanelArray = new JPanel[getCurrentBoard().getBasicPlayers().size()];
            iconPanelArray = new JPanel[getCurrentBoard().getBasicPlayers().size()];
            for (int i = 0; i<getCurrentBoard().getBasicPlayers().size(); i++){
                constraints.gridx = i;
                constraints.gridy = 0;
                arrowPanelArray[i] = new WeightlessPanel();
                arrowPanelArray[i].setOpaque(false);
                arrowPanelArray[i].setLayout(new BorderLayout());
                //arrowPanelArray[i].setBorder(new EmptyBorder(0,30,0,100));
                constraints.weighty = 0.5;
                panel.add(arrowPanelArray[i], constraints);

                constraints.gridy = 1;
                constraints.weighty = 1;
                JLayeredPane layeredPane = new JLayeredPane();
                layeredPane.setBackground(Color.CYAN);

                iconBackgroundPanelArray[i] = new WeightlessPanel();
                iconBackgroundPanelArray[i].setLayout(new BorderLayout());
                iconBackgroundPanelArray[i].setOpaque(false);
                layeredPane.add(iconBackgroundPanelArray[i], JLayeredPane.FRAME_CONTENT_LAYER);

                iconOverlayPanelArray[i] = new WeightlessPanel();
                iconOverlayPanelArray[i].setLayout(new BorderLayout());
                iconOverlayPanelArray[i].setOpaque(false);
                layeredPane.add(iconOverlayPanelArray[i], JLayeredPane.PALETTE_LAYER);


                iconPanelArray[i] = new WeightlessPanel();
                iconPanelArray[i].setOpaque(false);
                //iconPanelArray[i].setBorder(new EmptyBorder(20,20,20,20));
                iconPanelArray[i].setLayout(new BorderLayout());
                layeredPane.add(iconPanelArray[i], JLayeredPane.DEFAULT_LAYER);

                ImageLabel icon = new ImageLabel("");
                icon.setPreserveRatio(true);
                icon.setImage(ImagePathFactory.getPlayerIconPath(i));
                iconPanelArray[i].add(icon);

                panel.add(layeredPane, constraints);
            }
            panel.doLayout();

            for (int i = 0; i<iconPanelArray.length; i++){
                iconBackgroundPanelArray[i].setBounds(0,0, iconBackgroundPanelArray[i].getParent().getWidth(), iconBackgroundPanelArray[i].getParent().getHeight());
                iconPanelArray[i].setBounds(0,0,iconPanelArray[i].getParent().getWidth(), iconPanelArray[i].getParent().getHeight());
            }
        }

        int [] auctionStates = getCurrentBoard().getAuctionStates();
        for (int i = 0; i<iconPanelArray.length; i++){
            arrowPanelArray[i].removeAll();
            iconBackgroundPanelArray[i].removeAll();
            iconOverlayPanelArray[i].removeAll();

            ImageLabel backgroundImage = new ImageLabel("");
            backgroundImage.setPreserveRatio(true);

            if (getCurrentPlayer().getPlayerIndex() == i){
                ImageLabel arrowImage = new ImageLabel("");
                arrowImage.setPreserveRatio(true);
                arrowImage.setImage(gameImages[5]);
                arrowImage.changeImageSize(100,100);
                arrowPanelArray[i].add(arrowImage);

                backgroundImage.setImage(gameImages[3]);
            }
            else if (auctionStates[i] == 0){
                backgroundImage.setImage(gameImages[2]);
            }
            else{
                backgroundImage.setImage(gameImages[4]);
                ImageLabel overlayImage = new ImageLabel("");
                overlayImage.setPreserveRatio(true);
                iconOverlayPanelArray[i].add(overlayImage);
            }
            iconBackgroundPanelArray[i].add(backgroundImage);
        }


    }


    public void connectButtons(JLayeredPane layeredPanel){
        if (!getState().isTurn()){
            return;
        }
        if (optionPanel != null) {
            layeredPanel.remove(optionPanel);
        }
        optionPanel = new JPanel();
        optionPanel.setBounds(0,0,layeredPanel.getWidth(), layeredPanel.getHeight());
        optionPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.weightx = 1;
        c.gridy = 0;
        optionPanel.setBorder(new EmptyBorder(30,40,10,40));

        c.insets = new Insets(6, 6,6,6);
        optionPanel.setOpaque(false);

        String [] paths = getImagePathFactory().getButtonPaths();

        JPanel[] panelList = new JPanel[options.getButtons().size()];
        for (int i = 0; i < options.getButtons().size(); i++){
            panelList[i] = new JPanel();
            panelList[i].setBackground(Color.GREEN);
            panelList[i].setLayout(new BorderLayout());
            c.gridx =  i;
            options.getButtons().get(i).setImage(paths[i]);
            if (i<3) {
                options.getButtons().get(i).addActionListener(e -> {
                    Screen.getSoundController().playClip(1);
                });
            }
            panelList[i].add(options.getButtons().get(i));
            options.getButtons().get(i).setFontSizeAndColour(24, Color.white);
            optionPanel.add(panelList[i],c );
        }
        optionPanel.validate();

        layeredPanel.add(optionPanel);
    }

    public JPanel addFirstLayer(BackgroundPanel backgroundPanel){
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0,300,0,300);
        c.weightx = 0.5;
        c.weighty = 0.2;


        JLayeredPane wrappingPane = new JLayeredPane();

        wrappingPane.setOpaque(false);

        ImageLabel descriptionLabel = new ImageLabel("");
        descriptionLabel.setOpaque(false);
        textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(30,50,20,30));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFont(FontCreator.getFontAharoni(24));
        textArea.setForeground(Color.WHITE);

        descriptionLabel.setImage(gameImages[0]);
        wrappingPane.add(descriptionLabel, JLayeredPane.FRAME_CONTENT_LAYER);
        wrappingPane.add(textArea, JLayeredPane.DEFAULT_LAYER);

        backgroundPanel.add(wrappingPane, c);


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

        descriptionLabel.setBounds(0,0, wrappingPane.getWidth(), wrappingPane.getHeight());
        textArea.setBounds(0,0, wrappingPane.getWidth(), wrappingPane.getHeight());
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
        chatBox = addLayeredPaneAndBackground(panel, gameImages[6]);

        JPanel chatPanel = new JPanel();
        chatPanel.setBounds(0,0,chatBox.getWidth(),chatBox.getHeight());
        chatPanel.setOpaque(false);
        chatBox.add(chatPanel, JLayeredPane.DEFAULT_LAYER);

        chatBox.doLayout();
        if (auctionChatBox == null) {
            auctionChatBox = new ChatBox(chatPanel,40);
        }
        else{
            auctionChatBox.attachPanel(chatPanel,40);
        }

    }
    public void addOptionBox(JPanel panel){
        panel.removeAll();
        optionBox = addLayeredPaneAndBackground(panel, gameImages[6]);
        connectButtons(optionBox);
    }



}
