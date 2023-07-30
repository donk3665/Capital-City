package Logic.InitialNodeLogic;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeNames;

import java.util.HashMap;

/**
 * This class represents the use case where the player has chosen to play a new game.
 */
public class SelectGameModeUseCase extends InitialGameNode {
    public SelectGameModeUseCase(GameNode beforeNode) {
        super(NodeNames.SELECT_GAME_MODE, beforeNode);
    }
    OrderedStringHashmap<String> options = new OrderedStringHashmap<>(){
        {
            put("NORMAL_MODE", "0");
            put("RICH_MODE", "1");
        }
    };
    @Override
    public State create_state() {
        State state = new State();
        //getting if the user wants to play a new game
        state.setBackEnable(true);

        //options associated with the next node
        state.addOptions(options.getKeys());
        return state;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        getSelectedOptions().put(getName(), options.get(input.getInput()));
        return getFactory().getNode(NodeNames.SELECT_NUMBER_OF_PLAYERS, this);
    }
}
