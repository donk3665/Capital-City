package Logic.MainTreeNodeLogic.StealBranch;

import Entities.InternalDataTransfer.InputInformation;
import Entities.Game.Player;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeInterface;
import Logic.NodeNames;
import Logic.PlayerLogic;

/**
 * This class represent the use case when a player has to be chosen.
 */
public class PerformStealUseCase extends MainGameNode {

    public PerformStealUseCase(GameNode previousNode) {
        super(NodeNames.PERFORM_STEAL, previousNode);
    }

    /**
     * This method creates makes the current player steal from a specific player specified by the input parameter. It
     * then returns a State object containing information about the steal attempt and information required to continue
     * the game.
     * @return a State object containing information about the steal attempt and information required to continue the
     * game.
     */
    public State create_state(){
        Player currentPlayer = getCurrentPlayer();

        State currentState = new State();
        //Steal from the target player
        Player stealPlayer = getBoard().getPlayers().get(Integer.parseInt(getSelectedOptions().get(NodeNames.STEAL)));

        PlayerLogic playerLogic = new PlayerLogic(currentPlayer);

        String stealStatus = playerLogic.steal(stealPlayer);
        currentState.setDescription(stealStatus);
        currentState.addOptions("Ok");
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        return getMainParent();
    }

}
