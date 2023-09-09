package GUIInteractors;

import Entities.ExternalDataTransfer.BasicBoard;
import Entities.ExternalDataTransfer.BasicPlayer;
import Entities.ExternalDataTransfer.Output;
import Entities.Game.Board;
import Entities.Game.Player;
import Entities.InternalDataTransfer.State;
import Interactors.StateOutputReader;
import Logic.NodeNames;
import UseCases.UseCaseInteractor;

import java.util.ArrayList;

/***
 * OutputInteractor is a class that controls the data in the Output Entity and interacts with the UseCaseInteractor
 * to present output the user based on the current state of the game
 */
public class OutputInteractor {
    /**
     *  InstanceVar output: an Output object that keeps track and updates the output depending on the state of the game
     *  InstanceVar currentState: a State object that helps with determining the output that should be presented to the user
     */
    private final Output output;
    private State currentState;

    /**
     * The Constructor for the OutputInteractor Class
     * @param interactor: gets the initial state of the game
     */
    public OutputInteractor(UseCaseInteractor interactor){
        this.output = new Output();
        StateOutputReader createInitOutput = new StateOutputReader();
        createInitOutput.initStateHash();
        this.output.setInitStateHash(createInitOutput.getStateMap());
        this.currentState = interactor.getCurrentState();
    }

    /**
     * Function to return the current Output message for context to the user
     * @return the context String
     */
    public String getOutputMessage() {
        updateLogicStates(this.currentState.getId());
        return this.output.getStateOutput(this.currentState.getId());
    }
    public State getCurrentState(){
        return this.currentState;
    }


    public boolean getStartThread(){
        return currentState.isStartConnection();
    }

    /**
     * This function deals with all the states that need to be updated periodically based on the current state of the game
     * @param state: the current state that game is in
     */
    public void updateLogicStates(NodeNames state){
        switch (state) {
            case MAIN_PARENT:
                updateMainTree();
                break;
            case TRADE_OPPONENT_PARENT:
                updateParentTrade();
                break;
            case BUILD_PROPERTY:
                updateBuildProperty();
                break;
            case CALL_ACTION:
                updateCallAction();
                break;
            case EMPTY_PROPERTY:
                updateEmptyPropertySpace();
                break;
            case AUCTION_PARENT:
                updateAuctionTree();
                break;
            case ROLL:
                updateRollTree();
                break;
            case AUCTION_COMPLETE:
                updateAuctionComplete();
                break;
            case STEAL:
                updateSteal();
                break;
            case PERFORM_STEAL:
                updatePerformSteal();
                break;
            case FINISH_GAME:
                updateEnding();
                break;
            case SAVE_GAME:
                updateSave();
                break;
        }
    }

    /**
     * All the functions below are helper update functions
     */
    public void updateMainTree(){
        String currString = this.currentState.getPlayer().getName() + " It's your turn! What do you want to do? You currently have " +
                this.currentState.getPlayer().getMoney() + " dollars";
        this.output.modifyStateOutput(NodeNames.MAIN_PARENT, currString);
    }
    public void updateSave(){
        String currString = this.currentState.getDescription();
        this.output.modifyStateOutput(NodeNames.SAVE_GAME, currString);
    }
    public void updateEnding(){
        String currString = this.currentState.getDescription();
        this.output.modifyStateOutput(NodeNames.FINISH_GAME, currString);
    }
    public void updateRollTree(){
        String currString = this.currentState.getDescription();
        this.output.modifyStateOutput(NodeNames.ROLL, currString);
    }
    public void updateSteal(){
        String currString = this.currentState.getDescription();
        this.output.modifyStateOutput(NodeNames.STEAL, currString);
    }
    public void updatePerformSteal(){
        String currString = this.currentState.getDescription();
        this.output.modifyStateOutput(NodeNames.PERFORM_STEAL, currString);
    }
    public void updateParentTrade(){
        String currString = currentState.getPlayer().getName() + ", Incoming trade from player " +
                currentState.getTradingOpponent().getName() + " requesting for " + currentState.getTradingPlayerProperty().getName() +
                " in return for " + currentState.getCurrentPlayerProperty().getName();
        this.output.modifyStateOutput(NodeNames.TRADE_OPPONENT_PARENT, currString);
    }
    public void updateBuildProperty(){
        String currString = currentState.getDescription();
        this.output.modifyStateOutput(NodeNames.BUILD_PROPERTY, currString);
    }

    public void updateCallAction(){
        String currString =  currentState.getDescription();
        this.output.modifyStateOutput(NodeNames.CALL_ACTION, currString);
    }
    public void updateEmptyPropertySpace(){
        String currString = " You have landed on " +
                currentState.getCurrentPlayerProperty().getName() + " and no ones owns this. It costs " +
                currentState.getCurrentPlayerProperty().getPrice() + " What do you want to do?";
        this.output.modifyStateOutput(NodeNames.EMPTY_PROPERTY, currString);
    }
    public void updateAuctionTree(){
        String currString = currentState.getPlayer().getName() + ", we are bidding on " + currentState.getBiddingProperty().getName() +
                " with the current pot being " + currentState.getBiddingPot();
        this.output.modifyStateOutput(NodeNames.AUCTION_PARENT, currString);
    }
    public void updateAuctionComplete(){
        String currString = currentState.getPlayerWon().getName() + " won the auction for " + currentState.getBiddingPot() + " dollars";
        this.output.modifyStateOutput(NodeNames.AUCTION_COMPLETE, currString);
    }

    /**
     * Function to get the options the user has based on the state
     * @return the ArrayList of options in the current state
     */
    public void updateStateOptions(){
        if (this.currentState.isBackEnable()){
            currentState.addOptions("BACK");
        }

    }

    /**
     * Update the current state based on changes on the previous state from the user's input
     * @param state: the updated state
     */
    public void updateState(State state){
        this.currentState = state;
    }

}