package TreeHandlers.MainTreeNodeLogic;

import Entities.Board;
import Entities.Player;
import Entities.State;
import Interactors.GameLogic;
import Interface.NodeLogic;

import java.util.ArrayList;

/**
 * This class represent the use case when a player has to be chosen.
 */
public class ChoosePlayerUseCase extends MainTreeNodeLogic implements NodeLogic {

    /**
     * This method creates makes the current player steal from a specific player specified by the input parameter. It
     * then returns a State object containing information about the steal attempt and information required to continue
     * the game.
     * @param input An integer representing the player that the current player wants to steal from.
     * @return a State object containing information about the steal attempt and information required to continue the
     * game.
     */
    public State create_state(int input){
        Board board = getBoard();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();

        ArrayList<Player> playerCopy = new ArrayList<Player>(board.getPlayers());
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        //Steal from the target player
        playerCopy = new ArrayList<Player>(board.getPlayers());
        playerCopy.remove(currentPlayer);
        String stealStatus = currentPlayer.steal(playerCopy.get(input));

        currentState.setDescription(stealStatus);
        currentState.addOptions("Ok");
        return currentState;
    }

}
