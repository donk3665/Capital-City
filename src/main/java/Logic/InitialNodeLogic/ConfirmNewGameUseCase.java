package Logic.InitialNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeNames;

/**
 * This class represents the use case where the player has confirmed their choice of making a new
 * game.
 */
public class ConfirmNewGameUseCase extends InitialGameNode {
    public ConfirmNewGameUseCase() {
        super(NodeNames.CONFIRM_NEW_GAME, null);
    }

    @Override
    public State create_state() {
        State state = new State();

        //options associated with the next node
        state.addOptions("Yes");
        state.addOptions("No");
        return state;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        int[] states = new int[6];

        states[0] = Integer.parseInt(getSelectedOptions().get(NodeNames.SELECT_NUMBER_OF_PLAYERS));
        states[1] = Integer.parseInt(getSelectedOptions().get(NodeNames.NUMBER_OF_ROUNDS));
        states[2] = Integer.parseInt(getSelectedOptions().get(NodeNames.SELECT_GAME_MODE));
        states[3] = 0;
        states[4] = 0;
        states[5] = 0;
        caseInteractor.createNewGame(states);
        return getFactory().getNode(NodeNames.MAIN_PARENT, this);

    }
}
