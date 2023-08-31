package GUI;

import Entities.GUI.Screens.Screen;
import Entities.GUI.Screens.ScreenFactory;
import Entities.GUIDataTransfer.GUIInterface;
import Interactors.ServerListener;

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

    /**
     * The constructor for this class, creates the frame and sets the permanent monopoly
     * background and fixes all its size and other properties
     */
    public GameDisplayInteractor(GameLoop looper){
        this.screenFactory = new ScreenFactory();
        this.inputHandler = new GameDisplayInputInteractor();
        this.labelSegments = new GameDisplayOutputInteractor(looper);
        this.selectedOutput = "";

    }
    public ScreenFactory getScreenFactory(){
        return screenFactory;
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
     * @param state: The interface which the GUI has access to
     * @param outputText: The String of Text shown to the user to
     *                  explain the current context of the game
     */
    public void setOutputs(GUIInterface state, String outputText){
        gameScreen = screenFactory.getNode(state.getId());
        this.labelSegments.setOptions(state.getOptions());
        this.labelSegments.createOptionSegment();
        this.labelSegments.createTextSegment(outputText);

        Screen.setDescription(labelSegments.getDescriptionRaw());
        Screen.setOptions(labelSegments.getOptionSegmentRaw());
        Screen.setCurrentBoard(state.getCurrentBoard());
        Screen.setCurrentPlayer(state.getPlayer());
        Screen.setClientPlayer(state.getClientPlayer());
        Screen.setState(state);

        gameScreen.attachNonStaticComponents();
        gameScreen.attachPanel();
    }
    public void refreshTurnChangeOptions(){
        gameScreen.attachNonStaticComponents();
    }
    public void setScreenListener(ServerListener listener){
        Screen.setListener(listener);
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
        this.gameScreen.refresh();
    }

    /**
     * @return the input based on the button clicked
     */
    public String getInput(){
        return this.labelSegments.getOptions().get(this.inputHandler.getInputMapValue(this.selectedOutput));
    }

}
