package Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.AuctionNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

/**
 * This class represents the use case when a player chooses to bid high in an auction.
 */
public class HighOptionUseCase extends AuctionTreeNode {

    public HighOptionUseCase() {
        super(NodeNames.HIGH_OPTION, null);
    }

    /**
     * This method a State object containing necessary information after the high bid is attempted.
     * @return A State object containing necessary information after the high bid is attempted.
     */
    @Override
    public State create_state() {
        return null;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        auctionStates[potIndex] += HIGH_OPTION;
        switchPlayersAuction();
        return getFactory().getNode(NodeNames.AUCTION_PARENT, this);
    }
}
