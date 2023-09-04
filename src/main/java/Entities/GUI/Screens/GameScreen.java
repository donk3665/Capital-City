package Entities.GUI.Screens;

import Entities.ExternalDataTransfer.BasicPlayer;
import Entities.GUI.Screens.ScreenElements.*;
import Entities.GUI.Screens.ScreenElements.Popup;
import Entities.GUI.SettingsController.SettingsFunction;
import Logic.NodeNames;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameScreen extends Screen{
    /**
     * Constructor that configures the JFrame
     *
     */
    String[] gameImages = getImagePathFactory().getUIImagePaths();
    JLayeredPane optionBox = null;
    JPanel optionPanel =  new JPanel();
    JLayeredPane chatBox = null;

    JTextArea textArea = null;

    BoardGrid overlayGrid = null;
    BoardGrid buttonGrid = null;

    JPanel overlayPanel = null;

    JLayeredPane board = null;
    static ChatBox actualChatBox;



    public GameScreen() {

    }

    public boolean connectGameButtons(JLayeredPane connect){
        if (!getState().isTurn()){
            return false;
        }
        connectButtons(optionPanel, options.getButtons());

        connect.add(optionPanel);
        return true;

    }
    public void connectButtons(JPanel panel, List<ImageButton> buttons){

        panel.removeAll();

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.weightx = 1;
        c.ipady = 24;
        panel.setBorder(new EmptyBorder(30,40,30,40));

        c.insets = new Insets(6, 6,6,6);
        panel.setOpaque(false);

        String [] paths = getImagePathFactory().getButtonPaths();

        JPanel[] panelList = new JPanel[buttons.size()];


        for (int i = 0; i < buttons.size(); i++){
            panelList[i] = new WeightlessPanel();
            panelList[i].setLayout(new BorderLayout());
            c.gridy = i%4;
            c.gridx =  i/4;
            //System.out.println(options.getButtons().get(i).getText());
            buttons.get(i).setImage(paths[i]);
            panelList[i].add(buttons.get(i));
            buttons.get(i).setFontSizeAndColour(24, Color.white);
            panel.add(panelList[i],c );
            //panelList[i].validate();
        }
        panel.validate();

    }

    public JPanel addFirstLayer(BackgroundPanel backgroundPanel){
        backgroundPanel.setLayout(new GridBagLayout());

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


        JLayeredPane wrappingPane = new JLayeredPane();

        wrappingPane.setOpaque(false);

        ImageLabel descriptionLabel = new ImageLabel("");
        descriptionLabel.setOpaque(false);


        textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(30,30,20,30));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFont(FontCreator.getFontAharoni(24));
        textArea.setForeground(Color.WHITE);

        descriptionLabel.setImage(gameImages[4]);

        wrappingPane.add(descriptionLabel, JLayeredPane.FRAME_CONTENT_LAYER);
        wrappingPane.add(textArea, JLayeredPane.DEFAULT_LAYER);
        backgroundPanel.add(wrappingPane, c);
        descriptionLabel.doLayout();


        c.gridx = 2;
        c.weightx = 0.2;

        JPanel settingsButtonPanel = new JPanel();
        settingsButtonPanel.setOpaque(false);
        settingsButtonPanel.setLayout(new BorderLayout());
        ImageButton settingsButton = new ImageButton("");
        addSettingsButton(settingsButton);
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

        backgroundPanel.doLayout();
        bottomHalf.doLayout();

        descriptionLabel.setBounds(0,0, wrappingPane.getWidth(), wrappingPane.getHeight());
        textArea.setBounds(0,0, wrappingPane.getWidth(), wrappingPane.getHeight());
        return actualBottomHalf;
    }
    private void addSettingsButton(ImageButton button){
        JPanel popupPanel = new JPanel();
        popupPanel.setOpaque(false);
        gamePane.add(popupPanel,JLayeredPane.POPUP_LAYER);

        popupPanel.setBounds(gamePane.getWidth()/4,gamePane.getHeight()/4, gamePane.getWidth()/2, gamePane.getHeight()/2);

        SettingsFunction function = new SettingsFunction(popupPanel, this);
        button.addActionListener(e ->{
            function.createPopup(null);
        });
    }

    private JLayeredPane addLayeredPaneAndBackground(JPanel panel, String imagePath){
        JLayeredPane layeredPane = new JLayeredPane();

        layeredPane.setBounds(0,0, panel.getWidth(), panel.getHeight());

        JPanel jPanel = new JPanel();
        jPanel.setOpaque(false);
        jPanel.setLayout(new BorderLayout());
        layeredPane.add(jPanel, JLayeredPane.FRAME_CONTENT_LAYER);
        panel.add(layeredPane);
        ImageLabel image = new ImageLabel("");
        image.setImage(imagePath);
        jPanel.add(image);


        jPanel.setBounds(0,0,layeredPane.getWidth(),layeredPane.getHeight());
        jPanel.doLayout();

        return layeredPane;
    }

    public void addBoard(JPanel panel){

        board = addLayeredPaneAndBackground(panel, gameImages[0]);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridBagLayout());
        gridPanel.setBounds(0,0, board.getWidth(), board.getHeight());
        board.add(gridPanel, JLayeredPane.MODAL_LAYER);
        gridPanel.setOpaque(false);

        buttonGrid = new BoardGrid(gridPanel, false, board);

        overlayPanel = new JPanel();
        overlayPanel.setLayout(new GridBagLayout());
        overlayPanel.setBounds(0,0, board.getWidth(), board.getHeight());
        overlayPanel.setOpaque(false);
        board.add(overlayPanel, JLayeredPane.PALETTE_LAYER);
        overlayGrid = new BoardGrid(overlayPanel, true, board);

