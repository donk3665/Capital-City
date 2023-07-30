package Logic.MainTreeNodeLogic.SettingsBranch;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeNames;

import java.util.HashMap;

/**
 * This class represents a use case where the settings menu is to be set up.
 */
public class SettingsMenuUseCase extends MainGameNode {

    public SettingsMenuUseCase(GameNode previousNode) {
        super(NodeNames.SETTINGS, previousNode);
    }
    OrderedStringHashmap<NodeNames> options = new OrderedStringHashmap<>(){
        {
            put("SAVE", NodeNames.SAVE_GAME);
            put("EXIT_GAME", NodeNames.EXIT_GAME);

        }
    };

    /**
     * This method creates a State object for the initialization of the settings menu.
     * @return A State object set up for the display of the settings menu.
     */
    public State create_state(){
        State currentState = new State();
        currentState.setBackEnable(true);

        currentState.addOptions(options.getKeys());

        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        return getFactory().getNode(options.get(input.getInput()), this);
    }

}
