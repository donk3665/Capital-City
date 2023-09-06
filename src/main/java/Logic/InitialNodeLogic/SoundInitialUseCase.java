package Logic.InitialNodeLogic;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

import java.util.Objects;

public class SoundInitialUseCase extends InitialGameNode{
    public SoundInitialUseCase(GameNode previousNode) {
        super(NodeNames.SOUND_INITIAL, previousNode);
    }

//    String[] options = new String[]{
//            "BACK"
//    };

    @Override
    public State create_state() {
        State currentState = new State();
        //currentState.addOptions(options);
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        //return getFactory().getNode(NodeNames.SETTINGS_INITIAL);
        return null;
    }
}
