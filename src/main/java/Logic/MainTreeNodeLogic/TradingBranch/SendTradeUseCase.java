package Logic.MainTreeNodeLogic.TradingBranch;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeNames;

/**
 * This class represents a use case where the current user send a trade to the targeted player.
 */
public class SendTradeUseCase extends MainGameNode {

    public SendTradeUseCase(GameNode previousNode) {
        super(NodeNames.SEND_TRADE, previousNode);
    }

    String[] options = new String[]{"Yes", "No"};
    /**
     * This method creates a State
     * @return A State object containing either one that represents the end of a tree path, or one that contains
     * required to propose the trade.
     */
    @Override
    public State create_state() {
        State currentState = new State();
        currentState.addOptions(options);
        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {

        // if the input is 0, is if the trade is to be sent. Otherwise, the trade will not be sent.
        if (input.getInput().equals(options[0])) {
            return getFactory().getNode(NodeNames.TRADE_OPPONENT_PARENT, null);
        }
        else{
            return getFactory().getNode(NodeNames.MAIN_PARENT, null);
        }

    }
}