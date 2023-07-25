package Logic.MainTreeNodeLogic.BankruptcyBranch;

import Entities.Game.Board;
import Entities.Game.Player;
import Entities.Game.Property;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeNames;

import java.util.ArrayList;

/**
 * This class represents a use case when a player in the game is bankrupt.
 */
public class BankruptcyUseCase extends MainGameNode {

    public BankruptcyUseCase(GameNode previousNode) {
        super(NodeNames.BANKRUPTCY, previousNode);
    }

    /**
     * This method creates a State and returns it. The State object either contains options for confirmation or be one
     * representing the end of a tree path. The method also disconnects the bankrupt player from the game.
     * @return A State object either contains options for confirmation or be one representing the end of a tree path.
     */
    @Override
    public State create_state() {
        State currentState = new State();
        Player currentPlayer = getCurrentPlayer();
        Board board = getBoard();

        if (mainStates[0] == 1) {
            mainStates[0] = 0;

            //removing all player connection with the board
            ArrayList<Property> currentPlayerProperties = currentPlayer.getProperties();
            for (Property targetedProperty : currentPlayerProperties) {
                targetedProperty.setOwner(null);
                targetedProperty.setHouses(0);
                targetedProperty.setMortgageStatus(false);
            }
            //switching the player before removing the original player
            changePlayers();
            board.removePlayer(currentPlayer);

            if (board.getPlayers().size() == 1){
                //go into node
                return currentState;
            }

            //changing the player and turning the state back to normal
            mainStates[0] = 0;
            mainStates[1] = 0;

        } else {
            //confirmation node setup
            currentState.addOptions("Yes");
            currentState.addOptions("No");
        }
        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        return getMainParent();
    }

}
