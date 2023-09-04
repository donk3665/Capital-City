package Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.AuctionNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

/**
 * This class represents the use case when a player chooses to bid low in an auction.
 */
public class LowOptionUseCase extends AuctionTreeNode {
    public LowOptionUseCase() {
        super(NodeNames.LOW_OPTION, null);
    }

    /**
     * This method a State object containing necessary information after the low bid is attempted.
     * @return A State object containing necessary information after the low bid is attempted.
     */
    @Override
    public State create_state() {
        return null;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        auctionStates[potIndex] += LOW_OPTION;
        getCaseInteractor().getListener().writeIfMultiplayer("AUCTION LOW");
        switchPlayersAuction();
        return getFactory().getNode(NodeNames.AUCTION_PARENT, this);
    }
}
