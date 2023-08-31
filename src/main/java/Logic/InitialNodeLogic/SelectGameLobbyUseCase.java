package Logic.InitialNodeLogic;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

public class SelectGameLobbyUseCase extends InitialGameNode {

    public SelectGameLobbyUseCase(GameNode beforeNode) {
        super(NodeNames.SELECT_GAME_LOBBY, beforeNode);
    }

    OrderedStringHashmap<NodeNames> options = new OrderedStringHashmap<>(){
        {
            put("SINGLE_PLAYER", NodeNames.SELECT_GAME_TYPE);
            put("MULTIPLAYER", NodeNames.MULTIPLAYER_LOBBY);
        }
    };
    @Override
    public State create_state() {
        multiplayer = false;
        State currentState = new State();
        currentState.addOptions(options.getKeys());
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        return getFactory().getNode(options.get(input.getInput()), this);
    }

}