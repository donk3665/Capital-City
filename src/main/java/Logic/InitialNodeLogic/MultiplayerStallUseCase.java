package Logic.InitialNodeLogic;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

public class MultiplayerStallUseCase extends InitialGameNode {

    public MultiplayerStallUseCase(GameNode beforeNode) {
        super(NodeNames.MULTIPLAYER_STALL, beforeNode);
    }


    @Override
    public State create_state() {
        State currentState = new State();
        currentState.setStartConnection(true);
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        return null;
    }

}