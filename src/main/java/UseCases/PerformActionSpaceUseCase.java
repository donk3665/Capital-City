package UseCases;

import Entities.Game.Player;
import Entities.Game.ActionSpace;
import Entities.Game.Board;

/**
 * Interface for the performAction method for action space logic interactors
 */
public interface PerformActionSpaceUseCase {
    
    String performAction(ActionSpace actionSpace, Player player, Board board);

}
