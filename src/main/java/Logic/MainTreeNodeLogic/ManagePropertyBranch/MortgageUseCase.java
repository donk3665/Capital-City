package Logic.MainTreeNodeLogic.ManagePropertyBranch;

import Entities.Game.Player;
import Entities.Game.Property;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeInterface;
import Logic.NodeNames;
import Logic.PlayerLogic;

import java.util.HashMap;

/**
 * This class represents the use case where the current player chooses to mortgage a chosen property.
 */
public class MortgageUseCase extends MainGameNode {

    public MortgageUseCase(GameNode previousNode) {
        super(NodeNames.MORTGAGE, previousNode);
    }
    String [] options = new String[]{"Yes","No"};
    /**
     * This method creates a State object and updates it based on whether the current player chose to mortgage the
     * property or not. It will mortgage the property if the current player chooses to.
     * @return A State object that either has options for confirmation in the case where the mortgage was not conducted,
     * or the standards State object when the tree is now at its bottom node.
     */
    @Override
    public State create_state() {

        State currentState = new State();

        currentState.addOptions(options);

        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        Player currentPlayer = getCurrentPlayer();
        PlayerLogic currentPlayerLogic = new PlayerLogic(currentPlayer);
        HashMap<NodeNames, String> selectedOptions = getSelectedOptions();

        if (input.getInput().equals(options[0])){
            //the player chooses to mortgage the property
            Property targetProperty = currentPlayer.getProperties().get(Integer.parseInt(selectedOptions.get(NodeNames.SELECT_PROPERTY)));
            if (!targetProperty.getMortgageStatus()){
                currentPlayerLogic.mortgage(targetProperty);
            }
        }

        return getMainParent();
    }
}