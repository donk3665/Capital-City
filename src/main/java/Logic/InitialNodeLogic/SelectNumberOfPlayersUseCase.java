package Logic.InitialNodeLogic;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;

import Logic.NodeInterface;
import Logic.NodeNames;

import java.util.HashMap;

/**
 * This class represents the use case where the player has chosen their desired game mode.
 */
public class SelectNumberOfPlayersUseCase extends InitialGameNode {
    public SelectNumberOfPlayersUseCase(GameNode beforeNode) {
        super(NodeNames.SELECT_NUMBER_OF_PLAYERS, beforeNode);
    }

    OrderedStringHashmap<String> options = new OrderedStringHashmap<>(){
        {
            for (int i = 2; i < 9; i++){
                put("PLAYERS_"+i, String.valueOf(i));
            }
        }
    };
    @Override
    public State create_state() {
        State state = new State();
        //getting the mode the user wants
        state.setBackEnable(true);

        state.addOptions(options.getKeys());

        return state;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        getSelectedOptions().put(getName(), options.get(input.getInput()));
        return getFactory().getNode(NodeNames.NUMBER_OF_ROUNDS, this);
    }
}
