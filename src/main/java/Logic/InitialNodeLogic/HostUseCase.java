package Logic.InitialNodeLogic;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.NodeInterface;
import Logic.NodeNames;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class HostUseCase extends InitialGameNode {

    public HostUseCase() {
        super(NodeNames.HOST_GAME, null);
    }

        OrderedStringHashmap<NodeNames> options = new OrderedStringHashmap<>(){
        {
            //put("Start", NodeNames.MAIN_PARENT);
            put("Exit", NodeNames.INITIAL_PARENT);
        }
    };
    @Override
    public State create_state() {
        State currentState = new State();
        currentState.addOptions(options.getKeys());
        int maxNumPlayers;
        if (states[0] == -1){
            maxNumPlayers = 8;
        }
        else {
            maxNumPlayers = states[0];
        }
        getCaseInteractor().getListener().write("HOST " + maxNumPlayers + " " + states[1] + " " + states[2] + " " + savedGame);
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        return getFactory().getNode(options.get(input.getInput()), this);
    }

}