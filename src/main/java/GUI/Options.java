package GUI;

import GUI.Screens.ScreenElements.ImageButton;

import java.util.ArrayList;

/**
 * This class handles the section on the GUI for all the options the user has at a given state
 */
public class Options {


    public ArrayList<ImageButton> getButtons() {
        return buttons;
    }

    private ArrayList<ImageButton> buttons = new ArrayList<>();



    /**
     * The constructor for this class that sets the layout
     */
    public Options() {
    }

    /**
     * Function to add a component to the components arraylist
     * @param component: the component to be added
     */
    public void add(ImageButton component) {
        this.buttons.add(component);
    }

    /**
     * Function to clear the label for the next set of outputs
     */
    public void clearOptions() {
        this.buttons.clear();
    }


}
