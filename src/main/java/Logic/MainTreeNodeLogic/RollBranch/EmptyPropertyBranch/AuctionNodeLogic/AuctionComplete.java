package Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.AuctionNodeLogic;

import Entities.Game.Player;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

import java.util.List;

public class AuctionComplete extends AuctionTreeNode {
    public AuctionComplete() {
        super(NodeNames.AUCTION_COMPLETE,null);
    }

    @Override
    public State create_state() {
        State currentState = new State();
        currentState.setPlayerWon(playerWon);
        currentState.setBiddingPot(auctionStates[potIndex]);
        currentState.setBiddingProperty(biddingProperty);
        currentState.addOptions("Ok");


        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        List<Player> players = getPlayers();
        int returnPlayerIndex = getReturnPlayerIndex();
        //return to the main tree and correct player
        setCurrentPlayer(players.get(returnPlayerIndex));
        getCaseInteractor().getListener().writeIfMultiplayer("AUCTION DONE");
        return getMainParent();
    }
}
