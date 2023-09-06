package Logic.InitialNodeLogic;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

/**
 * This class represents the use case where the game has first initialized.
 */
public class SelectGameTypeUseCase extends InitialGameNode {
    public SelectGameTypeUseCase(GameNode previousNode) {
        super(NodeNames.SELECT_GAME_TYPE, previousNode);
    }

    OrderedStringHashmap<NodeNames> options = new OrderedStringHashmap<>(){
        {
            put("NEW_GAME", NodeNames.SELECT_GAME_MODE);
            put("OLD_GAME", NodeNames.SELECT_SAVE);
        }
    };
    @Override
    public State create_state() {
        State currentState = new State();
        currentState.addOptions(options.getKeys());
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        return getFactory().getNode(options.get(input.getInput()), this);
    }

}
