package Logic.MainTreeNodeLogic.EndTurnBranch;

import Entities.Game.Player;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

/**
 * This class represents the use case when a player ends its turn.
 */
public class EndTurnUseCase extends MainGameNode {

    public EndTurnUseCase(GameNode previousNode) {
        super(NodeNames.END_TURN, previousNode);
    }
    GameNode node;
    /**
     * This method creates a State object that either helps to tell the user that it cannot end its turn or changes
     * the current player and makes the state go back to normal.
     * @return A State object containing either an "Ok" option or a State object corresponding to one at the end of a
     * tree path.
     */
    public State create_state(){
        State currentState = new State();
        Player currentPlayer = getCurrentPlayer();

        //end the turn if the person is not in debt
        if (currentPlayer.getMoney()  >= 0){
            mainStates[3] += 1;
            if (mainStates[3] >= mainStates[4] && mainStates[4] != -1){
                node = new FinishGameUseCase();
                return node.create_state();
            }
            //changing the player and turning the state back to normal
            changePlayers();
            mainStates[0] = 0;
            mainStates[1] = 0;
            node = getMainParent();
            getGameLogicInteractor().setCurrentNode(node);
            return getMainParent().create_state();
        }
        else{
            //option when the user cannot end their turn
            currentState.addOptions("Ok");
            node = null;
        }

        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        if (node != null) {
            return node.performInput(input);
        }
        else {
            return getMainParent();
        }
    }

}
