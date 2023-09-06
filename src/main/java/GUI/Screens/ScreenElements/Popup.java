package GUI.Screens.ScreenElements;

import javax.swing.*;
import java.awt.*;

public class Popup {

    private JPanel parentPanel;
    private ImagePanel basePanel;
    private JPanel content;
    private boolean addExitButton;

    public Popup(JPanel parentPanel, JPanel content, boolean addExitButton){
        this.parentPanel = parentPanel;
        this.content = content;
        this.addExitButton = addExitButton;
        this.parentPanel.setLayout(new BorderLayout());
        createPopup(parentPanel.getSize());
    }
    private void createPopup(Dimension size){
        basePanel = new ImagePanel();
        parentPanel.add(basePanel);
        parentPanel.validate();

        basePanel.setImage(ImageCreator.getAndScaleImage("/Images/InGameAssets/PopupAssets/popupbg.jpg", size));
        basePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.15;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;

        WeightlessPanel topWrappingPanel = new WeightlessPanel();
        topWrappingPanel.setOpaque(false);
        topWrappingPanel.setLayout(new BorderLayout());
        ImagePanel topPanel = new ImagePanel();

        topWrappingPanel.add(topPanel);
        basePanel.add(topWrappingPanel,constraints);

        constraints.gridy = 1;
        constraints.weighty = 1;
        WeightlessPanel contentWrappingPanel = new WeightlessPanel();
        contentWrappingPanel.setOpaque(false);
        contentWrappingPanel.setLayout(new BorderLayout());
        contentWrappingPanel.add(content);
        basePanel.add(contentWrappingPanel, constraints);

        basePanel.validate();
        addExitStrip(topPanel);
    }
    private void addExitStrip(ImagePanel panel){
        panel.setImage(ImageCreator.getAndScaleImage("/Images/InGameAssets/PopupAssets/BlackBar.png", panel.getSize()));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 1;

        constraints.insets = new Insets(5,5,5,5);
        constraints.fill = GridBagConstraints.BOTH;

        ImageLabel label = new ImageLabel("");
        label.setImage("/Images/InGameAssets/PopupAssets/WhiteBar.png");

        panel.add(label, constraints);

        if (addExitButton) {
            constraints.gridx = 1;
            constraints.weightx = 0.1;
            ImageButton exitButton = new ImageButton("");
            exitButton.setImage("/Images/InGameAssets/PopupAssets/ExitImage.png");

            exitButton.addActionListener(e -> {
                        parentPanel.remove(basePanel);
                        parentPanel.getParent().repaint();
                    }
            );
            panel.add(exitButton, constraints);
        }
        panel.validate();

    }


}
