package Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.AuctionNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.Game.Player;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

import java.util.List;

/**
 * This class represents a use case when a player folds during an auction.
 */
public class FoldUseCase extends AuctionTreeNode {

    public FoldUseCase() {
        super(NodeNames.FOLD, null);
    }


    /**
     * This method handles a case where a player folds during an auction. It returns a State object containing
     * information required to continue the auction process.
     * @return A State object containing information required to continue the auction process.
     */
    @Override
    public State create_state() {
        return null;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {

        List<Player> players = getPlayers();

        //fold indicator
        auctionStates[getCurrentPlayerIndex()] = 1;
        auctionComplete = checkAuction();

        getCaseInteractor().getListener().writeIfMultiplayer("AUCTION FOLD");

        switchPlayersAuction();
        //when the auction is complete
        if (auctionComplete != -1){
            //give the winner their property
            getSelectedOptions().put(getName(), String.valueOf(auctionComplete));
            playerWon = players.get(auctionComplete);
            biddingProperty.setOwner(playerWon);
            playerWon.pay(auctionStates[potIndex]);

            getCaseInteractor().getListener().writeIfMultiplayer("AUCTION WON " + playerWon.getPlayerIndex());

            return getFactory().getNode(NodeNames.AUCTION_COMPLETE, this);
        }

        return getFactory().getNode(NodeNames.AUCTION_PARENT, this);
    }
}
