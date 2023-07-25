package Logic.InitialNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeNames;

public class NoSavesUseCase extends InitialGameNode{
    public NoSavesUseCase() {
        super(NodeNames.NO_SAVES, null);
    }

    @Override
    public State create_state() {
        State currentState = new State();

        currentState.addOptions("ok");
        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        return getFactory().getNode(NodeNames.SELECT_GAME_TYPE);
    }
}
