package Logic.MainTreeNodeLogic.RollBranch;

import Entities.Game.*;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Interactors.CornerTilePerformActionInteractor;
import Interactors.PerformActionSpaceCardInteractor;
import Interactors.PropertyPerformActionInteractor;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.MainTreeNodeLogic.MainParentNodeUseCase;
import Logic.NodeInterface;
import Logic.NodeNames;
import UseCases.CornerTilePerformActionUseCase;
import UseCases.PerformActionSpaceUseCase;
import UseCases.PropertyPerformActionUseCase;

/**
 * This class represents a use case after an action card is drawn.
 */
public class CallActionUseCase extends MainGameNode {
    public CallActionUseCase() {
        super(NodeNames.CALL_ACTION, null );
    }

    /**
     * This method creates and returns a State object containing relevant information on the action called.
     * @return a State object containing relevant information on the action called and options for the user to choose
     * from.
     */
    @Override
    public State create_state() {
        Board board = getBoard();
        Player currentPlayer = getCurrentPlayer();
        Cell landedOnCell = board.getCell(currentPlayer.getPosition());
//perform the action on the space as well
        switch (landedOnCell.getType()) {
            case PROPERTY -> {
                PropertyPerformActionUseCase propertyInteractor = new PropertyPerformActionInteractor(getCaseInteractor().getListener());
                assert landedOnCell instanceof Property;
                Property property = (Property) landedOnCell;
                if (property.getMortgageStatus()) {
                    setAnswer("This property is mortgaged, don't need to pay rent.");
                } else {
                    setAnswer(propertyInteractor.performAction(property, currentPlayer));
                }
            }
            case CORNER_TILE -> {
                CornerTilePerformActionUseCase cornerTileInteractor = new CornerTilePerformActionInteractor(getCaseInteractor().getListener());
                assert landedOnCell instanceof CornerTiles;
                CornerTiles cornerTile = (CornerTiles) landedOnCell;
                setAnswer(cornerTileInteractor.performAction(currentPlayer, cornerTile));

                if (cornerTile instanceof GoToJail){
                    currentPlayer.setInJail(true);
                    currentPlayer.setPosition(10);
                }
            }
            case ACTION_SPACE -> {
                PerformActionSpaceUseCase actionSpaceInteractor = new PerformActionSpaceCardInteractor(getCaseInteractor().getListener());
                assert landedOnCell instanceof ActionSpace;
                ActionSpace actionSpace = (ActionSpace) landedOnCell;
                setAnswer(actionSpaceInteractor.performAction(actionSpace, currentPlayer, board));
            }
        }

        State currentState = new State();
        String answer = getAnswer();
        currentState.setDescription(answer);
        currentState.addOptions("Ok");
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        return new MainParentNodeUseCase();
    }
}