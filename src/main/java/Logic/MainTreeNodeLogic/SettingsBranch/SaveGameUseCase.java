package Logic.MainTreeNodeLogic.SettingsBranch;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeNames;


/**
 * This class represents the use case where the users of the game wants to save the game.
 */
public class SaveGameUseCase extends MainGameNode {

    public SaveGameUseCase(GameNode previousNode) {
        super(NodeNames.SAVE_GAME, previousNode);
    }

    /**
     * This method returns a State object containing the option to confirm the saving of the game and sets up the State
     * object with to prepare for the saving of the game.
     * @return A State object containing the added confirmation option to save the game and sets up some instance
     * attributes  to prepare for the save.
     */
    public State create_state(){

        State currentState = new State();

        currentState.addOptions("Ok");
        currentState.setSaveGame(true);
        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        return getFactory().getNode(NodeNames.SELECT_GAME_TYPE, null);
    }

}
