package Logic.InitialNodeLogic;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.NodeInterface;
import Logic.NodeNames;

public class JoinUseCase extends InitialGameNode {

    public JoinUseCase() {
        super(NodeNames.JOIN_GAME, null);
    }

    OrderedStringHashmap<NodeNames> options = new OrderedStringHashmap<>(){
        {
            put("Exit", NodeNames.INITIAL_PARENT);
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