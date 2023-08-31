package Entities.GUI.Screens;

import Entities.GUI.Screens.ScreenElements.*;
import Logic.NodeNames;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EndingScreen extends Screen{
    /**
     * Constructor that configures the JFrame
     *
     */
    String[] gameImages = getImagePathFactory().getEndingImagePaths();

    JLayeredPane chatBox = null;

    JPanel middlePanel;

    JTextArea textArea;

    static ChatBox endingChatBox;

    JPanel buttonPanel;

    ImageLabel icon;



    public void setName(NodeNames name){
        this.name = name;
    }
    public EndingScreen() {

    }

    @Override
    public void attachNonStaticComponents() {
        textArea.setText(description.getDescription().getText());
        options.getButtons().get(0).setImage(getImagePathFactory().getButtonPaths()[0]);
        options.getButtons().get(0).setFontSizeAndColour(24, Color.white);
        buttonPanel.add(options.getButtons().get(0));
        icon.setImage(ImagePathFactory.getPlayerIconPath(getState().getPlayer().getPlayerIndex()));
        gameFrame.getContentPane().validate();
        setRecentChatBox(endingChatBox);
    }

    @Override
    public void setUpGamePane() {
        BackgroundPanel mainPanel = new BackgroundPanel();
        mainPanel.setBackgroundImage(getImagePathFactory().getBackgrounds(4));
        gamePane.add(mainPanel, JLayeredPane.FRAME_CONTENT_LAYER);
        mainPanel.setBounds(0,0, (int) Screen.width, (int) Screen.height);

        JPanel bottomHalf = addFirstLayer(mainPanel);
        bottomHalf.setLayout(new GridBagLayout());

        GridBagConstraints bottomHalfConstraints = new GridBagConstraints();
        bottomHalfConstraints.gridx = 0;
        bottomHalfConstraints.gridy = 0;
        bottomHalfConstraints.weighty = 0.6;
        bottomHalfConstraints.weightx = 1;
        bottomHalfConstraints.insets = new Insets(0, 0, 0, 0);
        bottomHalfConstraints.fill = GridBagConstraints.BOTH;

        middlePanel = new WeightlessPanel();
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

        addChatAndText(bottomPanel);
    }

    @Override
    public void handleAsynchronousInput(String input) {
        String [] splitMessage = input.split("\s+");
        switch (splitMessage[0]){
            case "UNLOCK_CHAT" ->{
                endingChatBox.addMultiplayerSupport(getListener());
            }
        }
    }

    public void addChatAndText(JPanel panel){
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.6;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;

        WeightlessPanel textPanel = new WeightlessPanel();

        textPanel.setOpaque(false);

        panel.add(textPanel,constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;


        buttonPanel = new WeightlessPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setOpaque(false);
        panel.add(buttonPanel,constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;

        WeightlessPanel panel3 = new WeightlessPanel();
        panel3.setBackground(Color.BLACK);
        panel.add(panel3,constraints);

        panel.doLayout();

        JLayeredPane textLayeredPanel = addLayeredPaneAndBackground(textPanel, gameImages[1]);

        textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(70,100,30,30));
        textArea.setOpaque(false);
        textArea.setFont(FontCreator.getFontAharoni(33));
        textArea.setForeground(Color.WHITE);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(0,0,textLayeredPanel.getWidth(), textLayeredPanel.getHeight());
        textLayeredPanel.add(textArea, JLayeredPane.DEFAULT_LAYER);

        addChatBox(panel3);
    }

    public JPanel addFirstLayer(BackgroundPanel backgroundPanel){
        backgroundPanel.setLayout(new GridBagLayout());



        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(90,600,0,600);
        c.weightx = 0.5;
        c.weighty = 0.5;

        JLayeredPane wrappingPane = new JLayeredPane();
        wrappingPane.setOpaque(false);
        ImageLabel iconBg = new ImageLabel("");
        iconBg.setImage(gameImages[0]);
        wrappingPane.add(iconBg, JLayeredPane.FRAME_CONTENT_LAYER);

        WeightlessPanel panel = new WeightlessPanel();
        panel.setBorder(new EmptyBorder(70,70,70,70));
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        icon = new ImageLabel("");
        panel.add(icon);

        wrappingPane.add(panel, JLayeredPane.DEFAULT_LAYER);

        backgroundPanel.add(wrappingPane, c);

        c.insets = new Insets(0,20,0,0);
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

        iconBg.setBounds(0,0, wrappingPane.getWidth(), wrappingPane.getHeight());
        panel.setBounds(0,0, wrappingPane.getWidth(), wrappingPane.getHeight());

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
        chatBox = addLayeredPaneAndBackground(panel, gameImages[2]);

        JPanel chatPanel = new JPanel();
        chatPanel.setBounds(0,0,chatBox.getWidth(),chatBox.getHeight());
        chatPanel.setOpaque(false);
        chatBox.add(chatPanel, JLayeredPane.DEFAULT_LAYER);

        chatBox.doLayout();
        if (endingChatBox == null) {
            endingChatBox = new ChatBox(chatPanel,40);
        }
        else{
            endingChatBox.attachPanel(chatPanel,40);
        }

    }


}