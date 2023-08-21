package Logic.InitialNodeLogic;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

public class MultiplayerUseCase extends InitialGameNode {

    public MultiplayerUseCase(GameNode beforeNode) {
        super(NodeNames.MULTIPLAYER_LOBBY, beforeNode);
    }

//    OrderedStringHashmap<NodeNames> options = new OrderedStringHashmap<>(){
//        {
//            put("SINGLE_PLAYER", NodeNames.SELECT_GAME_TYPE);
//
//        }
//    };
    @Override
    public State create_state() {
        State currentState = new State();
 //       currentState.addOptions(options.getKeys());
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
//        return getFactory().getNode(options.get(input.getInput()), this);
        return null;
    }

}