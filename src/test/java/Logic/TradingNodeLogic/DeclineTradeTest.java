//package Logic.TradingNodeLogic;
//
//import Entities.Game.Board;
//import Entities.Game.Cell;
//import Entities.Game.Player;
//import Entities.Game.Property;
//import Entities.InternalDataTransfer.State;
//import Logic.GameLogic;
//import Logic.GeneralGameNode;
//import Logic.MainTreeNodeLogic.TradingBranch.TradingOpponentNodeLogic.DeclineTradeUseCase;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DeclineTradeTest {
//
//    @Test
//    public void testDeclineTradeCreateState(){
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
//        GeneralGameNode.setReturnPlayerIndex(0);
//        GeneralGameNode.setCurrentPlayer(playerTwo);
//        DeclineTradeUseCase declineTrade = new DeclineTradeUseCase();
//        State actual = declineTrade.create_state(0);
//        ArrayList<String> options = new ArrayList<>();
//        options.add("Ok");
//        Assertions.assertEquals(actual.getId(), "Decline The Trade");
//        Assertions.assertEquals(actual.getOptions(), options);
//    }
//
//}
