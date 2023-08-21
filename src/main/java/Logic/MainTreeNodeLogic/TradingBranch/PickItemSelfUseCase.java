package Logic.MainTreeNodeLogic.TradingBranch;

import Entities.Game.OrderedStringHashmap;
import Entities.Game.Player;
import Entities.Game.Property;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents the use case where the current player has to choose an item that it owns.
 */
public class PickItemSelfUseCase extends MainGameNode {

    public PickItemSelfUseCase(GameNode previousNode) {
        super(NodeNames.PICK_ITEM_SELF, previousNode);
    }

    OrderedStringHashmap<Integer> options = new OrderedStringHashmap<>();
    /**
     * This method creates a State object and adds the selected option the current player chooses to it while also
     * up the State object for the following steps after choosing an item from its own inventory.
     * @return A State object containing the selected item and options for the following step after choosing the item.
     */
    @Override
    public State create_state() {
        Player currentPlayer = getCurrentPlayer();
        State currentState = new State();
        currentState.setBackEnable(true);

        //provide item options from the current player's inventory
        ArrayList<Property> currentPlayerInventory = currentPlayer.getProperties();
        //using "i" starting from 0 to number of properties the player has - 1
        for(int i = 0; i < currentPlayer.getProperties().size(); i++){
            String temp = currentPlayerInventory.get(i).getName();
            options.put(temp, i);
            currentState.addOptions(temp);
        }
        return currentState;

    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        getSelectedOptions().put(this.getName(), String.valueOf(options.get(input.getInput())));
        return getFactory().getNode(NodeNames.SEND_TRADE, this);
    }
}