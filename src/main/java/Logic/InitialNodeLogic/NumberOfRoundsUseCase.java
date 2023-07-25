package Logic.InitialNodeLogic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.NodeNames;

import java.util.HashMap;

/**
 * This class represents the use case where the player has chosen the number of players.
 */
public class NumberOfRoundsUseCase extends InitialGameNode {
    public NumberOfRoundsUseCase(GameNode previousNode) {
        super(NodeNames.NUMBER_OF_ROUNDS, previousNode);
    }
    HashMap <String, String> options = new HashMap<>(){
        {
            put("ROUNDS_30", "30");
            put("ROUNDS_60", "60");
            put("ROUNDS_90", "90");
            put("NO_LIMIT", "-1");
        }
    };
    @Override
    public State create_state() {
        State state = new State();
        //getting the number of players the user wants
        state.setBackEnable(true);

        state.addOptionsFromHashmap(options);

        return state;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        getSelectedOptions().put(getName(), options.get(input.getInput()));
        //        if (input != 3){
//            this.getSelectedOptions().put("GameLength", lengthMap.get(input) * getSelectedOptions().get("NumOfPlayers"));
//        }
//        else{
//            this.getSelectedOptions().put("GameLength",-1);
//        }
        return getFactory().getNode(NodeNames.CONFIRM_NEW_GAME, this);
    }
}
