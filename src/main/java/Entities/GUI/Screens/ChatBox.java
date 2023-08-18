package Entities.GUI.Screens;

import Entities.GUI.Screens.Functions.ChatLine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatBox {
    private ImagePanel panel;
    private GridBagConstraints chatConstraints;

    private int finalWidth = -1;
    private int finalHeight = -1;

    public ChatBox(JPanel pane, int borders){
        panel = new ImagePanel();
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());
        panel.setBounds(pane.getBounds());
        attachPanel(pane, borders);

        chatConstraints = new GridBagConstraints();
        chatConstraints.gridy = 0;
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

        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBar());

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        scrollPanePanel.add(scrollPane);
        pane.add(scrollPanePanel, constraints);

        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.weighty = 0.2;
        constraints.gridwidth = 1;


        JTextField field = new JTextField();
        field.setOpaque(false);
        field.setForeground(Color.WHITE);
        field.setFont(FontCreator.getFontAharoni(16));
        pane.add(field,constraints);

        constraints.gridx =1;
        constraints.weightx = 0.2;

        ImageButton submitButton =  new ImageButton("");

        pane.add(submitButton, constraints);

        pane.doLayout();

        if (finalHeight == -1){
            finalHeight = panel.getHeight();
            finalWidth = panel.getWidth();
        }

        field.addActionListener((e -> sendText(field,panel)));
        submitButton.addActionListener(e -> sendText(field,panel));
        panel.doLayout();
    }

    private void sendText(JTextField field, ImagePanel panel){
        ChatLine chatLine = new ChatLine(field.getText(), new Dimension(finalWidth, finalHeight/3),
                ImagePathFactory.getPlayerIconPath(Screen.getCurrentPlayer().getPlayerIndex()), Screen.getCurrentPlayer().getName(), MyColor.getColor(Screen.getCurrentPlayer().getPlayerIndex()));

        chatConstraints.gridy += 1;
        chatConstraints.weighty=1;
        chatConstraints.weightx=1;
        chatConstraints.fill= GridBagConstraints.HORIZONTAL;
        field.setText("");

        panel.add(chatLine, chatConstraints);
        panel.validate();
        chatLine.validate();
        panel.repaint();

    }
}
