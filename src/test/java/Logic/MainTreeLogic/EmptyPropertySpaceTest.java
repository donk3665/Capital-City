//package Logic.MainTreeLogic;
//
//import Entities.Game.Board;
//import Entities.Game.Cell;
//import Entities.Game.Player;
//import Entities.Game.Property;
//import Entities.InternalDataTransfer.State;
//import Logic.GameLogic;
//import Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.EmptyPropertySpaceUseCase;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class EmptyPropertySpaceTest {
//
//    @Test
//    public void testEmptyPropertySpaceCreateState(){
//        Player playerOne = new Player("Player One");
//        Player playerTwo = new Player("Player Two");
//        List<Player> players = new ArrayList<>();
//        players.add(playerOne);
//        players.add(playerTwo);
//        Property propertyOne = new Property("Property One", "Blue", 100, 100, new int[6],
//                playerOne, 50, 0, false);
//        Property propertyTwo = new Property("Property Two", "Blue", 100, 100, new int[6],
//                playerTwo, 50, 0, false);
//        playerOne.addProperty(propertyOne);
//        playerTwo.addProperty(propertyTwo);
//        List<Cell> cells = new ArrayList<>();
//        cells.add(propertyOne);
//        cells.add(propertyTwo);
//        Board board = new Board(players, cells);
//        new GameLogic(playerOne, board);
//        EmptyPropertySpaceUseCase emptyPropertySpace = new EmptyPropertySpaceUseCase();
//        State actual = emptyPropertySpace.create_state(0);
//        ArrayList<String> options = new ArrayList<>();
//        options.add("Trade");
//        options.add("Manage Property");
//        options.add("Roll The Dice");
//        options.add("Steal");
//        options.add("End Turn");
//        options.add("Settings Menu");
//        options.add("Bankruptcy");
//        Assertions.assertEquals(actual.getId(), "Property Unowned");
//        Assertions.assertEquals(actual.getOptions(), options);
//    }
//
//}
