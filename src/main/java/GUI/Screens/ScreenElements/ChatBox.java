package GUI.Screens.ScreenElements;

import GUI.Screens.Screen;
import Network.ServerListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatBox {
    private ImagePanel panel;
    private GridBagConstraints chatConstraints;

    private int finalWidth = -1;
    private int finalHeight = -1;
    private boolean lobbyChat =false;

    JTextField field;
    ImageButton submitButton;
    ServerListener listener;
    JScrollPane scrollPane;

    public ChatBox(JPanel pane, int borders){
        initializeChatBox(pane,borders);
    }
    public void initializeChatBox(JPanel pane, int borders){
        panel = new ImagePanel();
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());
        //panel.setBounds(pane.getBounds());
        attachPanel(pane, borders);

        chatConstraints = new GridBagConstraints();
        chatConstraints.gridy = 0;
        chatConstraints.anchor = GridBagConstraints.SOUTH;
    }
    public ChatBox(JPanel pane, int borders, boolean lobbyChat){
        this.lobbyChat = lobbyChat;
        initializeChatBox(pane, borders);
    }
    public void attachPanel(JPanel pane, int borders){
        pane.setLayout(new GridBagLayout());
        pane.setBorder(new EmptyBorder(borders,borders,borders,borders));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 2;
        constraints.weighty = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;

        WeightlessPanel scrollPanePanel = new WeightlessPanel();
        scrollPanePanel.setLayout(new BorderLayout());
        scrollPanePanel.setOpaque(false);

        scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBar());

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        //addResizeListener(panel);

        scrollPanePanel.add(scrollPane);
        pane.add(scrollPanePanel, constraints);

        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.weighty = 0.2;
        constraints.gridwidth = 1;


        field = new JTextField();
        field.setOpaque(false);
        field.setForeground(Color.WHITE);
        field.setFont(FontCreator.getFontAharoni(16));
        pane.add(field,constraints);

        constraints.gridx =1;
        constraints.weightx = 0.2;

        submitButton =  new ImageButton("");

        pane.add(submitButton, constraints);

        pane.doLayout();

        if (finalHeight == -1){
            finalHeight = pane.getHeight();
            finalWidth = pane.getWidth();
        }
        if (!lobbyChat) {
            field.addActionListener((e -> sendText()));
            submitButton.addActionListener(e -> {
                sendText();
            });
        }
        else {
            field.addActionListener((e -> sendText2()));
            submitButton.addActionListener(e -> {
                        sendText2();
                    }
                    );
            field.setEditable(false);
        }
        //panel.doLayout();
    }
    public void addMultiplayerSupport(ServerListener listener){
        this.listener = listener;
    }

    public void unlockField(){
        field.setEditable(true);
    }

    private void sendText(){
        ChatLine chatLine = new ChatLine(field.getText(), new Dimension(finalWidth, finalHeight/3),
                ImagePathFactory.getPlayerIconPath(Screen.getClientPlayer().getPlayerIndex()), Screen.getClientPlayer().getName(), MyColor.getColor(Screen.getClientPlayer().getPlayerIndex()));
        if (listener != null){
            listener.write("TEXT_MESSAGE ¶" + field.getText() + "¶" + Screen.getClientPlayer().getPlayerIndex() + "¶"  + Screen.getClientPlayer().getName());
        }
        addChatLine(chatLine);
    }
    private void sendText2(){
        ChatLine chatLine = new ChatLine(field.getText(), new Dimension(finalWidth, finalHeight/3),
                ImagePathFactory.getPlayerIconPath(Screen.getTempIndex()), Screen.getTempName(), MyColor.getColor(Screen.getTempIndex()));
        if (listener != null){
            listener.write("TEXT_MESSAGE ¶" + field.getText() + "¶" + Screen.getTempIndex() + "¶"  + Screen.getTempName());
        }
        addChatLine(chatLine);
    }
    public void addExternalMessage(String message, int writerIndex, String writerName){
        ChatLine chatLine = new ChatLine(message, new Dimension(finalWidth, finalHeight/3),
                ImagePathFactory.getPlayerIconPath(writerIndex), writerName, MyColor.getColor(writerIndex));
        addExternalChatLine(chatLine);
    }
    private void addExternalChatLine(ChatLine line){
        chatConstraints.gridy += 1;
        chatConstraints.weighty=1;
        chatConstraints.weightx=1;
        chatConstraints.fill= GridBagConstraints.HORIZONTAL;
        panel.add(line, chatConstraints);
        panel.validate();
        line.validate();
        panel.repaint();
        scrollPane.getViewport().validate();
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
    }

    private void addChatLine(ChatLine line){

        chatConstraints.gridy += 1;
        chatConstraints.weighty=1;
        chatConstraints.weightx=1;
        chatConstraints.fill= GridBagConstraints.HORIZONTAL;
        field.setText("");
        panel.add(line, chatConstraints);
        panel.validate();
        line.validate();
        panel.repaint();
        scrollPane.getViewport().validate();
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
    }

}
