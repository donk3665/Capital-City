
package UseCases;

import Network.GameInfoInterpreter;
import Entities.Game.Board;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Interactors.GameCreation;
import Interactors.SavePackager;
import Network.ServerListener;
import Logic.GameNode;
import Logic.GeneralGameNode;
import Logic.InitialNodeLogic.InitialGameNode;
import Logic.NodeNames;
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
    private boolean multiplayer = false;
    private ServerListener listener;

    public ServerListener getListener() {
        return listener;
    }

    public void setListener(ServerListener listener) {
        this.listener = listener;
    }

    private GameInfoInterpreter interpreter;

    /**
     * Constructor for the UseCaseInteractor.
     */
    public UseCaseInteractor(LoadAccess loadAccess, SaveAccess saveAccess){
        this.saveAccess = saveAccess;
        this.loadAccess = loadAccess;
        this.gameCreation = new GameCreation();
        this.savePackager = new SavePackager();
        this.logicInteractor = new GameLogic();
        this.interpreter = new GameInfoInterpreter(logicInteractor);
        GameNode.setCaseInteractor(this);
    }
    public void interpretInnerMessages(String[] message){
        interpreter.interpretString(message);
    }
    public SaveAccess getSaveAccess() {
        return saveAccess;
    }
    public void beginSequence(){
        if (InitialGameNode.savedGame){
            String loadFile = InitialGameNode.saveFile;
            ArrayList<String> lines = null;
            try {
                lines = getLoadAccess().getRawData(loadFile);
            }
            catch (FileNotFoundException e){
                System.err.println("FILE NOT FOUND");
                System.exit(1);
            }
            for (String line: lines){
                getListener().write("LOAD_DATA ¶" + line);
            }
        }
        else {
            int [] states = InitialGameNode.states;
            getListener().write("STATES " +states[0] + " " + states[1] + " " + states[2] + " " + states[3] + " " + states[4] + " " + states[5]);
        }
        setMultiplayer(true);
        getListener().write("INITIALIZE_GAME");
        InitialGameNode.createGame();
        getListener().write("READY");
    }


    public void setMultiplayer(boolean multiplayer) {
        this.multiplayer = multiplayer;
        logicInteractor.setMultiplayer(multiplayer);
    }

    public void setClient(int index){
        GeneralGameNode.setClientPlayer(index);
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

    public void exitToMenu(){logicInteractor.returnToMenu();}

    public void forceNodeSwitch(NodeNames node){
        logicInteractor.forceNodeSwitch(node);
    }

    /**
     * This method handles the input of the user. <br>It moves through the current tree with the user's input,
     * and uses helper methods to deal with the logic afterwards.
     * @param input the translated input of the user from the input interface
     */
    public State handleInput(InputInformation input){
        State currentState = logicInteractor.performInput(input);
        return currentState;
    }


    public String saveGame(){
        try{
            String saveReturn = saveAccess.saveGameNewFile(savePackager.getPlayerPropertyData(), savePackager.getStates());
            return "Successful save in file: "+ saveReturn;
        }
        catch (Exception IOException){
            return "Save failed";
        }
    }




    /**
     * This method allows the user to create a game, new or loaded, by loading the files
     * that deal with the game
     * @param board is the Board instance containing the save file information
     * @param states is an int[] containing save file information
     */
    public void createGame(Board board, int[] states){
        logicInteractor.setUpGame(board, states, multiplayer);
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
                playerNames.add("Player" + i);
            }
            Board newBoard = this.gameCreation.createNewBoard(playerNames, newProperties);
            gameCreation.initializeMode(newBoard, mode);
            return newBoard;
        }
        catch (Exception IOException){
            System.err.println("BOARD CREATION FAILED");
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
