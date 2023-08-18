package Logic.MainTreeNodeLogic.BankruptcyBranch;

import Entities.Game.Board;
import Entities.Game.Player;
import Entities.Game.Property;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.EndTurnBranch.FinishGameUseCase;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeNames;

import java.util.ArrayList;

/**
 * This class represents a use case when a player in the game is bankrupt.
 */
public class BankruptcyUseCase extends MainGameNode {
    String [] options = new String[]{"Yes","No"};
    public BankruptcyUseCase() {
        super(NodeNames.BANKRUPTCY, null);
    }

    /**
     * This method creates a State and returns it. The State object either contains options for confirmation or be one
     * representing the end of a tree path. The method also disconnects the bankrupt player from the game.
     * @return A State object either contains options for confirmation or be one representing the end of a tree path.
     */
    @Override
    public State create_state() {
        State currentState = new State();
        currentState.addOptions(options);
        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {

        if (input.getInput().equals(options[0])){
            mainStates[1] = 0;

            Player currentPlayer = getCurrentPlayer();
            Board board = getBoard();
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
                return getFactory().getNode(NodeNames.FINISH_GAME);
            }
        }
        return getMainParent();
    }

}
