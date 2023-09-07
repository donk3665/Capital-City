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
        State currentState = new State();

        Board board = getBoard();
        int returnPlayerIndex = getReturnPlayerIndex();

        Player firstTrader = board.getPlayers().get(returnPlayerIndex);
        Player secondTrader = getCurrentPlayer();
        Property secondTraderProperty = secondTrader.getProperties().get(Integer.parseInt(getSelectedOptions().get(NodeNames.PICK_ITEM_OPPONENT)));
        Property firstTraderProperty = firstTrader.getProperties().get(Integer.parseInt(getSelectedOptions().get(NodeNames.PICK_ITEM_OPPONENT)));

        // swap the asset owners
        secondTraderProperty.setOwner(firstTrader);
        firstTraderProperty.setOwner(secondTrader);

//        secondTrader.getProperties().remove(secondTraderProperty);
//        secondTrader.getProperties().add(firstTraderProperty);
//        firstTrader.getProperties().remove(firstTraderProperty);
//        firstTrader.getProperties().add(secondTraderProperty);

        // swap control back to the original player
        setCurrentPlayer(firstTrader);
        //goes back to initial tree
        setCurrentPlayer(getPlayers().get(returnPlayerIndex));

        getCaseInteractor().getListener().writeIfMultiplayer("SWAP " + " #1839673858#" + firstTraderProperty.getName() + "#1839673858#"+ secondTraderProperty.getName());

        //option for return node
        currentState.addOptions("Ok");
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        return getMainParent();
    }
}
