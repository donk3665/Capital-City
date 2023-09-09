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
 * This class represents the use case where the current player chooses to un-mortgage a chosen property.
 */
public class UnMortgageUseCase extends MainGameNode {
    public UnMortgageUseCase(GameNode previousNode) {
        super(NodeNames.UN_MORTGAGE, previousNode);
    }

    /**
     * This method creates a State object and returns it. It contains relevant information of the state of the game
     * after the player's property is un-mortgaged.
     * @return a State object containing relevant information after a player un-mortgages their property.
     */
    @Override
    public State create_state() {

        Player currentPlayer = getCurrentPlayer();
        HashMap<NodeNames, String> selectedOptions = getSelectedOptions();
        PlayerLogic currentPlayerLogic = new PlayerLogic(currentPlayer);
        State currentState = new State();
        Property targetProperty = currentPlayer.getProperties().get(Integer.parseInt(selectedOptions.get(NodeNames.SELECT_ACTION_PROPERTY)));
        if (targetProperty.getMortgageStatus()){
            currentPlayerLogic.unmortgage(targetProperty);
            getCaseInteractor().getListener().writeIfMultiplayer("UN-MORTGAGE #1839673858#" + targetProperty.getName() + "#1839673858#");
        }
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        return getMainParent();
    }
}