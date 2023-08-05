package Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.AuctionNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
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
    public GameNode performInput(InputInformation input) {
        auctionStates[potIndex] += MEDIUM_OPTION;
        switchPlayersAuction();
        return getFactory().getNode(NodeNames.AUCTION_PARENT, this);
    }
}