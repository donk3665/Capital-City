package Logic.MainTreeNodeLogic.TradingBranch.TradingOpponentNodeLogic;

import Entities.Game.Board;
import Entities.Game.Player;
import Entities.Game.Property;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

/**
 * This class represents the use case where a trade is initiated.
 */
public class TradingOpponentParentNodeUseCase extends TradingTreeNode {
    public TradingOpponentParentNodeUseCase() {
        super(NodeNames.TRADE_OPPONENT_PARENT, null);
    }
    String[] options = new String[]{"Accept", "Decline"};
    Player currentPlayer;
    Player tradingOpponent;

    /**
     * This method creates and returns a State object containing relevant information on the trade. This includes the
     * options of what the player can do after the initiation of a trade.
     * @return a State object including relevant information on the trade like the options of what the player can do
     * after the initiation of a trade.
     */
    @Override
    public State create_state() {
        State currentState = new State();
        currentPlayer =  getBoard().getPlayers().get(Integer.parseInt(getSelectedOptions().get(NodeNames.PICK_PLAYER)));
        tradingOpponent = getCurrentPlayer();

        currentState.setTradingOpponent(tradingOpponent);
        if (!GameNode.multiplayer) {
            currentState.addOptions(options);
        }

        Property currentPlayerProperty = tradingOpponent.getProperties().get(Integer.parseInt(getSelectedOptions().get(NodeNames.PICK_ITEM_SELF)));
        Property tradingOpponentProperty = currentPlayer.getProperties().get(Integer.parseInt(getSelectedOptions().get(NodeNames.PICK_ITEM_OPPONENT)));

        currentState.setCurrentPlayerProperty(currentPlayerProperty);
        currentState.setTradingPlayerProperty(tradingOpponentProperty);

        setReturnPlayerIndex(getCurrentPlayerIndex());
        //returnPlayerAddress will hold the original player index in this.board.getPlayers()
        setCurrentPlayer(currentPlayer);

        getCaseInteractor().getListener().writeIfMultiplayer("TRADE " + currentPlayer.getPlayerIndex() +" ¶"+  currentPlayerProperty.getName() + "¶"  + tradingOpponentProperty.getName());
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        if (input.getInput().equals(options[0])) {

            return getFactory().getNode(NodeNames.ACCEPT_TRADE,null);
        }
        else{

            return getFactory().getNode(NodeNames.DECLINE_TRADE,null);
        }
    }
}
