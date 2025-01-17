package Logic;

import Entities.Game.CellEnum;
import Entities.Game.Player;
import Entities.Game.Property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class which handles logic methods for a given player
 */
public class PlayerLogic {
    private final Player player;
    public static final int BROWN_DARKBLUE_SETSIZE = 2;
    public static final int PROPERTY_SETSIZE = 3;
    public static final double STEAL_CHANCE = 0.3;
    public static final double STEAL_JAIL_CHANCE = 0.6;
    public static final int STEAL_MONEY = 100;
    static final double MORTGAGE_INTEREST = 1.1;
    private boolean[] stealBoolean = new boolean[2];
    public boolean[] getStealBoolean() {
        return stealBoolean;
    }
    public PlayerLogic(Player player) {
        this.player = player;
    }
    private boolean rolledDouble = false;
    public boolean isRolledDouble() {
        return rolledDouble;
    }
    /**
     * This method rolls the dice for the player. The dice result can indicate this player's movement while
     * they are not in jail. If they are in jail, the dice result can determine if the player can get out of jail
     * @param consecutive the number of consecutive doubles
     * @return a String of two numbers indicating the numbers rolled from the dice
     */
    public String rollDice(int consecutive){
        int max = 6;
        int min = 1;
        int roll1 =  (int) Math.floor(Math.random() * (max - min + 1) + min);
        int roll2 = (int) Math.floor(Math.random() * (max - min + 1) + min);
        if (isConsecutive(roll1, roll2)){
            rolledDouble = true;
        }
        if (player.isInJail() && isConsecutive(roll1, roll2)){
                this.player.setInJail(false);
                this.player.move(roll1 + roll2);
            }
        else if (!player.isInJail() && ! isConsecutive(roll1, roll2) ){
                this.player.move(roll1 + roll2);
                return (String.valueOf( roll1 + roll2));
            }
        else if (!player.isInJail() && isConsecutive(roll1, roll2) && (consecutive + 1) < 3){
                return this.rollDice((consecutive + 1));
            }
        else if(!player.isInJail() && isConsecutive(roll1, roll2) && (consecutive + 1) == 3){
                // the player goes to jail
                this.player.setInJail(true);
                return ((roll1 + roll2) + "player goes to jail");
            }
        return (String.valueOf( roll1 + roll2));
    }
    public String forceRoll(int roll){
        this.player.move(roll);
        return String.valueOf(roll);
    }

    private static boolean isConsecutive(int roll1, int roll2) {
        return roll1 == roll2;
    }

    /**
     * Helper function for ownedPropertySets(). This function creates a map with the property colours as the key and
     * an int counter as its value.
     * @return a HashMap with the property colours as the key and an int counter as its value.
     */
    private HashMap<String, Integer> createSetMap() {
        HashMap<String, Integer> sets = new HashMap<>();
        sets.put("Brown", 0);
        sets.put("Light_Blue", 0);
        sets.put("Pink", 0);
        sets.put("Orange", 0);
        sets.put("Red", 0);
        sets.put("Yellow", 0);
        sets.put("Green", 0);
        sets.put("Dark_Blue", 0);
        return sets;
    }

    /**
     * Counts the number of properties owned by player in each colour set
     * @return a HashMap mapping each colour to an int value correlating to the number of properties of that colour
     * owned by this player
     */
    public HashMap<String, Integer> countPropertySets() {
        HashMap<String, Integer> sets = createSetMap();
        for (Property property : player.getProperties()) {
            if (!Objects.equals(property.getSpecialType(), CellEnum.RAILROAD) && !Objects.equals(property.getSpecialType(), CellEnum.UTILITY)){
                sets.put(property.getColour(), sets.get(property.getColour()) + 1);
            }
        }
        return sets;
    }

