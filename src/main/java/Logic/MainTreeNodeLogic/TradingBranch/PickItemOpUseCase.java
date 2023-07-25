package Logic.MainTreeNodeLogic.TradingBranch;

import Entities.Game.Player;
import Entities.Game.Property;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeNames;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents the use case where the current player has to choose an item of a targeted opponent's
 * inventory.
 */
public class PickItemOpUseCase extends MainGameNode {
    public PickItemOpUseCase(GameNode previousNode) {
        super(NodeNames.PICK_ITEM_OPPONENT, previousNode);
    }

    HashMap<String, Integer> options = new HashMap<>();
    GameNode tempNode = this;
    /** This method creates a State object and adds the selected inventory item belonging to a targeted opponent to the
     * State object and adds the properties of the current player as options to be chosen from later.
     * @return A State object containing the selected item and options for the current player to choose from later.
     */
    @Override
    public State create_state() {

        State currentState = new State();
        /*
        We asked them for an input in reference to a list with their player removed,
        thus we have to add 1 to their input in certain cases
        */

        int input = Integer.parseInt(getSelectedOptions().get(NodeNames.PICK_PLAYER));

        if (input >= getCurrentPlayerIndex()) {
            input += 1;
        }

        //provide item options from the inventory of the selected player
        Player selectedPlayer = getBoard().getPlayers().get(input);
        ArrayList<Property> playerProperties = selectedPlayer.getProperties();
        if (playerProperties.isEmpty() || getCurrentPlayer().getProperties().isEmpty()){
            tempNode = getFactory().getNode(NodeNames.NOTHING_TO_TRADE, null);
            getGameLogicInteractor().setCurrentNode(tempNode);
            return tempNode.create_state();
        }
        //using "i" starting from 0 to number of properties the player has - 1
        for (int i = 0; i<playerProperties.size(); i++) {
            String temp = playerProperties.get(i).getName();
            options.put(temp, i);
            currentState.addOptions(temp);
        }

        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {

        getSelectedOptions().put(this.getName(), String.valueOf(options.get(input.getInput())));
        return getFactory().getNode(NodeNames.PICK_ITEM_SELF, this);

    }
}