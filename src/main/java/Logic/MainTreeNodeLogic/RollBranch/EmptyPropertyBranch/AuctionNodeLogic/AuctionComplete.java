package Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.AuctionNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeNames;

public class AuctionComplete extends AuctionTreeNode {
    public AuctionComplete(GameNode previousNode) {
        super(NodeNames.AUCTION_COMPLETE, previousNode);
    }

    @Override
    public State create_state() {
        State currentState = new State();
        currentState.setPlayerWon(playerWon);
        currentState.setBiddingPot(auctionStates[potIndex]);
        currentState.addOptions("Ok");

        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        return getMainParent();
    }
}
