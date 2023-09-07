package Logic;

import Entities.Game.Board;
import Entities.Game.Player;
import Entities.Game.Property;
import Interactors.PerformActionSpaceCardInteractor;
import Interactors.PropertyPerformActionInteractor;
import UseCases.PerformActionSpaceUseCase;
import UseCases.PropertyPerformActionUseCase;

import java.util.ArrayList;
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
    private static int returnPlayerIndex = -1;
    private static String answer;

    public static void setClientPlayer(int index){
        clientPlayer = board.getPlayers().get(index);
    }


    public GeneralGameNode(NodeNames name, GameNode previousNode) {
        super(name, previousNode);
    }


    public void setAnswer(String answer1){
        answer = answer1;
    }
    public String getAnswer(){
        return answer;
    }
    public static List<Player> getPlayers(){
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
        if (!GameNode.isMultiplayer()){
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
        if (!GameNode.isMultiplayer()){
            clientPlayer = currentPlayer;
        }
        else {
            getCaseInteractor().getListener().write("CHANGE_PLAYER " + currentPlayer.getPlayerIndex());
        }
    }

    public GameNode getMainParent(){
        return getFactory().getNode(NodeNames.MAIN_PARENT,null);
    }

    public static String performAction(int cardIndex){
        PerformActionSpaceUseCase actionSpaceInteractor = new PerformActionSpaceCardInteractor(getCaseInteractor().getListener());
        return actionSpaceInteractor.performExactAction(currentPlayer, board, cardIndex);
    }
    public static String payProperty( Player currentPlayer){
        Property property = (Property) board.getCell(currentPlayer.getPosition());
        PropertyPerformActionUseCase propertyPerformInteractor = new PropertyPerformActionInteractor(getCaseInteractor().getListener());
        return propertyPerformInteractor.payExact(property,currentPlayer);
    }
    public static void buyProperty(Player currentPlayer){
        Property targetProperty = (Property) board.getCell(currentPlayer.getPosition());

        //indicates that the player can afford it and sets the property owner as the current player and
        //deducts the player's money.
        currentPlayer.pay(targetProperty.getPrice());
//        currentPlayer.getProperties().add(targetProperty);
        targetProperty.setOwner(currentPlayer);
    }
    public static void swapProperty(String propertyName1, String propertyName2){
        Property property1 = board.getPropertyFromName(propertyName1);
        Property property2 = board.getPropertyFromName(propertyName2);
        Player property1Player = property1.getOwner();
        property1.setOwner(property2.getOwner());
        property2.setOwner(property1Player);
    }
    public static void buildProperty(String propertyName){
        Property property = board.getPropertyFromName(propertyName);
        PlayerLogic playerLogic = new PlayerLogic(getCurrentPlayer());
        playerLogic.buildHouse(property,1);
    }
    public static void mortgageProperty(String propertyName){
        Property property = board.getPropertyFromName(propertyName);
        PlayerLogic playerLogic = new PlayerLogic(getCurrentPlayer());
        playerLogic.mortgage(property);
    }
    public static void unMortgageProperty(String propertyName){
        Property property = board.getPropertyFromName(propertyName);
        PlayerLogic playerLogic = new PlayerLogic(getCurrentPlayer());
        playerLogic.unmortgage(property);
    }
    public static void bankrupt(int playerIndex){
        Player player = getBoard().getPlayers().get(playerIndex);
        ArrayList<Property> currentPlayerProperties = player.getProperties();
        for (Property targetedProperty : currentPlayerProperties) {
            targetedProperty.resetProperty();
        }
        board.removePlayer(player);
    }
    public static void steal(int playerIndex, boolean firstCheck, boolean secondCheck){
        PlayerLogic playerLogic = new PlayerLogic(getCurrentPlayer());
        String message = playerLogic.forceSteal(getPlayers().get(playerIndex), firstCheck, secondCheck);
    }
    public static void changePlayerBack(){
        List<Player> players = getPlayers();
        int returnPlayerIndex = getReturnPlayerIndex();
        //return to the main tree and correct player
        setCurrentPlayer(players.get(returnPlayerIndex));
    }
}
