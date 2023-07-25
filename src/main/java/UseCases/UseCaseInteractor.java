
package UseCases;

import Entities.Game.Board;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Interactors.GameCreation;
import Interactors.SavePackager;
import Logic.GameNode;
import Persistence.LoadAccess;
import Logic.GameLogic;
import Persistence.SaveAccess;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 * This class creates a UseCaseInteractor which coordinates the inputs to the entities layer of the program.
 */
public class UseCaseInteractor{

    private GameLogic logicInteractor;
    private final LoadAccess loadAccess;
    private final SaveAccess saveAccess;
    private final GameCreation gameCreation;
    private final SavePackager savePackager;





    /**
     * Constructor for the UseCaseInteractor.
     */
    public UseCaseInteractor(LoadAccess loadAccess, SaveAccess saveAccess){
        this.saveAccess = saveAccess;
        this.loadAccess = loadAccess;
        this.gameCreation = new GameCreation();
        this.savePackager = new SavePackager();
        this.logicInteractor = new GameLogic();
        GameNode.setCaseInteractor(this);
    }
    public LoadAccess getLoadAccess(){
        return loadAccess;
    }

    /**
     * This method returns the state that the game is in
     * @return the state that the game is in
     */
    public State getCurrentState() {
        return logicInteractor.getCurrentState();
    }


    /**
     * This method handles the input of the user. <br>It moves through the current tree with the user's input,
     * and uses helper methods to deal with the logic afterwards.
     * @param input the translated input of the user from the input interface
     */
    public State handleInput(InputInformation input){

        State currentState = logicInteractor.performInput(input);

        if(currentState.isSaveGame()){
            saveGame(currentState);
        }
        return currentState;

    }


    public void saveGame(State currentState){
        try{
            String saveReturn = saveAccess.saveGameNewFile(savePackager.getPlayerPropertyData(), savePackager.getStates());
            currentState.setDescription("Successful save in file: "+ saveReturn);
        }
        catch (Exception IOException){
            currentState.setDescription("Save failed");
        }
    }


    public void createNewGame(int[] states){
        Board loadedBoard;
        loadedBoard = loadFiles(states[0], states[2]);
        createGame(loadedBoard, states);
    }

    /**
     * This method allows the user to create a game, new or loaded, by loading the files
     * that deal with the game
     * @param board is the Board instance containing the save file information
     * @param states is an int[] containing save file information
     */
    public void createGame(Board board, int[] states){
        logicInteractor.setUpGame(board, states);
    }

    /**
     * This method loads the files used in the program and creates an object which contains all the necessary game
     * files to begin a game
     * @return the object which will supply the data to create the game
     */
    public Board loadFiles(int numberOfPlayers, int mode) {
        try {
            ArrayList<String[]> newProperties = this.loadAccess.loadProperties();
            ArrayList<String> playerNames = new ArrayList<>();
            for (int i = 0; i < numberOfPlayers; i++) {
                playerNames.add("Player " + i);
            }
            Board newBoard = this.gameCreation.createNewBoard(playerNames, newProperties);
            gameCreation.initializeMode(newBoard, mode);
            return newBoard;
        }
        catch (Exception IOException){
            return null;
        }
    }
    /**
     * This method loads the files used in the program and the save file and
     * an object which contains all the necessary game files to load a previous game
     * @return the object which will supply the data to create the game
     */
    public Board loadSavedBoard(String file) throws IOException{
        ArrayList<ArrayList<String[]>> loadedGame = this.loadAccess.loadGame(file);
        ArrayList<String[]> newProperties = this.loadAccess.loadProperties();

        return this.gameCreation.createSavedBoard(loadedGame, newProperties);
    }

    /**
     * This method loads the save file and returns the initial states required for the
     * Tree when creating a game
     * @return an Integer[] of initial states
     * @throws FileNotFoundException - Exception thrown when loadGame method in loadAccess fails
     */
    public int[] loadInitialStates(String file) throws FileNotFoundException {
        ArrayList<ArrayList<String[]>> loadedGame = this.loadAccess.loadGame(file);
        return this.gameCreation.getInitialStates(loadedGame);
    }


    /**
     * Returns the GameLogic instance used in this object
     * @return the GameLogic instance
     */
    public GameLogic getLogicInteractor() {
        return logicInteractor;
    }



}
