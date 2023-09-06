package GUI.Screens.ScreenElements;

import GUI.Screens.ContentPanelInterface;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SliderPanel implements ContentPanelInterface {

    private final JPanel contentPanel;
    private JSlider[] sliderArray;

    private ImageButton[] buttonArray;
    private GridBagConstraints constraints = new GridBagConstraints();
    public SliderPanel(List<String> sliders, List<String> options){
        contentPanel = new WeightlessPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setOpaque(false);
        sliderArray = new JSlider[sliders.size()];
        buttonArray = new ImageButton[options.size()];

        addComponents(sliders, options);
    }
    public void addComponent(Component component){
        contentPanel.add(component,constraints);
        constraints.gridy = constraints.gridy + 1;
        contentPanel.validate();
    }
    private void addComponents(List<String> sliders, List<String> options){
        ImagePathFactory factory = new ImagePathFactory();
        constraints.gridx = 0;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;

        String [] paths = factory.getButtonPaths();

        int yCounter = 0;
        for (int i = 0; i<sliders.size(); i++){
            constraints.gridy = yCounter;
            JLabel label =  new JLabel(sliders.get(i));
            label.setFont(FontCreator.getFontAharoni(24));
            contentPanel.add(label, constraints);
            yCounter ++;

            constraints.gridy = yCounter;
            sliderArray[i] = new JSlider();
            contentPanel.add(sliderArray[i], constraints);
            yCounter ++;
            constraints.gridy = yCounter;
        }
        for (int i = 0; i<options.size(); i++){
            constraints.gridy = yCounter;
            buttonArray[i] = new ImageButton(options.get(i));
            buttonArray[i].setImage(paths[i]);
            contentPanel.add(buttonArray[i], constraints);
            yCounter ++;
            constraints.gridy = yCounter;
        }
    }
    @Override
    public JPanel getContentPanel() {
        return contentPanel;
    }
    public ImageButton[] getButtonArray() {
        return buttonArray;
    }
    public JSlider[] getSliderArray() {
        return sliderArray;
    }


}
