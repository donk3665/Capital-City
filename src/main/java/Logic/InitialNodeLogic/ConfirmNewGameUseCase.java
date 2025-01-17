package Logic.InitialNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

/**
 * This class represents the use case where the player has confirmed their choice of making a new
 * game.
 */
public class ConfirmNewGameUseCase extends InitialGameNode {
    public ConfirmNewGameUseCase(GameNode previousNode) {
        super(NodeNames.CONFIRM_NEW_GAME, null);
        this.previousNode = previousNode;
    }


    private GameNode previousNode;
    @Override
    public State create_state() {
        savedGame = false;
        State state = new State();

        //options associated with the next node
        state.addOptions("Yes");
        state.addOptions("No");
        return state;
    }



    @Override
    public NodeInterface performInput(InputInformation input) {
        if (input.getInput().equals("Yes")) {
            states = new int[7];

            states[0] = Integer.parseInt(getSelectedOptions().get(NodeNames.SELECT_NUMBER_OF_PLAYERS));
            states[1] = Integer.parseInt(getSelectedOptions().get(NodeNames.NUMBER_OF_ROUNDS));
            states[2] = Integer.parseInt(getSelectedOptions().get(NodeNames.SELECT_GAME_MODE));
            states[3] = 0;
            states[4] = 0;
            states[5] = 0;
            states[6] = 0;
           // System.err.println(states);
            if (multiplayer){
                //states[0] = -1;
                return getFactory().getNode(NodeNames.HOST_GAME, this);
            }
            createNewBoard();
            createGame();
            return getFactory().getNode(NodeNames.MAIN_PARENT, this);
        }
        else {
            return previousNode;
        }
    }
}
