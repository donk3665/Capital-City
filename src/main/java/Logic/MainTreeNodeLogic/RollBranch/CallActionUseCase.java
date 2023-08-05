package Logic.MainTreeNodeLogic.RollBranch;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.MainTreeNodeLogic.MainParentNodeUseCase;
import Logic.NodeNames;

/**
 * This class represents a use case after an action card is drawn.
 */
public class CallActionUseCase extends MainGameNode {
    public CallActionUseCase() {
        super(NodeNames.CALL_ACTION, null );
    }

    /**
     * This method creates and returns a State object containing relevant information on the action called.
     * @return a State object containing relevant information on the action called and options for the user to choose
     * from.
     */
    @Override
    public State create_state() {
        State currentState = new State();
        String answer = getAnswer();

        //gets the response from rolling on a space
        currentState.setRoll(diceRoll);
        currentState.setDescription(answer);
        currentState.addOptions("Ok");
        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        return new MainParentNodeUseCase();
    }
}