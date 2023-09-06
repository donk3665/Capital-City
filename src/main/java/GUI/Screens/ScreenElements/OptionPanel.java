package GUI.Screens.ScreenElements;

import GUI.Screens.ContentPanelInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OptionPanel implements ContentPanelInterface {

    private final JPanel contentPanel;

    private final ArrayList<ImageButton> buttonList;


    public OptionPanel(List<String> options){
        contentPanel = new WeightlessPanel();
        contentPanel.setLayout(new GridBagLayout());
        buttonList = new ArrayList<>();

        addComponents(options,0);
    }
    public OptionPanel(List<String> options, String text){
        contentPanel = new WeightlessPanel();
        contentPanel.setLayout(new GridBagLayout());
        buttonList = new ArrayList<>();

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(textPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBar());

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        JTextArea area = new JTextArea();
        area.setFont(FontCreator.getFontAharoni(24));
        area.setOpaque(false);
        area.setText(text);
        area.setEditable(false);
        area.setWrapStyleWord(true);
        area.setLineWrap(true);
        textPanel.add(area);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.weighty = 0.2;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;

        contentPanel.add(scrollPane, constraints);

        addComponents(options,1);
    }
    public void addComponents(List<String> options, int startingY){

        ImagePathFactory factory = new ImagePathFactory();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = startingY;

        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;


        String [] paths = factory.getButtonPaths();
        for (int i = 0; i<options.size(); i++){
            constraints.gridy = i + startingY;
            ImageButton button = new ImageButton(options.get(i));
            button.setImage(paths[i]);
            buttonList.add(button);
            contentPanel.add(button, constraints);
        }
    }
    public ArrayList<ImageButton> getButtonList() {
        return buttonList;
    }
    @Override
    public JPanel getContentPanel() {
        return contentPanel;
    }
}
