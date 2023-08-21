package Logic.MainTreeNodeLogic.RollBranch;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.MainTreeNodeLogic.MainParentNodeUseCase;
import Logic.NodeInterface;
import Logic.NodeNames;

/**
 * This class represents the use case where the player has already rolled the dice.
 */
public class AlreadyRolledUseCase extends MainGameNode {

    public AlreadyRolledUseCase() {
        super(NodeNames.ALREADY_ROLLED, null);
    }

    /**
     * This method creates a State object containing information required to proceed when the current player has already
     * rolled the dice.
     * @return A State object containing information required to proceed when the current player has already rolled the
     * dice.
     */
    @Override
    public State create_state() {
        State currentState = new State();
        currentState.addOptions("Ok");

        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        return new MainParentNodeUseCase();
    }
}