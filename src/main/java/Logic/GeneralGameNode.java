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
    private static Board board;
    private static final HashMap<String, Integer> selectedOptions = new HashMap<>();
    private static int returnPlayerIndex = -1;
    private static List<Player> players;
    private static String answer;


    public GeneralGameNode(NodeNames name, GameNode previousNode) {
        super(name, previousNode);
    }


    public void setAnswer(String answer1){
        answer = answer1;
    }
    public String getAnswer(){
        return answer;
    }
    public List<Player> getPlayers(){
        return players;
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
        players = board1.getPlayers();
        currentPlayer = currentPlayer1;
        board = board1;

    }




    /**
     * This method gets the index of the current player in the players arraylist.
     * @return the integer index of the current player.
     */
    public static int getCurrentPlayerIndex(){
        for (int i = 0; i< players.size(); i++){
            if (currentPlayer == players.get(i)){
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
    }

    /**
     * getter method for the current player instance attribute
     * @return the current player
     */
    public static Player getCurrentPlayer(){
        return currentPlayer;
    }



    /**
     * Method to change players when their turn is over
     */
    public static void changePlayers(){
        currentPlayer = players.get((getCurrentPlayerIndex() + 1) % players.size());
    }



    public GameNode getMainParent(){
        return getFactory().getNode(NodeNames.MAIN_PARENT,null);
    }

}
