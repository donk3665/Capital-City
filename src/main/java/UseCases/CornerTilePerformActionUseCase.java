package UseCases;

import Entities.Game.Player;
import Entities.Game.CornerTiles;
/**
 * Interface for the performAction method for corner tiles logic interactors
 */
public interface CornerTilePerformActionUseCase {
    String performAction(Player currentPlayer, CornerTiles corner);
}
