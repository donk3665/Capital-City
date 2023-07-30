package GUI;

import Entities.GUI.Screens.GameScreen;
import Entities.GUI.Screens.Screen;
import Entities.GUI.Screens.ScreenFactory;
import Logic.GameLogic;
import Logic.NodeNames;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * GameDisplayInteractor is a class that creates the Display to show the user
 */
public class GameDisplayInteractor {

    /**
     * InstanceVar gameFrame: the Frame of the monopoly game
     * InstanceVar labelSegments: The OutputHandler for the Display
     * InstanceVar inputHandler: The InputHandler for the Display
     * InstanceVar selectedOutput: The String of the button that
     * is clicked by the user
     */
    private Screen gameScreen;
    private final GameDisplayInputInteractor inputHandler;
    private final GameDisplayOutputInteractor labelSegments;
    private final ScreenFactory screenFactory;
    private String selectedOutput;
    private GameLoop looper;

    /**
     * The constructor for this class, creates the frame and sets the permanent monopoly
     * background and fixes all its size and other properties
     */
    public GameDisplayInteractor(GameLoop looper){
        this.looper = looper;
        this.screenFactory = new ScreenFactory();
        this.inputHandler = new GameDisplayInputInteractor();
        this.labelSegments = new GameDisplayOutputInteractor(looper);
        this.selectedOutput = "";

    }

    /**
     * Function that gets the labelSegments instance variable of this class
     * @return the labelSegments of type GameDisplayOutputInteractor
     */
    public GameDisplayOutputInteractor getLabelSegments(){
        return this.labelSegments;
    }

    /**
     * Function that adds the outputs to the Display
     * @param options: The Arraylist of options that the user has
     * @param outputText: The String of Text shown to the user to
     *                  explain the current context of the game
     */
    public void setOutputs(NodeNames name, ArrayList<String> options, String outputText){
        gameScreen = screenFactory.getNode(name);
        this.labelSegments.setOptions(options);
        this.labelSegments.createOptionSegment();
        this.labelSegments.createTextSegment(outputText);

        //this.gameFrame.add(this.labelSegments.getTextSegment());
        //this.gameFrame.add(this.labelSegments.getOptionSegment());
        //TODO: UPDATE THIS
 //       this.labelSegments.drawSegments();
        GameScreen.setDescription(labelSegments.getDescriptionRaw());
        GameScreen.setOptions(labelSegments.getOptionSegmentRaw());
        gameScreen.initDisplay();


    }

    /**
     * Function to get the input from the user on the current turn
     * @return  if the user has clicked on the of buttons
     */
    public boolean waitForInput(){
        HashMap<String, Integer> buttonClicks = this.labelSegments.getButtonList();
        for (String button: buttonClicks.keySet()){
            if (buttonClicks.get(button) == 1){
                this.selectedOutput = button;
                this.labelSegments.updateButtonList();
                return true;
            }
        }
        return false;
    }

    /**
     * Function to clear the display outputs to create space for the next set of
     * outputs
     */
    public void clearLabels(){
        this.labelSegments.clearOptions();
        this.labelSegments.clearOptionSegment();
        this.labelSegments.clearTextSegment();
        this.labelSegments.clearInput();
    }

    /**
     * Function that displays the screen to the user, Program ends when window
     * is closed
     */
    public void displayScreen(){
        this.gameScreen.display();
    }

    /**
     * Refresh the screen for the next set of outputs
     */
    public void refreshScreen(){
        clearLabels();
        //this.gameScreen.remove(this.labelSegments.getTextSegment());
 //       gameScreen.removeComponents();
 //       this.gameScreen.remove(this.labelSegments.getOptionSegment());
 //       this.gameScreen.removeAll(this.labelSegments.getOptionSegmentRaw().getComponents());
        this.gameScreen.refresh();
    }

    /**
     * @return the input based on the button clicked
     */
    public String getInput(){
        return this.labelSegments.getOptions().get(this.inputHandler.getInputMapValue(this.selectedOutput));
    }

}
