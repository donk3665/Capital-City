package Logic.InitialNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeNames;

/**
 * This class represents the use case where the game has first initialized.
 */
public class SelectGameTypeUseCase extends InitialGameNode {
    public SelectGameTypeUseCase() {
        super(NodeNames.SELECT_GAME_TYPE, null);
    }

    @Override
    public State create_state() {
        State currentState = new State();
        currentState.addOptions("NEW_GAME", "OLD_GAME");
        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        switch(input.getInput()){
            case "NEW_GAME" -> {
                return getFactory().getNode(NodeNames.SELECT_GAME_MODE, this);
            }
            case "OLD_GAME" -> {
                return getFactory().getNode(NodeNames.SELECT_SAVE, this);
            }
        }
        System.err.println("ERROR IN "+ this.getName().toString());
        System.exit(1);
        return null;
    }

}
