package Logic.MainTreeNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeNames;

import java.util.HashMap;

/**
 * This class represents the use case where the game is in a non-auction or non-trading state.
 */
public class MainParentNodeUseCase extends MainGameNode {
    public MainParentNodeUseCase() {
        super(NodeNames.MAIN_PARENT, null);
    }
    HashMap<String, NodeNames> options = new HashMap<>(){
        {
            put("TRADE", NodeNames.TRADE);
            put("MANAGE_PROPERTY", NodeNames.SELECT_PROPERTY);
            put("ROLL", NodeNames.ROLL);
            put("STEAL", NodeNames.STEAL);
            put("END_TURN", NodeNames.END_TURN);
            put("SETTINGS", NodeNames.SETTINGS);
            put("BANKRUPTCY", NodeNames.BANKRUPTCY);
        }
    };

    /**
     * This method creates a State object containing relevant information of the current state of the game including
     * the current player and the options of what the player can do.
     * @return A State object containing options available of what the current player can do.
     */
    @Override
    public State create_state() {
        State currentState = new State();
        currentState.addOptions(options.keySet().toArray(new String[0]));
        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        return getFactory().getNode(options.get(input.getInput()), this);
    }
}
