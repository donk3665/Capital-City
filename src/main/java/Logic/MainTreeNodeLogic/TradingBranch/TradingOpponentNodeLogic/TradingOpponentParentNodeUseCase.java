package Logic.MainTreeNodeLogic.TradingBranch.TradingOpponentNodeLogic;

import Entities.Game.Board;
import Entities.Game.Player;
import Entities.Game.Property;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
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
        currentState.addOptions(options);
        currentState.setCurrentPlayerProperty(currentPlayer.getProperties().get(Integer.parseInt(getSelectedOptions().get(NodeNames.PICK_ITEM_SELF))));
        currentState.setTradingPlayerProperty(tradingOpponent.getProperties().get(Integer.parseInt(getSelectedOptions().get(NodeNames.PICK_ITEM_SELF))));

        setReturnPlayerIndex(getCurrentPlayerIndex());
        //returnPlayerAddress will hold the original player index in this.board.getPlayers()
        setCurrentPlayer(tradingOpponent);


        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        if (input.getInput().equals(options[0])) {
            Board board = getBoard();
            int returnPlayerIndex = getReturnPlayerIndex();

            Player firstTrader = board.getPlayers().get(returnPlayerIndex);
            Player secondTrader = getCurrentPlayer();
            Property secondTraderProperty = secondTrader.getProperties().get(Integer.parseInt(getSelectedOptions().get(NodeNames.PICK_ITEM_OPPONENT)));
            Property firstTraderProperty = firstTrader.getProperties().get(Integer.parseInt(getSelectedOptions().get(NodeNames.PICK_ITEM_OPPONENT)));

            // swap the asset owners
            secondTraderProperty.setOwner(firstTrader);
            firstTraderProperty.setOwner(secondTrader);
            secondTrader.getProperties().remove(secondTraderProperty);
            secondTrader.getProperties().add(firstTraderProperty);
            firstTrader.getProperties().remove(firstTraderProperty);
            firstTrader.getProperties().add(secondTraderProperty);
            // swap control back to the original player
            setCurrentPlayer(firstTrader);
            //goes back to initial tree
            setCurrentPlayer(getPlayers().get(returnPlayerIndex));


            return getFactory().getNode(NodeNames.ACCEPT_TRADE,null);
        }
        else{

            return getFactory().getNode(NodeNames.DECLINE_TRADE,null);
        }
    }
}
