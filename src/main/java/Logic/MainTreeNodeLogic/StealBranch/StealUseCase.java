package Logic.MainTreeNodeLogic.StealBranch;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeNames;

import java.util.HashMap;


/**
 * This class represents a use case where a Player instance chooses to steal money from another Player instance.
 */
public class StealUseCase extends MainGameNode {

    public StealUseCase(GameNode previousNode) {
        super(NodeNames.STEAL, previousNode);
    }

    OrderedStringHashmap<Integer> options = new OrderedStringHashmap<>();

    /**
     * This method creates a States object and adds all the possible list of players that the current player can steal
     * from as options. Then it returns the State object.
     * @return A State object containing options which are a list of all possible that the current player can steal from.
     * This excludes the current player.
     */
    @Override
    public State create_state() {
        State currentState = new State();
        currentState.setBackEnable(true);
        //provide options of which players we can steal from
        for (int i = 0; i < getPlayers().size(); i++){
            String temp = getPlayers().get(i).getName();
            options.put(temp, i);
            currentState.addOptions(temp);
        }
        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        getSelectedOptions().put(this.getName(), String.valueOf(options.get(input.getInput())));
        return getFactory().getNode(NodeNames.PERFORM_STEAL, this);
    }
}