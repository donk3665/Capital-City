package Logic.MainTreeNodeLogic.TradingBranch;

import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

import java.util.HashMap;


/**
 * This class represents a use case where two Player instances trade with each other. The class handles the actions
 * required to successfully set up a trade.
 */
public class PickPlayerUseCase extends MainGameNode {

    public PickPlayerUseCase(GameNode previousNode) {
        super(NodeNames.PICK_PLAYER, previousNode);
    }
    OrderedStringHashmap<Integer> options = new OrderedStringHashmap<>();
    /**
     * This method creates a State object and adds all the possible list of player that the current player can trade
     * with into the State object that was created and returns it.
     * @return A State object containing options which are a list of all possible players that the current player can
     * trade with. This excludes the current player.
     */
    @Override
    public State create_state() {
        State currentState = new State();
        currentState.setBackEnable(true);
        //provide a list of all possible players considering the current player is not an option
        for (int i = 0; i < getPlayers().size(); i++){
            if (i != getCurrentPlayerIndex()) {
                String temp = getPlayers().get(i).getName();
                options.put(temp, i);
                currentState.addOptions(temp);
            }
        }
        return currentState;

    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        getSelectedOptions().put(this.getName(), String.valueOf(options.get(input.getInput())));
        return getFactory().getNode(NodeNames.PICK_ITEM_OPPONENT, this);
    }
}
