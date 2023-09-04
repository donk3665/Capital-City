package UseCases;

import Entities.Game.Player;
import Entities.Game.Property;

/**
 * Interface for the performAction method for property logic interactors
 */
public interface PropertyPerformActionUseCase {
    String performAction(Property property, Player player);

    String payExact(Property property, Player currentPlayer);
}
