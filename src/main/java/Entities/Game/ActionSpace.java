package Entities.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents an action space cell.
 */
public class ActionSpace extends Cell{
    
    private final List<Card> cards;
    private final String type;

    public ActionSpace(HashMap<String, List<Card>> cards) {
        String key = cards.keySet().iterator().next();
        this.cards = cards.get(key);
        this.type = key;
    }
    public ActionSpace(Card card){
        cards = new ArrayList<>();
        cards.add(card);
        this.type = card.getType();
    }

    @Override
    public CellEnum getType() {
        return CellEnum.ACTION_SPACE;
    }

    public String getActionType() {
        return this.type;
    }


    public List<Card> getCards() {
        return this.cards;
    }

}
