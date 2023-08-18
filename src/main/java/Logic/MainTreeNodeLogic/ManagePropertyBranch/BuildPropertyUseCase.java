package Logic.MainTreeNodeLogic.ManagePropertyBranch;

import Entities.InternalDataTransfer.InputInformation;
import Entities.Game.Player;
import Entities.Game.Property;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeNames;
import Logic.PlayerLogic;

import java.util.HashMap;

/**
 * This class represents the use case when a building is to be built on a property.
 */
public class BuildPropertyUseCase extends MainGameNode {

    public BuildPropertyUseCase(GameNode previousNode) {
        super(NodeNames.BUILD_PROPERTY, previousNode);
    }

    /**
     * This method builds a house on a chosen property and returns a State object containing the necessary information
     * to continue the game after the house is built.
     * @return Returns a State object containing the necessary information required to continue the game after the house
     * is built.
     */
    @Override
    public State create_state() {
        State currentState = new State();
        Player currentPlayer = getCurrentPlayer();

        Property targetProperty = currentPlayer.getProperties().get(Integer.parseInt(getSelectedOptions().get(NodeNames.SELECT_PROPERTY)));

        //builds a house on the chosen property
        PlayerLogic playerLogic = new PlayerLogic(currentPlayer);
        playerLogic.buildHouse(targetProperty,1);
        currentState.setCurrentPlayerProperty(targetProperty);
        currentState.addOptions("Ok");
        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        return getMainParent();
    }
}