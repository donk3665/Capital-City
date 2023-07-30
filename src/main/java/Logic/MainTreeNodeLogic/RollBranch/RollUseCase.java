package Logic.MainTreeNodeLogic.RollBranch;

import Entities.Game.*;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Interactors.CornerTilePerformActionInteractor;
import Interactors.PerformActionSpaceCardInteractor;
import Interactors.PropertyPerformActionInteractor;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeNames;
import UseCases.CornerTilePerformActionUseCase;
import UseCases.PerformActionSpaceUseCase;
import Logic.PlayerLogic;
import UseCases.PropertyPerformActionUseCase;

/**
 * This class represents the use case where the current player rolls the dice.
 */
public class RollUseCase extends MainGameNode {

    public RollUseCase(GameNode previousNode) {
        super(NodeNames.ROLL, previousNode);
    }
    GameNode tempNode;
    /**
     * This method creates a State object and conducts the appropriate actions related to the current state of the game,
     * and sets up the tree objects that are required due to the movement of the game.
     * @return A State object representing the state of the game after the current player rolls the dice.
     */
    @Override
    public State create_state() {
        Board board = getBoard();
        Player currentPlayer = getCurrentPlayer();

        if (mainStates[1] == 0) {
            //roll the dice and update the position
            PlayerLogic playerLogic = new PlayerLogic(currentPlayer);
            diceRoll = playerLogic.rollDice(0);

            //get the space landed on
            Cell landedOnCell = board.getCell(currentPlayer.getPosition());

            /*
            if the space is a property and has no owner, transverse to a branch, otherwise,
            transverse to another
            */
            mainStates[1] = 1;

            if (landedOnCell instanceof Property &&
                    ((Property) landedOnCell).getOwner() == null) {
                tempNode = getFactory().getNode(NodeNames.EMPTY_PROPERTY,this);
            } else {
                //perform the action on the space as well
                switch (landedOnCell.getType()) {
                    case "Property" -> {
                        PropertyPerformActionUseCase propertyInteractor = new PropertyPerformActionInteractor();
                        assert landedOnCell instanceof Property;
                        Property property = (Property) landedOnCell;
                        if (property.getMortgageStatus()) {
                            setAnswer("This property is mortgaged, don't need to pay rent.");
                        } else {
                            setAnswer(propertyInteractor.performAction(property, currentPlayer));
                        }
                    }
                    case "Corner Tile" -> {
                        CornerTilePerformActionUseCase cornerTileInteractor = new CornerTilePerformActionInteractor();
                        assert landedOnCell instanceof CornerTiles;
                        CornerTiles cornerTile = (CornerTiles) landedOnCell;
                        setAnswer(cornerTileInteractor.performAction(currentPlayer, cornerTile));
                    }
                    case "Action Space" -> {
                        PerformActionSpaceUseCase actionSpaceInteractor = new PerformActionSpaceCardInteractor();
                        assert landedOnCell instanceof ActionSpace;
                        ActionSpace actionSpace = (ActionSpace) landedOnCell;
                        setAnswer(actionSpaceInteractor.performAction(actionSpace, currentPlayer, board));
                    }
                }
                tempNode = getFactory().getNode(NodeNames.CALL_ACTION,this);
            }
        }
        else{
            tempNode = getFactory().getNode(NodeNames.ALREADY_ROLLED,this);
            //go to a node where it tells the user that they cannot roll
        }
        getGameLogicInteractor().setCurrentNode(tempNode);
        return tempNode.create_state();
    }

    @Override
    public GameNode performInput(InputInformation input) {
        return null;
    }
}