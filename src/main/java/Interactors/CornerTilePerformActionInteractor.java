package Interactors;

import Entities.Game.CornerTiles;
import Entities.Game.Player;
import UseCases.CornerTilePerformActionUseCase;

/**
 * This class is an interactor for the performAction function in the case that a player lands on a corner tile.
 */
public class CornerTilePerformActionInteractor implements CornerTilePerformActionUseCase {
    private ServerListener listener;
    public CornerTilePerformActionInteractor(ServerListener listener){
        this.listener = listener;
    }

    /**
     * Take the player and the board and decide which action
     * should be performed on the cornerTiles according to its type.
     *
     * @param currentPlayer the player instance of the current player
     * @return The message associated with the action.
     */
    @Override
    public String performAction(Player currentPlayer, CornerTiles corner) {
        //TODO SOMETHING WITH LISTENER
        return corner.returnMessage(currentPlayer);
    }
}
