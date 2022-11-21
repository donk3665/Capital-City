
package UseCases;

import Entities.Board;
import Entities.GameLogicTree;
import Entities.MenuTree;
import Entities.State;
import Interactors.DataAccess;
import Interactors.GameCreation;
import Interactors.GameLogic;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UseCaseInteractor{
    private GameLogicTree currentTree;
    private boolean menuTreeActive = true;

    private InitialTreeHandler treeHandler;

    private GameLogic logicInteractor;
    private DataAccess dataAccess;
    private GameCreation gameCreation;
    private State currentState;

    /**
     * Constructor for the UseCaseInteractor.
     */
    public UseCaseInteractor(DataAccess dataAccess){
        this.dataAccess = dataAccess;
        createTrees();
        currentState = getInitialState();
        updateOutput(currentState);
        treeHandler = new InitialTreeHandler(this);
        this.gameCreation = new GameCreation();
    }

    /**
     * This method handles the input of the user. <br>It moves through the current tree with the user's input,
     * and uses helper methods to deal with the logic afterwards.
     * @param input the translated input of the user from the input interface
     */
    public State handleInput(int input){
        if (menuTreeActive){
            //Moving through the tree depending on the input and the node
            if (input == -1){
                //Move backwards in tree
                currentTree = (GameLogicTree) currentTree.getParent();
            }
            else if (currentTree.isSwitchBlock()){
                //Move forward in tree to one of the branches
                currentTree = (GameLogicTree) currentTree.getChildren().get(input);
            }
            else{
                //Move forward in tree
                currentTree = (GameLogicTree) currentTree.getChildren().get(0);
            }
            currentState = treeHandler.handleTree(input);
        }
        else{
            currentState = handleOtherTrees(input);
        }
        return currentState;

    }
    /**
     * This method creates the initial menu tree for the program <br>
     * The tree is for the initialization part of the program, allowing the user
     * to choose game modes, number of players, etc.
     */
    public void createTrees(){
        //creating first tree
        GameLogicTree initialMenu = new GameLogicTree("InitialMenu", "Welcome to monopoly");
        GameLogicTree newGame = new GameLogicTree("NewGame");
        GameLogicTree chooseGameMode = new GameLogicTree("ChooseGameMode");
        GameLogicTree numPlayers = new GameLogicTree("NumberOfPlayers");
        GameLogicTree gameLength = new GameLogicTree("GameLength");
        GameLogicTree createNewGame = new GameLogicTree("CreateNewGame");
        GameLogicTree loadGame = new GameLogicTree("LoadGame");
        GameLogicTree chooseSave = new GameLogicTree("ChooseSave");
        GameLogicTree createGame = new GameLogicTree("CreateLoadedGame");

        //creating the tree structure
        gameLength.addChild(createNewGame);
        numPlayers.addChild(gameLength);
        chooseGameMode.addChild(numPlayers);
        newGame.addChild(chooseGameMode);

        chooseSave.addChild(createGame);
        loadGame.addChild(chooseSave);

        initialMenu.addChild(newGame);
        initialMenu.addChild(loadGame);
        //indicates that this tree switches with input
        initialMenu.setIsSwitchBlock(true);


        //adding the tree into an array for later retrieval
        currentTree = initialMenu;

    }
    public State getInitialState(){
        State currentState = new State();
        currentState.setDescription(currentTree.getPrompt());
        for (MenuTree tree: currentTree.getChildren()){
            currentState.addOptions(tree.getName());
        }
        return currentState;
    }


    /**
     * This method handles the input of the user in the game part of the program.
     * <p>
     */
    public State handleOtherTrees(int input){
        //deciding what to do based on node visited
        return logicInteractor.performInput(input);

    }
    public void updateOutput(State currentState){
        //TODO update the user interface
    }
    public Board loadFiles(String filepath) throws IOException {
        //TODO load files for game creation
        //TODO correctly implement this method

        ArrayList<ArrayList<String[]>> loadedGame = this.dataAccess.loadGame();
        ArrayList<String[]> newProperties = this.dataAccess.loadProperties();

        ArrayList<String> playerNames = new ArrayList<>();
        for (int i = 0; i <= treeHandler.selectedOptions.get("NumberOfPlayers"); i++){
            playerNames.add("Player " + i);
        }

        Board newGame = this.gameCreation.createNewGame(playerNames, newProperties);
        Board savedGame = this.gameCreation.createSavedGame(loadedGame, newProperties);

        return newGame;
    }

    public GameLogicTree getCurrentTree(){
        return currentTree;
    }



}
