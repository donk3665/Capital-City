package GUIInteractors;

import GUI.Description;
import GUI.Options;
import GUI.Screens.Screen;
import GUI.Screens.ScreenElements.ImageButton;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that handles the output for the display
 */
public class GameDisplayOutputInteractor {
    /**
     * InstanceVar description: the section for the context shown to the user
     * InstanceVar optionSegment: the section for all the option buttons
     * <p>
     * InstanceVar options: an ArrayList of all the options on this state
     * InstanceVar buttonDisplayHandler: the handler of the buttons on the display
     */
    private final Description description;

    public Description getDescriptionRaw() {
        return description;
    }
    public Options getOptionSegmentRaw(){
        return optionSegment;
    }

    private final Options optionSegment;
    private ArrayList<String> options;

    private final ButtonDisplayInteractor buttonDisplayHandler;

    private GameLoop looper;
    /**
     * The constructor for this class
     */
    public GameDisplayOutputInteractor(GameLoop looper){
        this.looper = looper;
        this.optionSegment = new Options();
        this.description = new Description();
        this.options = new ArrayList<>();
        this.buttonDisplayHandler = new ButtonDisplayInteractor(looper);
    }

    /**
     * Function to get the options for this state
     * @return the ArrayList that contains the options for the current state
     */
    public ArrayList<String> getOptions() {
        return this.options;
    }

    /**
     * Function to set options for this state
     * @param options: An ArrayList of the options for the current state
     */
    public void setOptions(ArrayList<String> options){
        this.options = options;
    }

    /**
     * Function to clear the ArrayList for the next states options
     */
    public void clearOptions(){
        this.options.clear();
    }

    /**
     * Function to set up the option segment through the creation of all the option
     * buttons
     */
    public void createOptionSegment(){
        this.buttonDisplayHandler.setButtonFactory(this.options);
        for (ImageButton button: this.buttonDisplayHandler.getButtons()){
            button.addActionListener(e->{
                Screen.getSoundController().playClip(0);
            });
            this.optionSegment.add(button);
        }

    }


    /**
     * Clear the option segment for the next state
     */
    public void clearOptionSegment(){
        this.optionSegment.clearOptions();
    }

    /**
     * Create the text segment to show the context to the user
     * @param textOutput the context String
     */
    public void createTextSegment(String textOutput) {
        this.description.setDescription(textOutput);
    }

    /**
     * Function to return the text segment
     * @return the JLabel that contains the context of the state
     */
    public JLabel getTextSegment(){
        return this.description.getDescription();
    }

    /**
     * Function that clears the text segment for the next state
     */
    public void clearTextSegment(){
        this.description.clear();
    }

    /**
     * function to return the mapping between the buttons and how many times they are pressed
     * @return the mapping
     */
    public HashMap<String, Integer> getButtonList(){
        return this.buttonDisplayHandler.getButtonList();
    }

    /**
     * Function to update the current status of how many times each button was pressed
     */
    public void updateButtonList(){
        this.buttonDisplayHandler.updateClicks();
    }

    /**
     * Clear the mapping between the buttons and number of times pressed for the next state
     */
    public void clearInput(){
        this.buttonDisplayHandler.resetInputs();
    }
}
