package Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.AuctionNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

/**
 * This class represents the use case when a player chooses to make a middle bid in an auction.
 */
public class MediumOptionUseCase extends AuctionTreeNode {

    public MediumOptionUseCase() {
        super(NodeNames.MEDIUM_OPTION, null);
    }

    /**
     * This method a State object containing necessary information after the medium bid is attempted.
     * @return A State object containing necessary information after the medium bid is attempted.
     */
    @Override
    public State create_state() {
        return null;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        auctionStates[potIndex] += MEDIUM_OPTION;
        getCaseInteractor().getListener().writeIfMultiplayer("AUCTION MEDIUM");
        switchPlayersAuction();
        return getFactory().getNode(NodeNames.AUCTION_PARENT, this);
    }
}
