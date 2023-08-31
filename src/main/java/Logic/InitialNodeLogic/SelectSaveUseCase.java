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
    private String[] getSavesWithoutTemp(String[] saves){
        String [] returnStrings;
        returnStrings = new String[saves.length];
        for (String str: saves){
            if (str.equals("temp.txt")){
                returnStrings = new String[saves.length-1];
            }
        }
        int counter = 0;
        for (String save : saves) {
            if (!save.equals("temp.txt")) {
                returnStrings[counter] = save;
                counter++;
            }
        }

        return returnStrings;
    }

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
            state.addOptions(getSavesWithoutTemp(allSaves));
        }
        return state;
    }
    @Override
    public NodeInterface performInput(InputInformation input) {
        InitialGameNode.saveFile = input.getInput();
        getSelectedOptions().put(getName(), input.getInput());
        return getFactory().getNode(NodeNames.CONFIRM_LOADED_GAME, this);
    }
}
