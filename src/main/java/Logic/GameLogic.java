package Logic;

import Entities.Game.Board;
import Entities.Game.Player;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.MainTreeNodeLogic.MainGameNode;

/**
 * This class creates a GameLogic instance which coordinates the flow of the game.
 */
public class GameLogic {
    //private final GameLogicTree[] trees = new GameLogicTree[3];
//    private GameLogicTree currentTree;

    private GameNode currentNode;
    /**
     * This is the constructor for creating a GameLogic instance.
     * @param currentPlayer A Player instance that is intended to be the current player of the game's turn.
     * @param board A board instance that the GameLogic instance will help govern.
     */
    public GameLogic(Player currentPlayer, Board board){
        initialize(currentPlayer, board);
    }
    public void initialize(Player currentPlayer, Board board){
        //AuctionTreeNode.array_init(board.getPlayers().size());
        GeneralGameNode.initialize(currentPlayer,board);
        GameNode.setGameLogicInteractor(this);
//        createTrees();
    }
    public void setCurrentNode(GameNode currentNode){
        this.currentNode = currentNode;
    }
    public GameLogic(){
        currentNode = GeneralGameNode.getFactory().getNode(NodeNames.INITIAL_PARENT);
    }
    /**
     * This is the constructor for creating a GameLogic instance.
     * @param board A board instance that the GameLogic instance will help govern.
     * @param states array of integers containing what is needed to create the game
     */
    public void setUpGame(Board board, int[] states){
        Player currentPlayer = board.getPlayers().get(states[4]);
        initialize(currentPlayer,board);
        int [] gameStates = new int[5];
        gameStates[0] = 0;
        gameStates[1] = states[5];
        gameStates[2] = states[2];
        gameStates[3] = states[3];
        gameStates[4] = states[1];
        MainGameNode.initializeStates(gameStates);
    }

    public State getCurrentState(){
        return setDefaultStateAttributes(currentNode.create_state());
    }
    public State performInput(InputInformation input){
        if (input == null){
            currentNode = currentNode.getPreviousNode();
        }
        else {
            currentNode = currentNode.performInput(input);

        }
        return setDefaultStateAttributes(currentNode.create_state());
    }
    public State setDefaultStateAttributes(State state){
        if (currentNode.getPreviousNode() != null){
            state.setBackEnable(true);
        }
        state.setId(currentNode.getName());
        state.setPlayer(MainGameNode.getCurrentPlayer());
        state.setCurrentBoard(GeneralGameNode.getBoard());
        return state;
    }


}

