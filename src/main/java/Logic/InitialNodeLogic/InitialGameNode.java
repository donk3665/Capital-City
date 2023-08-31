package Logic.InitialNodeLogic;

import Entities.Game.Board;
import Logic.GameNode;
import Logic.NodeNames;

import java.io.IOException;

public abstract class InitialGameNode extends GameNode {
    public static boolean savedGame = false;
    public static int[] states;
    public static Board board;
    public static String saveFile;
    public InitialGameNode(NodeNames name, GameNode previousNode) {
        super(name, previousNode);
    }

    public static void createGame(){
        caseInteractor.createGame(board, states);
    }
    public static void loadStatesNetwork(String message){
        String[] splitMessage = message.split("\s+");
        for (int i = 1; i<splitMessage.length; i++){
            states = new int[6];
            states[i-1] = Integer.parseInt(splitMessage[i]);
        }
    }
    public static void createNewBoard(){
        board = caseInteractor.loadFiles(states[0], states[2]);
    }
    public static void loadBoardAndStates(String loadFile) throws IOException {
        board = caseInteractor.loadSavedBoard(loadFile);
        states = caseInteractor.loadInitialStates(loadFile);
    }

}
