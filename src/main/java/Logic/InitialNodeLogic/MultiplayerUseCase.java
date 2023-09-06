package Logic.InitialNodeLogic;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

public class MultiplayerUseCase extends InitialGameNode {
    //TODO: MAKE IT SO IT STALLS WHEN YOU CLICK MULTIPLAYER AND THE SERVER IS NOT UP

    public MultiplayerUseCase(GameNode beforeNode) {
        super(NodeNames.MULTIPLAYER_LOBBY, beforeNode);
    }

    OrderedStringHashmap<NodeNames> options = new OrderedStringHashmap<>(){
        {
            put("HOST", NodeNames.SELECT_GAME_TYPE);
            put("JOIN", NodeNames.SELECT_GAME_CLIENT);
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
        if (options.get(input.getInput()) == NodeNames.SELECT_GAME_TYPE){
            multiplayer = true;
        }
        return getFactory().getNode(options.get(input.getInput()), this);
    }

}