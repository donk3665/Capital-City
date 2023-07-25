package Logic.MainTreeNodeLogic.TradingBranch;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeNames;

/**
 *  This class represents a use case where the current player has nothing to trade.
 */
public class NothingToTradeUseCase extends MainGameNode {
    public NothingToTradeUseCase(GameNode previousNode) {
        super(NodeNames.NOTHING_TO_TRADE, previousNode );
    }

    /**
     * This method creates and returns a State object containing information required to proceed in the game when the
     * current player has nothing to trade.
     * @return A State object containing the options and other information required to proceed with the game when the
     * current player has nothing to trade.
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
