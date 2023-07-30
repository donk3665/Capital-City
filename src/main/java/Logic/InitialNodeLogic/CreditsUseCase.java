package Logic.InitialNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeNames;


public class CreditsUseCase extends InitialGameNode{
    public CreditsUseCase(GameNode previousNode) {
        super(NodeNames.CREDITS, previousNode);
    }
    @Override
    public State create_state() {
        return new State();
    }

    @Override
    public GameNode performInput(InputInformation input) {
        return null;
    }
}
