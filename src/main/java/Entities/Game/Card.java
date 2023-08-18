package Entities.Game;

/**
 * This class represents a card that a player interacts with.
 */
public class Card {

    private final String type;
    private final String actionString;
    private final ActionTypeEnum actionType;
    private final Integer amount;

    public Card(String type, String actionString, ActionTypeEnum actionType, Integer amount) {
        this.type = type;
        this.actionString = actionString;
        this.actionType = actionType;
        this.amount = amount;
    }

    public String getType() {
        return this.type;
    }

    public String getActionString() {
        return this.actionString;
    }

    public ActionTypeEnum getActionType() {
        return this.actionType;
    }

    public Integer getAmount() {
        return this.amount;
    }

}
