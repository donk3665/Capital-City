package Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch;

import Entities.Game.Board;
import Entities.Game.OrderedStringHashmap;
import Entities.Game.Player;
import Entities.Game.Property;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeInterface;
import Logic.NodeNames;

import java.util.HashMap;

/**
 * This use case represents when the current user lands on an empty property space.
 */
public class EmptyPropertySpaceUseCase extends MainGameNode {

    public EmptyPropertySpaceUseCase() {
        super(NodeNames.EMPTY_PROPERTY, null);
    }


    OrderedStringHashmap<NodeNames> options = new OrderedStringHashmap<>(){
        {
            put("BUY", NodeNames.BUY_PROPERTY);
            put("AUCTION", NodeNames.AUCTION_ENTRY);
        }
    };
    /**
     * This method creates a State object containing information on the response and options from rolling on an empty
     * property space.
     * @return a State object containing information on the response and options from rolling on an empty property
     * space.
     */
    @Override
    public State create_state() {
        Board board = getBoard();
        Player currentPlayer = getCurrentPlayer();
        Property targetProperty;

        State currentState = new State();
        //gets the response and options from rolling on an empty property
        currentState.setRoll(diceRoll);
        targetProperty = (Property) board.getCell(currentPlayer.getPosition());
        currentState.setCurrentPlayerProperty(targetProperty);
        currentState.addOptions(options.getKeys());

        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {

        return getFactory().getNode(options.get(input.getInput()), this);
    }
}