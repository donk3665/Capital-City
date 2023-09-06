package GUI.Screens;

import GUI.Screens.ScreenElements.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class JoinScreen extends Screen{
    /**
     * Constructor that configures the JFrame
     *
     */
    String[] gameImages = getImagePathFactory().getJoinImagePaths();
    JLayeredPane optionBox = null;
    JLayeredPane descriptionBox = null;
    JPanel optionPanel =  new JPanel();
    JPanel descriptionPanel =  new JPanel();
    JPanel backButtonPanel;
    JPanel gamesContentPanel;
    ArrayList<ImageButton> buttonLobbies = new ArrayList<>();
    private String attemptID;
    public JoinScreen() {
        joinAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
    }
    ActionListener joinAction;

    public void connectButtons(JLayeredPane connect){
        if (joinAction != null){
            options.getButtons().get(0).removeActionListener(joinAction);
        }
        joinAction = e -> {
            getListener().write("JOIN " + attemptID);
        };
        options.getButtons().get(0).addActionListener(joinAction);

        optionPanel.removeAll();
        optionPanel.setLayout(new BorderLayout());
        optionPanel.setBorder(new EmptyBorder(30,40,30,40));
        optionPanel.setOpaque(false);


        String [] paths = getImagePathFactory().getButtonPaths();
        options.getButtons().get(0).setImage(paths[0]);
        options.getButtons().get(0).setFontSizeAndColour(24, Color.white);

        optionPanel.add(options.getButtons().get(0));
        optionPanel.doLayout();

        connect.add(optionPanel);

    }

    public JPanel addFirstLayer(BackgroundPanel backgroundPanel){
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0,0,0,300);
        c.weightx = 1;
        c.weighty = 0.2;

        backButtonPanel = new WeightlessPanel();
        backButtonPanel.setOpaque(false);
        backgroundPanel.add(backButtonPanel, c);

        c.insets = new Insets(0,0,0,0);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.weighty = 1;
        c.weightx = 1;

        WeightlessPanel bottomHalf = new WeightlessPanel();
        bottomHalf.setOpaque(false);
        bottomHalf.setLayout(new GridBagLayout());

        backgroundPanel.add(bottomHalf, c);

        backgroundPanel.doLayout();

        return bottomHalf;
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







    public void addOptionDescriptionBox(JPanel panel){

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

        addDescriptionBox(topPanel);

        addOptionBox(bottomPanel);

    }


    public void addDescriptionBox(JPanel panel){
        descriptionBox = addLayeredPaneAndBackground(panel, gameImages[1]);
        descriptionPanel.setBounds(0,0,descriptionBox.getWidth(), descriptionBox.getHeight());
        descriptionPanel.setLayout(new GridBagLayout());
        descriptionPanel.setOpaque(false);
        descriptionBox.add(descriptionPanel);
    }

    public void addOptionBox(JPanel panel){
        optionBox = addLayeredPaneAndBackground(panel, gameImages[1]);
        optionPanel.setBounds(0,0,optionBox.getWidth(), optionBox.getHeight());
        optionPanel.setLayout(new GridBagLayout());
    }
    public void addGameOptionsBox(JPanel panel){
        JLayeredPane scrollBox = addLayeredPaneAndBackground(panel, gameImages[0]);

        WeightlessPanel scrollPanePanel = new WeightlessPanel();
        scrollPanePanel.setLayout(new BorderLayout());
        scrollPanePanel.setOpaque(false);
        scrollPanePanel.setBorder(new EmptyBorder(50,50,50,50));
        scrollBox.add(scrollPanePanel, JLayeredPane.DEFAULT_LAYER);

        gamesContentPanel = new JPanel();
        gamesContentPanel.setOpaque(false);
        gamesContentPanel.setLayout(new GridBagLayout());

        scrollBox.doLayout();

        scrollPanePanel.setBounds(scrollBox.getBounds());

        JScrollPane scrollPane = new JScrollPane(gamesContentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBar());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        scrollPanePanel.add(scrollPane);
    }

    @Override
    public void setUpGamePane() {
        BackgroundPanel mainPanel = new BackgroundPanel();

        mainPanel.setBackgroundImage(getImagePathFactory().getBackgrounds(6));
        gamePane.add(mainPanel, JLayeredPane.FRAME_CONTENT_LAYER);


        mainPanel.setBounds(gamePane.getBounds());
        JPanel bottomPanel = addFirstLayer(mainPanel);


        mainPanel.doLayout();

        GridBagConstraints bottomHalfConstraints = new GridBagConstraints();
        bottomHalfConstraints.gridx = 1;
        bottomHalfConstraints.gridy = 0;
        bottomHalfConstraints.weighty = 1;
        bottomHalfConstraints.weightx = 1;
        bottomHalfConstraints.insets = new Insets(10, 10, 10, 10);
        bottomHalfConstraints.fill = GridBagConstraints.BOTH;

        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BorderLayout());

        bottomPanel.add(rightPanel, bottomHalfConstraints);

        bottomHalfConstraints.gridx = 0;
        bottomHalfConstraints.weightx = 0.7;

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setOpaque(false);
        bottomPanel.add(leftPanel, bottomHalfConstraints);

        bottomPanel.doLayout();

        addGameOptionsBox(rightPanel);

        addOptionDescriptionBox(leftPanel);

    }
    private void addLobbyToList(String input){
        String [] splitMessage = input.split("\s+");
        String currNumPlayers = splitMessage[1];
        String maxNumPlayers = splitMessage[2];
        String numOfRounds = splitMessage[3];
        String gameMode = splitMessage[4];
        String lobbyID = splitMessage[5];

        String buttonText;
        if (Integer.parseInt(numOfRounds)!= -1) {
            buttonText = currNumPlayers + "/" + maxNumPlayers + " players, " + numOfRounds + " rounds, " + gameMode + " mode, " + lobbyID;
        }
        else{
            buttonText = currNumPlayers + "/" + maxNumPlayers + " players, " + gameMode + " mode, " + lobbyID;
        }

        for (ImageButton button: buttonLobbies){
            if (button.getId().equals(lobbyID)){
                if (Integer.parseInt(currNumPlayers) == 0){
                    buttonLobbies.remove(button);
                    return;
                }
                button.setText(buttonText);
                return;
            }
        }
        ImageButton lobbyButton;
        lobbyButton= new ImageButton(buttonText);
        lobbyButton.setId(lobbyID);
        lobbyButton.setSuperPreservedSize(true);
        lobbyButton.setFontSizeAndColour(20, Color.WHITE);
        lobbyButton.setImage(getImagePathFactory().getButtonPaths()[4]);
        buttonLobbies.add(lobbyButton);

        lobbyButton.addActionListener(e ->{
            attemptID = lobbyButton.getId();
            connectButtons(optionBox);
            addDescription(descriptionPanel);
        });

    }
    public void addDescription(JPanel panel){
        panel.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setFont(FontCreator.getFontAharoni(16));
        textArea.setText("Sup");
        textArea.setEditable(false);
        panel.add(textArea);
        panel.validate();
    }

    @Override
    public void handleAsynchronousInput(String input) {
        String [] splitMessage = input.split("\s+");
        switch (splitMessage[0]){
            case "LOBBY" ->{
                addLobbyToList(input);
                gamesContentPanel.removeAll();

                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = 0;

                constraints.weightx = 1;
                constraints.weighty = 1;
                constraints.anchor = GridBagConstraints.NORTH;
                constraints.fill = GridBagConstraints.HORIZONTAL;

                for (int i = 0; i< buttonLobbies.size(); i++){
                    constraints.gridy = i;
                    gamesContentPanel.add(buttonLobbies.get(i),constraints);
                }
                gamesContentPanel.validate();
            }
            case "SENDING_LOBBIES" ->{
                buttonLobbies.clear();
            }
        }

    }

    @Override
    public void attachNonStaticComponents() {
        gamesContentPanel.removeAll();
        for (Component component: optionBox.getComponentsInLayer(JLayeredPane.DEFAULT_LAYER)){
            optionBox.remove(component);
        }
        addExitButton();
    }
    public void addExitButton(){
        backButtonPanel.setLayout(new BorderLayout());
        backButtonPanel.setBorder(new EmptyBorder(10,0,30, (int) (1000*widthAdjust)));

        String [] paths = getImagePathFactory().getButtonPaths();
        options.getButtons().get(1).setImage(paths[1]);
        options.getButtons().get(1).setFontSizeAndColour(24, Color.white);
        backButtonPanel.add(options.getButtons().get(1));
        backButtonPanel.doLayout();
    }





}
