package Logic.MainTreeNodeLogic.ManagePropertyBranch;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeNames;

import java.util.HashMap;

/**
 * This class represents a use case where the current player selects a property that it owns that will then be either
 * mortgaged, un-mortgaged, or have a house built on it.
 */
public class SelectActionPropertyUseCase extends MainGameNode {
    public SelectActionPropertyUseCase(GameNode previousNode) {
        super(NodeNames.SELECT_ACTION_PROPERTY, previousNode);
    }

    OrderedStringHashmap<NodeNames> options = new OrderedStringHashmap<>(){
        {
            put("MORTGAGE", NodeNames.MORTGAGE);
            put("UN-MORTGAGE", NodeNames.UN_MORTGAGE);
            put("BUILD_A_HOUSE", NodeNames.BUILD_PROPERTY);
        }
    };
    /**
     * This method creates a State object and adds the current player's selected option for which property it wants to
     * manage and adds the options to mortgage, un-mortgage, and to build a house to the State object.
     * @return A State object containing options to mortgage, un-mortgage, or build a house and the chosen property.
     */
    @Override
    public State create_state() {
        State currentState = new State();
        currentState.setBackEnable(true);

        //the player chooses what to do to the property
        currentState.addOptions(options.getKeys());

        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        return getFactory().getNode(options.get(input.getInput()));
    }
}