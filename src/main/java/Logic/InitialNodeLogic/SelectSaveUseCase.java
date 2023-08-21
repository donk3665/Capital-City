package Logic.InitialNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;
import Persistence.LoadAccess;
/**
 * This class represents the use case where the player has chosen to load a game.
 */
public class SelectSaveUseCase extends InitialGameNode {
    public SelectSaveUseCase(GameNode beforeNode) {
        super(NodeNames.SELECT_SAVE, beforeNode);
    }
    GameNode node;
    @Override
    public State create_state() {
        State state = new State();

        //options associated with the next node
        LoadAccess load = getCaseInteractor().getLoadAccess();

        String[] allSaves = load.checkSaves(load.getFile().getAbsolutePath());
        if (allSaves.length == 0){
            node = getFactory().getNode(NodeNames.NO_SAVES);
            state = node.create_state();
            getGameLogicInteractor().setCurrentNode(node);
        }
        else {
            state.addOptions(allSaves);
        }
        return state;
    }
    @Override
    public NodeInterface performInput(InputInformation input) {
        getSelectedOptions().put(getName(), input.getInput());
        return getFactory().getNode(NodeNames.CONFIRM_LOADED_GAME, this);
    }
}
