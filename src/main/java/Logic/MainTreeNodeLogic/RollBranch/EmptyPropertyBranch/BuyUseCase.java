package Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch;

import Entities.Game.Board;
import Entities.Game.Player;
import Entities.Game.Property;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.GeneralGameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.MainTreeNodeLogic.MainParentNodeUseCase;
import Logic.NodeInterface;
import Logic.NodeNames;

/**
 * This class represents the use case for when the current player wants to buy a property.
 */
public class BuyUseCase extends MainGameNode {

    public BuyUseCase() {
        super(NodeNames.BUY_PROPERTY, null);
    }

    /**
     * This method goes through the steps for the current player to buy the house if the current player has sufficient
     * funds and returns a State object with the necessary information on the game. If the purchase was successful, it
     * indicates the end of the tree path.

     * @return A State object containing the necessary information on the game. If the purchase was successful it will
     * indicate the end of a tree path.
     */
    @Override
    public State create_state() {

        Player currentPlayer = getCurrentPlayer();
        State currentState = new State();

        GeneralGameNode.buyProperty(currentPlayer);

        currentState.addOptions("ok");

        getCaseInteractor().getListener().writeIfMultiplayer("INFO: BUY ");

        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        return new MainParentNodeUseCase();
    }
}