package Logic.InitialNodeLogic;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

public class InitialParentUseCase extends InitialGameNode {

    public InitialParentUseCase() {
        super(NodeNames.INITIAL_PARENT, null);
    }

    OrderedStringHashmap<NodeNames> options = new OrderedStringHashmap<>(){
        {
            put("PLAY", NodeNames.SELECT_GAME_LOBBY);
            put("SETTINGS", NodeNames.SETTINGS_INITIAL);
            put("CREDITS", NodeNames.CREDITS);
            put("QUIT", NodeNames.QUIT_INITIAL);
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