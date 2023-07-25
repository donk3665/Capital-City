package Logic.MainTreeNodeLogic.ManagePropertyBranch;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeNames;

/**
 * This class represents the use case where the targeted player does not have any properties.
 */
public class NoPropertiesUseCase extends MainGameNode {

    public NoPropertiesUseCase(GameNode previousNode) {
        super(NodeNames.NO_PROPERTIES, previousNode);
    }

    /**
     * This method creates a State object and sets a description showing that there are not properties available and
     * the required option, "Ok", to the State object.
     * @return A State object containing a description alerting the current player that are not properties available.
     * The returned State object also adds the "Ok" option.
     */
    @Override
    public State create_state() {
        State currentState = new State();
        currentState.addOptions("Ok");

        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        return getMainParent();
    }
}