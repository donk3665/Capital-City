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
    public ConfirmLoadedGameUseCase(GameNode previousNode) {
        super(NodeNames.CONFIRM_LOADED_GAME, null);
        this.previousNode = previousNode;
    }
    private GameNode previousNode;
    @Override
    public State create_state() {
        savedGame = true;
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
            states = null;

            try  {
                loadBoardAndStates(loadFile);
            }
            catch (IOException exception){
                System.err.println("IO error");
                System.exit(1);
            }

            if (multiplayer){
                return getFactory().getNode(NodeNames.HOST_GAME, this);
            }
            createGame();
            return getFactory().getNode(NodeNames.MAIN_PARENT, this);
        }
        else{
            return previousNode;
        }

    }
}
