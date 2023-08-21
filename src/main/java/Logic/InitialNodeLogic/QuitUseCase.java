package Logic.InitialNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

public class QuitUseCase extends InitialGameNode{{
}
    String[] options = new String[]{
            "YES", "NO"
    };
    public QuitUseCase() {
        super(NodeNames.QUIT_INITIAL, null);
    }

    @Override
    public State create_state() {
        State currentState = new State();
        currentState.addOptions(options);
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        if (input.getInput().equals(options[0])){
            System.exit(0);
        }
        return getFactory().getNode(NodeNames.INITIAL_PARENT);
    }
}
