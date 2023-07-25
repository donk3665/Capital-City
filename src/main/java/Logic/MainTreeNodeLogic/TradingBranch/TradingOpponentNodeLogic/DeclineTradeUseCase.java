package Logic.MainTreeNodeLogic.TradingBranch.TradingOpponentNodeLogic;

import Entities.Game.Board;
import Entities.InternalDataTransfer.InputInformation;
import Entities.Game.Player;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeNames;

/**
 * This class represents the use case when a player declines a trade proposal from another player.
 */
public class DeclineTradeUseCase extends TradingTreeNode {

    public DeclineTradeUseCase() {
        super(NodeNames.DECLINE_TRADE,null);
    }

    /**
     * This method reverts the game to its previous state before the trade was attempted. It returns a State object
     * containing information to continue the game.
     * @return State object containing information to continue the game.
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
