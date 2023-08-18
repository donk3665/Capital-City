package Entities.Game;

/**
 * This is an abstract class representing the corner tiles on the monopoly board.
 */
public abstract class CornerTiles extends Cell {
    public CellEnum getType(){
        return CellEnum.CORNER_TILE;
    }
    public abstract String returnMessage(Player player);
}
