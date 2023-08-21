package Logic.InitialNodeLogic;

import Entities.Game.Board;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

import java.io.IOException;

/**
 * This class represents the use case where the player has chosen a save file to load.
 */
public class ConfirmLoadedGameUseCase extends InitialGameNode {
    public ConfirmLoadedGameUseCase() {
        super(NodeNames.CONFIRM_LOADED_GAME, null);
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
    public NodeInterface performInput(InputInformation input) {
        if (input.getInput().equals("Yes")){

            String loadFile = getSelectedOptions().get(NodeNames.SELECT_SAVE);
            Board board = null;
            int[] initialStates = new int[0];

            try  {
                board = caseInteractor.loadSavedBoard(loadFile);
                initialStates = caseInteractor.loadInitialStates(loadFile);
            }
            catch (IOException exception){
                System.err.println("IO error");
                System.exit(1);
            }
            caseInteractor.createGame(board, initialStates);
            return getFactory().getNode(NodeNames.MAIN_PARENT, this);
        }
        else{
            return getFactory().getNode(NodeNames.SELECT_GAME_TYPE);
        }

    }
}
