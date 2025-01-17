package Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.AuctionNodeLogic;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

import java.util.HashMap;

/**
 * This class represents the use case where an auction is started.
 */
public class AuctionParent extends AuctionTreeNode {
    public AuctionParent() {
        super(NodeNames.AUCTION_PARENT, null);
    }
    OrderedStringHashmap<NodeNames> options = new OrderedStringHashmap<>(){
        {
            put("LOW OPTION", NodeNames.LOW_OPTION);
            put("MEDIUM OPTION", NodeNames.MEDIUM_OPTION);
            put("HIGH OPTION", NodeNames.HIGH_OPTION);
            put("FOLD", NodeNames.FOLD);
        }
    };
    /**
     * This method creates a State object containing relevant information on the auction. This includes the player that
     * is to bid, the options to bid, the property that is being auctioned, and the bidding pot.
     * @return A State object containing relevant information on the auction.
     */
    @Override
    public State create_state() {
        State currentState = new State();
        currentState.addOptions(options.getKeys());
        currentState.setBiddingPot(auctionStates[potIndex]);
        currentState.setBiddingProperty(biddingProperty);
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        return getFactory().getNode(options.get(input.getInput()), this).performInput(input);
    }
}
