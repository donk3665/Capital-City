package Logic.MainTreeNodeLogic.TradingBranch.TradingOpponentNodeLogic;

import Entities.Game.Board;
import Entities.Game.Player;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeNames;

/**
 * This class represents the use case where a player accepts a trade proposal from another player.
 */
public class AcceptTradeUseCase extends TradingTreeNode {

    public AcceptTradeUseCase() {
        super(NodeNames.ACCEPT_TRADE, null);
    }
    /**
     * This method handles the trade when it is accepted and moves their property items from one player to another.
     * It returns a State object containing an "Ok" option and all the necessary information to continue.
     * @return A state object representing the current state of the program
     */
    @Override
    public State create_state() {
        Board board = getBoard();

        State currentState = new State();
        Player firstTrader = board.getPlayers().get(getReturnPlayerIndex());

        setCurrentPlayer(firstTrader);

        //option for return node
        currentState.addOptions("Ok");
        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        return getMainParent();
    }
}
