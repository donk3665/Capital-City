package GUI.Screens.ScreenElements;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ChatLine extends ImagePanel {

    private Dimension size;
    private JPanel iconBackgroundPanel;
    private JPanel iconPanel;
    private JLayeredPane layeredImagePanel;
    private JTextArea speech;
    private String text;

    private WeightlessPanel areaPanel;
    private WeightlessPanel speechPanel;

    @Override
    public Dimension getPreferredSize() {
        return size;
    }


    public ChatLine(String text, Dimension size, String profileImagePath, String name, Color nameColor){
        this.text = text;
        this.size = size;
        this.size = new Dimension((int) size.getWidth(), (int) (size.getHeight() + 30*text.length()/36));

        setOpaque(false);
        setImage(ImageCreator.getAndScaleImage("/Images/InGameAssets/ChatAssets/Picture1.png", this.size));

        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.1;
        constraints.weighty = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets.set(2,2,2,2);

        layeredImagePanel = new JLayeredPane();
        layeredImagePanel.setOpaque(false);

        iconBackgroundPanel = new JPanel();
        iconBackgroundPanel.setOpaque(false);
        iconBackgroundPanel.setLayout(new FlowLayout());

        iconPanel = new JPanel();
        iconPanel.setOpaque(false);
        iconPanel.setLayout(new FlowLayout());

        ImageLabel iconBackground = new ImageLabel("");
        iconBackground.setPreserveRatio(true);
        iconBackground.setImage("/Images/InGameAssets/ChatAssets/Picture2.png");
        iconBackground.changeImageSize(60,60);

        ImageLabel icon = new ImageLabel("");
        icon.setImage(profileImagePath);
        icon.setPreserveRatio(true);
        icon.changeImageSize(60,60);

        iconBackgroundPanel.add(iconBackground);
        iconPanel.add(icon);

        layeredImagePanel.add(iconBackgroundPanel, JLayeredPane.FRAME_CONTENT_LAYER);
        layeredImagePanel.add(iconPanel, JLayeredPane.DEFAULT_LAYER);

        add(layeredImagePanel, constraints);

        constraints.gridy = 0;
        constraints.gridx = 1;
        constraints.weightx=1;
        constraints.weighty = 2;
        constraints.gridheight = 2;

        WeightlessPanel textPanel = new WeightlessPanel();
        textPanel.setOpaque(false);
        addText(textPanel, name, nameColor);
        add(textPanel, constraints);

        iconBackgroundPanel.setBounds(layeredImagePanel.getBounds());
        iconPanel.setBounds(layeredImagePanel.getBounds());
    }
    public void addText(WeightlessPanel panel, String name, Color nameColor){
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 0.4;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets.set(2,2,4,4);


        areaPanel = new WeightlessPanel();
        areaPanel.setOpaque(false);
        areaPanel.setLayout(new GridLayout(0,2));

        JTextArea nameArea = new JTextArea();;
        nameArea.setEditable(false);
        nameArea.setForeground(nameColor);
        nameArea.setOpaque(false);
        nameArea.setFont(FontCreator.getFontAharoni(12));
        nameArea.setText(name);
        areaPanel.add(nameArea);


        JTextArea date = new JTextArea();
        date.setEditable(false);
        date.setFont(FontCreator.getFontAharoni(12));
        date.setOpaque(false);
        date.setForeground(Color.GRAY);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("EST5EDT"));
        date.setText(dateFormat.format(new Date()));

        areaPanel.add(date);

        panel.add(areaPanel, constraints);


        constraints.gridy = 1;
        constraints.weighty = 1;
        constraints.gridheight = 1;
        speechPanel = new WeightlessPanel();
        speechPanel.setOpaque(false);
        //speechPanel.setOpaque(false);
        speechPanel.setLayout(new BorderLayout());
        speech = new JTextArea();
      //  addResizeListener(speech);
        speech.setLineWrap(true);
        speech.setEditable(false);
        speech.setWrapStyleWord(true);
        speech.setOpaque(false);
        speech.setForeground(Color.WHITE);
        speech.setFont(FontCreator.getFontAharoni(24));

        speech.setText(text);

        speechPanel.add(speech);

        panel.add(speechPanel,constraints);
    }

    @Override
    public void validate() {
        iconBackgroundPanel.setBounds(layeredImagePanel.getBounds());
        iconPanel.setBounds(layeredImagePanel.getBounds());
        iconBackgroundPanel.validate();
        iconPanel.validate();
        super.validate();
    }
}
