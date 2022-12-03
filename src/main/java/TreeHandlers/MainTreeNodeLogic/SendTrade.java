package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

import java.util.HashMap;

/**
 * This class represents a use case where the current user send a trade to the targeted player.
 */

public class SendTrade extends MainTreeNodeLogic implements NodeLogic {

    /**
     * This method creates a State
     * @param input An integer representing if the trade is to be sent. 0 means that the trade will be sent over.
     * @return A State object containing either one that represents the end of a tree path, or one that contains
     * required to propose the trade.
     */
    @Override
    public State create_state(int input) {
        Board board = getBoard();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
        HashMap<String, Integer> selectedOptions = getSelectedOptions();

        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        if (input == 0) {
            Player tradingOpponent = board.getPlayers().get(selectedOptions.get("PickPlayer"));
            currentState.setTradingOpponent(tradingOpponent);
            currentState.setPlayer(currentPlayer);
            currentState.setCurrentPlayerProperty(currentPlayer.getProperties().get(selectedOptions.get("PickItemSelf")));
            currentState.setTradingPlayerProperty(tradingOpponent.getProperties().get(selectedOptions.get("PickItemOp")));
            setReturnTree(currentTree);
            setReturnPlayerIndex(getCurrentPlayerIndex());
            //returnPlayerAddress will hold the original player index in this.board.getPlayers()
            setCurrentPlayer(tradingOpponent);
            gameLogicInteractor.setCurrentTree(gameLogicInteractor.getTrees()[1]);
            addSwitchOptions(currentState);
        }
        else{
            currentState = afterBottomNode();
        }
        return currentState;
    }
}