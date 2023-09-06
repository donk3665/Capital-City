package Interactors;

import java.util.Random;

import Entities.Game.*;
import Network.ServerListener;
import UseCases.PerformActionSpaceUseCase;

/**
 * This class generates and performs the correct action when a player draws an action card.
 */
public class PerformActionSpaceCardInteractor implements PerformActionSpaceUseCase{

    private ServerListener listener;
    public PerformActionSpaceCardInteractor(ServerListener listener){
        this.listener = listener;
    }


    /**
     * Generates a random card from the action space
     * @param actionSpace the action space
     * @return the card
     */
    public Card generateRandomCard(ActionSpace actionSpace) {
        int random_index = new Random().nextInt(actionSpace.getCards().size());
        listener.writeIfMultiplayer("INFO: ACTION_SPACE_CARD " + random_index);
        return actionSpace.getCards().get(random_index);
    }
    public Card generateCard(ActionSpace actionSpace, int index) {
        return actionSpace.getCards().get(index);
    }
    /**
     * Performs the advance action of the card
     * @param player the player
     * @param card the card
     * @return the message
     */
    public String advanceAction(Player player, Card card) {
            String action = card.getActionString();
            int numberOfSteps = card.getAmount();
            player.move(numberOfSteps);


            action = " " + action + " " + numberOfSteps + " steps.";

            if (player.getPosition() == 0 || player.getPosition() + numberOfSteps > 40) {
                player.changeMoney(200);
            }
            return action;
    }

    /**
     * Performs the paid action of the card
     * @param player the player
     * @param card the card
     * @return the message
     */
    public String getPaidAction(Player player, Card card) {
        String action = " " + card.getActionString();
        player.changeMoney(card.getAmount());
        return action;
    }

    /**
     * Performs the pay action of the card
     * @param player the player
     * @param card the card
     * @return the message
     */
    public String payAction(Player player, Card card) {
        String action = " " + card.getActionString();
        player.pay(card.getAmount());
        return action;
    }

    /**
     * Performs the pay all players action of the card
     * @param player the player
     * @param board the board
     * @param card the card
     * @return the message
     */
    public String payAllAction(Player player, Board board, Card card) {
        String action = " " + card.getActionString();
        for (int i = 0; i < board.getPlayers().size(); i++) {
            player.pay(board.getPlayers().get(i), card.getAmount());
        }
        return action;
    }

    /**
     * Performs the go-to jail action of the card
     * @param player the player
     * @param card the card
     * @return the message
     */
    public String goToJailAction(Player player, Card card) {
        String action = " " + card.getActionString();
        player.setInJail(true);
        player.setPosition(10);
        return action;
    }

    /**
     * Performs the get out of jail card action of the card, adds one to the users jail cards
     * @param player the player
     * @param card the card
     * @return the message
     */
    public String getOutOfJailAction(Player player, Card card) {
        String action = " " + card.getActionString();
        player.setJailCards(player.getJailCards() + 1);
        return action;
    }

    /**
     * Performs the pay tax card action of the card, adds one to the users jail cards
     * @param player the player
     * @param card the card
     * @return the message
     */
    public String payTaxAction(Player player, Card card) {
        String action = " " + card.getActionString();
        player.pay(card.getAmount());
        return action;
    }


    /**
     * Aggregates all the actions of the card and the main perform action method
     * @param player the player
     * @param board the board
     * @return the message
     */
    public String performAction(ActionSpace actionSpace, Player player, Board board) {
        Card card = generateRandomCard(actionSpace);
        return performCardAction(card, player, board);
    }
    public String performExactAction(Player player, Board board, int cardIndex) {
        ActionSpace actionSpace = (ActionSpace) board.getCell(player.getPosition());
        Card card = generateCard(actionSpace, cardIndex);
        return performCardAction(card, player, board);
    }
    private String performCardAction(Card card, Player player, Board board){
        ActionTypeEnum actionType = card.getActionType();

        switch (actionType){
            case ADVANCE -> {
                return advanceAction(player, card);
            }
            case GET_PAID -> {
                return getPaidAction(player, card);
            }
            case PAY -> {
                return payAction(player, card);
            }
            case PAY_ALL -> {
                return payAllAction(player, board, card);
            }
            case GO_TO_JAIL -> {
                return goToJailAction(player, card);
            }
            case TAX -> {
                return payTaxAction(player,card);
            }
        }
        return getOutOfJailAction(player, card);
    }
}