    /**
     * Gets all the complete sets of properties owned by this player (sets are grouped by colours)
     *
     * @return an ArrayList of colours referring to the complete sets of properties owned by this player
     */
    public ArrayList<String> ownedPropertySets() {
        ArrayList<String> ownedSets = new ArrayList<>();
        HashMap<String, Integer> sets = countPropertySets();
        for (Map.Entry<String, Integer> colour : sets.entrySet()) {
            if (colour.getKey().equals("Brown") || colour.getKey().equals("Dark_Blue")) {
                if (colour.getValue() == BROWN_DARKBLUE_SETSIZE) {
                    ownedSets.add(colour.getKey());
                }
            } else {
                if (colour.getValue() == PROPERTY_SETSIZE) {
                    ownedSets.add(colour.getKey());
                }
            }
        }
        return ownedSets;
    }

    /**
     * A helper function for steal that deals with the stealSuccessful situation.
     * @param victim The victim whose money is going to be stolen
     * @return A string that indicates if the stealing is successful or if the player is in jail.
     */
    public String stealSuccessful(Player victim) {
        victim.pay(player, STEAL_MONEY);
        return player.getName() + " stole money from " + victim.getName();
    }

    /**
     * A helper function for steal that deals with the stealUnsuccessful situation.
     * @return A string that indicates if the stealing is successful or if the player is in jail.
     */
    public String stealUnsuccessful() {
        double jail = Math.random();
        if (jail <= STEAL_JAIL_CHANCE) {
            return stealVeryUnsuccessful();
        } else {
            return stealMildlyUnsuccessful();
        }
    }
    private String stealVeryUnsuccessful(){
        player.setInJail(true);
        player.setPosition(10);
        return ("The police are looking for " + player.getName() + "\n" + player.getName() + " is put in jail");
    }
    private String stealMildlyUnsuccessful(){
        stealBoolean[1] = true;
        return ("The police are looking for " + player.getName() + "\n" + player.getName() +
                " escaped from the police");
    }
    public String forceSteal(Player victim, boolean firstChance, boolean secondChance){
        if (firstChance){
            return stealSuccessful(victim);
        }
        else if (secondChance){
            return stealMildlyUnsuccessful();
        }
        else {
            return stealVeryUnsuccessful();
        }
    }

    /**
     * Method that allows this player to steal 100 units of money from a player. The success of stealing relies on
     * a chance basis. Unsuccessfully stealing from the victim gives this player a chance of being put in jail.
     * @param victim the player whom this player is stealing from
     * @return returns a String statement indicating whether stealing was successful. If not, the statement indicates
     * whether this player is put in jail
     */
    public String steal(Player victim) {
        double success = Math.random();
        if (success <= STEAL_CHANCE) {
            stealBoolean[0] = true;
            return stealSuccessful(victim);
        } else {
            return stealUnsuccessful();
        }
    }

    /**
     * Placing the property for mortgage
     * @param property the property to remove and to add the money to the current players balance
     */
    public void mortgage(Property property) {
        player.changeMoney(property.getMortgageValue());
        property.setMortgageStatus(true);
    }

    /**
     * Unmortgaging a property
     * @param property the property to be unmortgaged
     */
    public void unmortgage(Property property) {
        player.pay((int) (property.getMortgageValue() * MORTGAGE_INTEREST));
        property.setMortgageStatus(false);
    }

    /**
     * This method allows this player to build houses on a currently owned property
     * @param property the property to build the house on
     * @param houses the number of houses to build
     */
    public String buildHouse(Property property, int houses) {
        switch (property.addHouse(player, houses)) {
            case "house":
                return ((houses + " houses have been built on " + property.getName()));
            case "hotel":
                return ("A hotel has been built on " + property.getName());
            case "not owned":
                return ("Player does not own " + property.getName());
            case "not owned set":
                return ("Player does not own the full colour set of " + property.getName());
            case "not enough money":
                return ("Player does not have enough money to build " + houses + " houses on " + property.getName());
            default:
                return null;
        }
    }

}

