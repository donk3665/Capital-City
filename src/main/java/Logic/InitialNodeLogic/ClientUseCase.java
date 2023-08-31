package Logic.InitialNodeLogic;


import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

public class ClientUseCase extends InitialGameNode {

    public ClientUseCase(GameNode beforeNode) {
        super(NodeNames.SELECT_GAME_CLIENT, beforeNode);
    }

        OrderedStringHashmap<NodeNames> options = new OrderedStringHashmap<>(){
        {
            put("JOIN", NodeNames.JOIN_GAME);

        }
    };
    @Override
    public State create_state() {
        State currentState = new State();
        currentState.addOptions(options.getKeys());
        getCaseInteractor().getListener().write("GET");
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        return getFactory().getNode(options.get(input.getInput()), this);
    }

}