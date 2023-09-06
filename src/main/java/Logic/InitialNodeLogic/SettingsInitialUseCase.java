package Logic.InitialNodeLogic;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

import java.util.Objects;

public class SettingsInitialUseCase extends InitialGameNode{
    public SettingsInitialUseCase(GameNode previousNode) {
        super(NodeNames.SETTINGS_INITIAL, previousNode);
    }

    String[] options = new String[]{
            "SOUND"
    };

    @Override
    public State create_state() {
        State currentState = new State();
        currentState.addOptions(options);
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        return getFactory().getNode(NodeNames.SOUND_INITIAL, this);
    }
}
