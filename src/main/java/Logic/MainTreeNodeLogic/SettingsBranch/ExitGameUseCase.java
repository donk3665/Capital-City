package Logic.MainTreeNodeLogic.SettingsBranch;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeNames;

/**
 * This class represents the use case where the users want to end the game.
 */
public class ExitGameUseCase extends MainGameNode {

    public ExitGameUseCase(GameNode previousNode) {
        super(NodeNames.EXIT_GAME, previousNode);
    }

    /**
     * This method creates a State
     * @return A State object containing options that confirm whether the users want to exit the game. The State object
     * also updates the State object to accommodate the game being exited.
     */
    public State create_state(){
        State currentState = new State();
        currentState.addOptions("Yes");
        currentState.addOptions("No");

        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        mainStates[0]= 0;
        return getFactory().getNode(NodeNames.SELECT_GAME_TYPE, null);
    }

}
