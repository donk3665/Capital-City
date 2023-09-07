package Logic.MainTreeNodeLogic.RollBranch;

import Entities.Game.*;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Interactors.CornerTilePerformActionInteractor;
import Interactors.PerformActionSpaceCardInteractor;
import Interactors.PropertyPerformActionInteractor;
import Logic.*;
import Logic.MainTreeNodeLogic.MainGameNode;
import UseCases.CornerTilePerformActionUseCase;
import UseCases.PerformActionSpaceUseCase;
import UseCases.PropertyPerformActionUseCase;

/**
 * This class represents the use case where the current player rolls the dice.
 */
public class RollUseCase extends MainGameNode {

    public RollUseCase() {
        super(NodeNames.ROLL, null);
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
        State currentState = new State();
        currentState.addOptions("Okay");
        if (mainStates[1] == 0) {
            //roll the dice and update the position
            PlayerLogic playerLogic = new PlayerLogic(currentPlayer);
            diceRoll = playerLogic.rollDice(mainStates[5]);

            String stateMessage = "You rolled a " + diceRoll + ".";
            if (playerLogic.isRolledDouble()){
                mainStates[5] = mainStates[5] + 1;
                stateMessage = stateMessage.concat("This is a double, so you can roll again. You have rolled " + mainStates[3] + " doubles in a row.");
            }
            else{
                mainStates[1] = 1;
            }
            currentState.setDescription(stateMessage);

            //diceRoll = playerLogic.forceRoll(7);

            getCaseInteractor().getListener().writeIfMultiplayer("INFO: MOVE " + currentPlayer.getPosition());

            /*
            transverse to another node based on position of player
            */
            Cell landedOnCell = board.getCell(currentPlayer.getPosition());
            if (landedOnCell instanceof Property &&
                    ((Property) landedOnCell).getOwner() == null) {
                tempNode = getFactory().getNode(NodeNames.EMPTY_PROPERTY,this);
            } else {

                tempNode = getFactory().getNode(NodeNames.CALL_ACTION,this);
            }
        }
        else{
            tempNode = getFactory().getNode(NodeNames.ALREADY_ROLLED,this);
            getGameLogicInteractor().setCurrentNode(tempNode);
            return tempNode.create_state();
            //go to a node where it tells the user that they cannot roll
        }
        //getGameLogicInteractor().setCurrentNode(tempNode);
        return currentState;
    }

    @Override
    public NodeInterface performInput(InputInformation input) {
        return tempNode;
    }
}