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
    boolean [] sounds = new boolean[]{
            true,true
    };
    String[] options = new String[]{
            "MUSIC", "SFX"
    };

    @Override
    public State create_state() {
        State currentState = new State();
        currentState.addOptions(options);
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        for (int i = 0; i<2; i ++){
            if (Objects.equals(input.getInput(), options[0])){
                sounds[i] = !sounds[i];
            }
        }

        return this;
    }
}