//        JPanel backgroundPanel = new JPanel();
//        backgroundPanel.setLayout(new GridBagLayout());
//        backgroundPanel.setBounds(0,0, board.getWidth(), board.getHeight());
//        backgroundPanel.setOpaque(false);
//        board.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
//        BoardGrid backgroundGrid = new BoardGrid(backgroundPanel, true, board);
//        paintBackgroundElements(backgroundGrid);
    }
//    private void paintBackgroundElements(BoardGrid overlayGrid){
//        GridBackgroundCreator backgroundCreator = new GridBackgroundCreator(overlayGrid.getPanelGrid());
//    }
    private void paintHousesAndHotels(BoardGrid overlayGrid){
        GridOverlayCreator overlayCreator = new GridOverlayCreator(overlayGrid.getPanelGrid(), getCurrentBoard());
    }


    private void connectBoardButtons(JLayeredPane layeredPane, BoardGrid grid){
        for (Component component: layeredPane.getComponentsInLayer(JLayeredPane.POPUP_LAYER)){
            layeredPane.remove(component);
        }
        JPanel popupPanel = new JPanel();
        popupPanel.setOpaque(false);
        popupPanel.setBounds(layeredPane.getWidth()/4,layeredPane.getHeight()/4, layeredPane.getWidth()/2, layeredPane.getHeight()/2);

        layeredPane.add(popupPanel, JLayeredPane.POPUP_LAYER);
        ImageButton [] buttons = grid.getButtonGrid();

        for (int i = 0; i< buttons.length; i++){
            int num = i;
            for (ActionListener t : buttons[ i].getActionListeners()){
                buttons[i].removeActionListener(t);
            }
            buttons[i].addActionListener(e -> {
                popupPanel.removeAll();
                Entities.GUI.Screens.ScreenElements.Popup p = new Popup( popupPanel, new CardShower(num, getCurrentBoard().getCells().get(num), getNumPlayersTile(num)).getContentPanel(), true);
            });
        }
    }

    private int getNumPlayersTile(int tile){
        int counter = 0;
        List<BasicPlayer> players = getCurrentBoard().getBasicPlayers();
        for (BasicPlayer player : players) {
            if (player.getPosition() == tile) {
                counter += 1;
            }
        }
        return counter;
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

        panel.doLayout();

        addOptionBox(topPanel);

        addChatBox(bottomPanel);

    }
    public void addChatBox(JPanel panel){
        chatBox = addLayeredPaneAndBackground(panel, gameImages[1]);
        JPanel chatPanel = new JPanel();
        chatPanel.setBounds(0,0,chatBox.getWidth(),chatBox.getHeight());
        chatPanel.setOpaque(false);
        chatBox.add(chatPanel, JLayeredPane.DEFAULT_LAYER);

        chatBox.doLayout();
        if (actualChatBox == null) {
            actualChatBox = new ChatBox(chatPanel,20);
        }
        else{
            actualChatBox.attachPanel(chatPanel,20);
        }

    }
    public void addOptionBox(JPanel panel){
        optionBox = addLayeredPaneAndBackground(panel, gameImages[2]);
        optionPanel.setBounds(0,0,optionBox.getWidth(), optionBox.getHeight());
        optionPanel.setLayout(new GridBagLayout());
    }

    @Override
    public void setUpGamePane() {
        BackgroundPanel mainPanel = new BackgroundPanel();

        mainPanel.setBackgroundImage(getImagePathFactory().getBackgrounds(2));
        gamePane.add(mainPanel, JLayeredPane.FRAME_CONTENT_LAYER);


        mainPanel.setBounds(gamePane.getBounds());
        JPanel boardOptionPanel = addFirstLayer(mainPanel);


        mainPanel.doLayout();

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

        boardOptionPanel.doLayout();

        addBoard(leftPanel);

        addOptionChatBox(rightPanel);

    }

    @Override
    public void handleAsynchronousInput(String input) {
        String [] splitMessage = input.split("\s+");
        switch (splitMessage[0]){
            case "UNLOCK_CHAT" ->{
                actualChatBox.addMultiplayerSupport(getListener());
            }
            case "TRADE" ->{
                String[] tradeSplit = input.split("¶");
                addTradePopup(tradeSplit[1], tradeSplit[2]);
            }
            case "CANCEL_TRADE" ->{
                if (!trade_reacted){
                    gamePane.remove(tradePopupPanel);
                    gamePane.validate();
                    gamePane.repaint();

                    getListener().write("TRADE_CANCELLED " + currentPlayer.getPlayerIndex());
                }
            }

        }
    }
    private volatile boolean trade_reacted = false;
    JPanel tradePopupPanel = new WeightlessPanel();;
    public void addTradePopup(String currentPropertyName, String tradingPropertyName){
        trade_reacted = false;
        tradePopupPanel.setBounds(gamePane.getWidth()/4, gamePane.getHeight()/4, gamePane.getWidth()/2, gamePane.getHeight()/2);
        gamePane.add(tradePopupPanel, JLayeredPane.MODAL_LAYER);

        List<String> list = new ArrayList<>();
        list.add("yes");
        list.add("no");
        OptionPanel optionPanel1 = new OptionPanel(list, getCurrentPlayer().getName() + " is requesting for " +tradingPropertyName + " in exchange for " + currentPropertyName
        + ". Accept?");
        optionPanel1.getButtonList().get(0).addActionListener(e ->{
            getListener().write("TRADE_ACCEPT " + currentPlayer.getPlayerIndex());
            trade_reacted = true;
            gamePane.remove(tradePopupPanel);
            gamePane.validate();
            gamePane.repaint();
        });
        optionPanel1.getButtonList().get(1).addActionListener(e ->{
            getListener().write("TRADE_DECLINE " + currentPlayer.getPlayerIndex());
            trade_reacted = true;
            gamePane.remove(tradePopupPanel);
            gamePane.validate();
            gamePane.repaint();
        });
        Popup popup = new Popup(tradePopupPanel, optionPanel1.getContentPanel() ,false);

        gamePane.validate();
        gamePane.repaint();
    }

    @Override
    public void attachNonStaticComponents() {
        textArea.setText("");

        for (Component component: optionBox.getComponentsInLayer(JLayeredPane.DEFAULT_LAYER)){
            optionBox.remove(component);
        }
        for (Component component: optionBox.getComponentsInLayer(JLayeredPane.POPUP_LAYER)){
            optionBox.remove(component);
        }
        for (Component component: optionBox.getComponentsInLayer(JLayeredPane.MODAL_LAYER)){
            optionBox.remove(component);
        }
        if (connectGameButtons(optionBox)){
            textArea.setText(description.getDescription().getText());
        }
        else {
            if (getState().getId() == NodeNames.TRADE_OPPONENT_PARENT){
                addWaitCancelPanel(optionBox);
            }
            else {
                addPlayerViewerPanel(optionBox);
            }
        }
        paintHousesAndHotels(overlayGrid);
        connectBoardButtons(board, buttonGrid);
        setRecentChatBox(actualChatBox);
    }
    public void addPlayerViewerPanel(JLayeredPane pane){
        List<ImageButton> playerButtons = new ArrayList<>();

        ImagePanel panel = new ImagePanel();
        pane.add(panel, JLayeredPane.POPUP_LAYER);
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(40,40,40,40));
        panel.setBounds(0,0, pane.getWidth(), pane.getHeight());

        for (int i = 0; i<currentBoard.getBasicPlayers().size(); i++){
            ImageButton tempButton = new ImageButton(currentBoard.getBasicPlayers().get(i).getName());
            playerButtons.add(tempButton);

            int finalI = i;
            tempButton.addActionListener(e->{
                panel.removeAll();
                Popup popup = new Popup(panel, new PlayerShower(getCurrentBoard().getBasicPlayers().get(finalI)).getContentPanel(),true);
            });

        }
        JPanel asyncOptionPanel = new WeightlessPanel();

        asyncOptionPanel.setBounds(0,0,optionBox.getWidth(), optionBox.getHeight());
        asyncOptionPanel.setLayout(new GridBagLayout());
        connectButtons(asyncOptionPanel, playerButtons);

        pane.add(asyncOptionPanel, JLayeredPane.MODAL_LAYER);
    }

    public void addWaitCancelPanel(JLayeredPane pane){
        OptionPanel optionPanel1 = new OptionPanel(Collections.singletonList("Cancel"), "Sending trade...");

        optionPanel1.getButtonList().get(0).addActionListener(e->{
            getListener().write("CANCEL_TRADE " + currentPlayer.getPlayerIndex());

        });
        optionPanel1.getContentPanel().setBounds(pane.getWidth()/4,pane.getHeight()/4, pane.getWidth()/2, pane.getHeight()/2);
        pane.add(optionPanel1.getContentPanel());

    }





}
