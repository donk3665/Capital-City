package Logic.MainTreeNodeLogic.ManagePropertyBranch;

import Entities.Game.Player;
import Entities.Game.Property;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeNames;

import java.util.ArrayList;

/**
 * This class represents the use case where the users want to manage a property.
 */
public class SelectPropertyUseCase extends MainGameNode {
    public SelectPropertyUseCase(GameNode previousNode) {
        super(NodeNames.SELECT_PROPERTY, previousNode);
    }
    GameNode tempNode;

    /**
     * This method creates a State object either containing properties as options or moves to another node when there
     * are no properties to manage.
     * @return A State object containing either properties to manage or moves the game into another node.
     */
    @Override
    public State create_state() {
        Player currentPlayer = getCurrentPlayer();

        State currentState = new State();
        currentState.setBackEnable(true);
        ArrayList<Property> currentPlayerProperties = currentPlayer.getProperties();

        //if player has no properties, go to another node.
        if (currentPlayerProperties.isEmpty()){

            tempNode = getFactory().getNode(NodeNames.NO_PROPERTIES);
            getGameLogicInteractor().setCurrentNode(tempNode);
            return tempNode.create_state();
        }

        //provide options on the properties available
        for (Property currentPlayerProperty : currentPlayerProperties) {
            currentState.addOptions(currentPlayerProperty.getName());
        }
        tempNode = getFactory().getNode(NodeNames.SELECT_ACTION_PROPERTY);
        return currentState;
    }

    @Override
    public GameNode performInput(InputInformation input) {
        getSelectedOptions().put(getName(), input.getInput());
        return tempNode;
    }
}