package Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.AuctionNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.Game.Player;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
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
    public GameNode performInput(InputInformation input) {

        List<Player> players = getPlayers();

        int returnPlayerIndex = getReturnPlayerIndex();

        //fold indicator
        auctionStates[getCurrentPlayerIndex()] = 1;
        auctionComplete = checkAuction();

        //when the auction is complete
        if (auctionComplete != -1){
            //give the winner their property
            getSelectedOptions().put(getName(), String.valueOf(auctionComplete));
            playerWon = players.get(auctionComplete);
            playerWon.addProperty(biddingProperty);
            playerWon.pay(auctionStates[potIndex]);


//          //return to the main tree and correct player
            setCurrentPlayer(players.get(returnPlayerIndex));

            return getFactory().getNode(NodeNames.AUCTION_COMPLETE, this);
        }
        switchPlayersAuction();
        return getFactory().getNode(NodeNames.AUCTION_PARENT, this);
    }
}
