package Entities;

public class CornerTiles extends Cell{
    private String type;

    /**
     * The constructor for CornerTiles.
     * @param type The type of the Player/
     */
    public CornerTiles(String type) {
        this.type = type;
    }

    /**
     * Take the player and the board and decide which action
     * should be performed on the cornerTiles according to its
     * type.
     * @param currentPlayer
     * @param board
     * @return The message associated with the action.
     */
    @Override
    public String performAction(Player currentPlayer, Board board) {
        //TODO: check this.type and perform corresponding action
        String returnMessage = "";
        switch (this.type) {
            case "Go":
                returnMessage = "Passed Go! Collected 200";
                break;
            case "jail":
                if (currentPlayer.isInJail()) {
                    returnMessage = "You are still in Jail";
                } else {
                    returnMessage = "You're visiting Jail";
                }
                break;
            case "freeParking":
                returnMessage = "You landed on Free Parking!";
                break;
            case "goToJail":
                currentPlayer.setPosition(11);
                currentPlayer.changeJailStatus();
                returnMessage = "Go to Jail!";
                break;
        }
        return returnMessage;
    }
}
