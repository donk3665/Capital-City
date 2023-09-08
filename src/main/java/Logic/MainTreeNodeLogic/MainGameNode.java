package Logic.MainTreeNodeLogic;

import Logic.GameNode;
import Logic.GeneralGameNode;
import Logic.NodeNames;

public abstract class MainGameNode extends GeneralGameNode {
    public MainGameNode(NodeNames name, GameNode previousNode) {
        super(name, previousNode);
    }
    /*
    mainStates ARRAY FORMATTING FOR SAVING:
    [0]: number of players
    [1]: game length (turn where game ends)
    [2]: game mode
    [3]: current turn
    [4]: current player
    [5]: Roll


    IN GAME mainStates ARRAY FORMATTING:
    [0]: Confirmation node
    [1]: Roll
    [2]: Game mode
    [3]: Turn counter
    [4]: Game Length
    [5]: consecutive rolls

     */

    public static void setStatesNotCurrentPlayer(){
        mainStates[0] = 0;
        mainStates[1] = 0;
        mainStates[5] = 0;
    }
    public static int[] mainStates = new int[6];
    public static String diceRoll;

    /**
     //     * When states are already known.
     //     * @param states - The states of the program from the loaded game
     //     */
    public static void initializeStates(int[] states){
        mainStates = states;
    }

    public static int[] getStates(){
        return mainStates;
    }




}
