package Buttons;

import Entities.GUI.Screens.ScreenElements.ImageButton;
import GUI.GameLoop;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ButtonFactory is a class that creates all the button instances that need to be
 * displayed to the screen with their respective action handling
 */
public class ButtonFactory {
    /**
     * InstanceVar buttonStrings: an ArrayList of Strings that contains all the output
     * strings that need to be displayed on each button
     * InstanceVar actionPerformed: A Mapping between the button string and the number
     * of times the button was pressed
     * InstanceVar buttonMappings: an ArrayList of ButtonHandlers, which contains all the
     * different Button'x' objects to determine user input
     */
    private ArrayList<String> buttonStrings;
    private final HashMap<String, Integer> actionPerformed;
    private final ArrayList<ButtonMapping> buttonMappings;

    private GameLoop looper;
    /**
     * Constructor with no parameters for this class
     */
    public ButtonFactory (GameLoop looper){
        this.looper = looper;
        this.buttonStrings = new ArrayList<>();
        this.actionPerformed = new HashMap<>();
        this.buttonMappings = new ArrayList<>();
        addButtons();
    }
    public void addStrings(ArrayList<String> options){
        this.buttonStrings = options;
    }

    /**
     * Function that creates all the ButtonHandlers and adds them to the Arraylist
     * of ButtonHandlers
     */
    public void addButtons(){
        for (int i = 1; i<=16; i++){
            GeneralButton b = new GeneralButton("B"+i);
            buttonMappings.add(b);
        }
    }

    /**
     * Function that creates all the buttons, assigns their action listeners
     * @return the arraylist of JButtons
     */
    public ArrayList<ImageButton> getButtons(){
        ArrayList<ImageButton> returnedButtons = new ArrayList<>();

        for (int i = 0; i < this.buttonStrings.size(); i++){
            ImageButton temp = new ImageButton(this.buttonStrings.get(i) );
            int finalI = i;
            this.actionPerformed.put(this.buttonMappings.get(i).pressedButton(), 0);
            temp.addActionListener(e -> {
                        this.actionPerformed.replace(this.buttonMappings.get(finalI).pressedButton(), 1);
                        looper.gameLoop();
            }
            );
            returnedButtons.add(temp);
        }


        return returnedButtons;
    }

    /**
     * Function that returns the mapping of the buttons and how many times they are pressed
     * @return the hashmap of the buttons and number of times pressed
     */
    public HashMap<String, Integer> getActionPerformed() {
        return this.actionPerformed;
    }
}
