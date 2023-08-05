package Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.AuctionNodeLogic;

import Entities.Game.Player;
import Entities.Game.Property;
import Logic.GameNode;
import Logic.GeneralGameNode;
import Logic.NodeNames;

import java.util.List;

/**
 * This class handles the logic for the auction tree nodes.
 */
public abstract class AuctionTreeNode extends GeneralGameNode {
    final int LOW_OPTION = 20;
    final int MEDIUM_OPTION = 80;
    final int HIGH_OPTION = 160;
    static int[] auctionStates;
    static Player playerWon;
    static Property biddingProperty;
    static int potIndex;

    static int auctionComplete = -1;

    public AuctionTreeNode(NodeNames name, GameNode previousNode) {
        super(name, previousNode);
    }


//    public static void array_init(int playerLength){
//        auctionStates = new int[playerLength + 1];
//    }

    /**
     * Check if the auction is complete
     * @return an integer representing the index of the player in the array who won.
     * if no one has won, return -1.
     */
    public int checkAuction(){
        int auctionFinishedCounter = 0;
        int playerIndex = 0;

        //check if everyone but one player has folded
        for (int i = 0; i<auctionStates.length - 1; i++){
            if (auctionStates[i] == 0){
                auctionFinishedCounter += 1;
                playerIndex = i;
            }
        }
        if (auctionFinishedCounter == 1){
            return playerIndex;
        }
        return -1;
    }

    /**
     * Initialize this tree to prepare for an auction
     */
    public void initialize() {
        List<Player> players = getPlayers();
        auctionComplete = -1;
        auctionStates = new int[players.size() + 1];
        potIndex = auctionStates.length - 1;
    }


    public void switchPlayersAuction(){
        int playerNum = getCurrentPlayerIndex();
        do {
            playerNum = (playerNum + 1) % getPlayers().size();
        } while (auctionStates[playerNum] != 0);
        setCurrentPlayer(getPlayers().get(playerNum));
    }
}