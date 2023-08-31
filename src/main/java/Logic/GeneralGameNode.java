package Logic;

import Entities.Game.Board;
import Entities.Game.Player;

import java.util.HashMap;
import java.util.List;

/** This class is the parent class of the three tree handlers used during the game phase of the application.
 *  <br> Each of the subclasses coordinate the game logic of the application.
 */
public abstract class GeneralGameNode extends GameNode {

    //Static variables used by all the subclasses
    private static Player currentPlayer;
    private static Player clientPlayer;
    private static Board board;
//    private static final HashMap<String, Integer> selectedOptions = new HashMap<>();
    private static int returnPlayerIndex = -1;
    private static String answer;
    private static boolean multiplayer = false;

    public static void setClientPlayer(int index){
        clientPlayer = board.getPlayers().get(index);
    }


    public GeneralGameNode(NodeNames name, GameNode previousNode) {
        super(name, previousNode);
    }

    public static void setMultiplayer(boolean multiplayer) {
        GeneralGameNode.multiplayer = multiplayer;
    }
    public void setAnswer(String answer1){
        answer = answer1;
    }
    public String getAnswer(){
        return answer;
    }
    public List<Player> getPlayers(){
        return board.getPlayers();
    }
    public static void setReturnPlayerIndex(int index){
        returnPlayerIndex = index;
    }
    public static int getReturnPlayerIndex(){
        return returnPlayerIndex;
    }
    public static Board getBoard(){
        return board;
    }


    /**
     * This method initializes variables used by the tree handlers
     * @param currentPlayer1 - The current player of the game
     * @param board1 - the current board being used in the game
     */
    public static void initialize(Player currentPlayer1, Board board1){
        currentPlayer = currentPlayer1;
        clientPlayer = currentPlayer;
        board = board1;
    }
//    public static void setClientPlayer(String name){
//        for (Player player: board.getPlayers()){
//            if (player.getName().equals(name)){
//                clientPlayer = player;
//            }
//        }
//    }

    public static boolean isTurn(){
        return currentPlayer == clientPlayer;
    }

    /**
     * This method gets the index of the current player in the players arraylist.
     * @return the integer index of the current player.
     */
    public static int getCurrentPlayerIndex(){
        for (int i = 0; i< board.getPlayers().size(); i++){
            if (currentPlayer == board.getPlayers().get(i)){
                return i;
            }
        }
        throw new RuntimeException("Player not in array");
    }


    /**
     * Setter method for the current player instance attribute
     * @param player - the player to set
     */
    public static void setCurrentPlayer(Player player){currentPlayer = player;
        if (!multiplayer){
            clientPlayer = currentPlayer;
        }
    }
    public static void setCurrentPlayer(int index){
        setCurrentPlayer(board.getPlayers().get(index));
    }
    /**
     * getter method for the current player instance attribute
     * @return the current player
     */
    public static Player getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     * getter method for the client player instance attribute
     * @return the current player
     */
    public static Player getClientPlayer(){
        return clientPlayer;
    }

    /**
     * Method to change players when their turn is over
     */
    public static void changePlayers(){
        currentPlayer = board.getPlayers().get((getCurrentPlayerIndex() + 1) % board.getPlayers().size());
        if (!multiplayer){
            clientPlayer = currentPlayer;
        }
        else {
            getCaseInteractor().getListener().write("CHANGE_PLAYER " + currentPlayer.getPlayerIndex());
        }
    }



    public GameNode getMainParent(){
        return getFactory().getNode(NodeNames.MAIN_PARENT,null);
    }

}
