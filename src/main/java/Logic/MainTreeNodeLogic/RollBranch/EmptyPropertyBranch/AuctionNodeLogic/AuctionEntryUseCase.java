package Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.AuctionNodeLogic;


import Entities.Game.Board;
import Entities.Game.Player;
import Entities.Game.Property;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeNames;

/**
 * This class represents the use case of when an auction is to be started.
 */
public class AuctionEntryUseCase extends AuctionTreeNode {
    public AuctionEntryUseCase(GameNode previousNode) {
        super(NodeNames.AUCTION_ENTRY, previousNode);
    }
    GameNode tempNode;
    /**
     * This method returns a State object containing the original current player and the original tree node that the
     * game was on. It will then change the trees and start the auction.
     * @return A State object containing the current player and the node that the game was in prior to setting up the
     * auction.
     */
    @Override
    public State create_state() {
        initialize();
        Board board = getBoard();
        Player currentPlayer = getCurrentPlayer();
        tempNode = getFactory().getNode(NodeNames.AUCTION_PARENT, this);
        //returnPlayerAddress will hold the original player index in this.board.getPlayers()
        biddingProperty = (Property) board.getCell(currentPlayer.getPosition());
        setReturnPlayerIndex(getCurrentPlayerIndex());
        getGameLogicInteractor().setCurrentNode(tempNode);

        return tempNode.create_state();
    }

    @Override
    public GameNode performInput(InputInformation input) {
        return tempNode.performInput(input);
    }
}