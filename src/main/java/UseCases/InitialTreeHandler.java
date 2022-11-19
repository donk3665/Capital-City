package UseCases;

import Entities.GameLogicTree;
import Entities.State;

public class InitialTreeHandler {
    //This array contains various states for the program which will be used for various calculations
    private int[] globalStates = new int[5];
    UseCaseInteractor caseInteractor;
    public InitialTreeHandler(UseCaseInteractor caseInteractor){
        this.caseInteractor = caseInteractor;
    }

    /**
     * This method handles the input of the user in the initialization part of the program.
     * <p>
     * Usage of globalStates array:<br>
     * ------------------------------------------------------------------------------------- <br>
     * globalStates[0]: If the user is loading a game or starting a new game. Can be 1 or 0.
     * <p>
     * If the user decides to create a new game: <br>
     * globalStates[1]: An integer indicating what game mode the user wishes to load.<br>
     * globalStates[2]: An integer indicating the number of players in the game.<br>
     * globalStates[3]: An integer indicating the choice for the length of the game.<br>
     * <p>
     * If the user decides to load a past game:<br>
     * globalStates[4]: An integer indicating the choice of saved games to load<br>
     * @param input the translated input of the user from the input interface
     */
    public State handleTree(int input){
        GameLogicTree currentTree = caseInteractor.getCurrentTree();
        //deciding what to do based on node visited
        State state = new State();
        switch (currentTree.getName()){
            case "NewGame":
                //in "New Game" node
                globalStates[0] = 1;
                state.setName("What mode would you like to play?");
                state.addOptions("Normal mode");
                break;
            case "ChooseGameMode":
                //in "Choose Game mode" node
                globalStates[1] = input;
                state.setName("How many players?");
                for (int i = 2; i<9; i++){
                    state.addOptions(i + " players");
                }
                break;
            case "NumberOfPlayers":
                //in "Number of Players" node
                globalStates[2] = input;
                state.setName("How many rounds?");
                state.addOptions("30 rounds");
                state.addOptions("60 rounds");
                state.addOptions("90 rounds");
                state.addOptions("no limit");
                break;
            case "GameLength":
                //in "Game Length" node
                globalStates[3] = input;
                state.setName("Create the game?");
                state.addOptions("Yes");
                state.addOptions("No");
                break;
            case "CreateNewGame":
                //in "Create new Game" node
                //TODO: CREATE THE GAME BY MAKING THE LOGIC INTERACTOR
                break;
            case "LoadGame":
                //in "Load Game" node
                globalStates[0] = 0;
                break;
            case "ChooseSave":
                //in "Choose Save" node
                globalStates[4] = input;
                break;
            case "CreateLoadedGame":
                //in "Create Loaded Game" node
                //TODO: CREATE THE GAME BY MAKING THE LOGIC INTERACTOR
                break;
        }
        return state;
    }
}
