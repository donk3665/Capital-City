//package Logic.MainTreeLogic;
//
//
//import Entities.Game.Board;
//import Entities.Game.Cell;
//import Entities.Game.Player;
//import Entities.Game.Property;
//import Entities.InternalDataTransfer.State;
//import Logic.GameLogic;
//import Logic.GeneralGameNode;
//import Logic.MainTreeNodeLogic.TradingBranch.SendTradeUseCase;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SendTradeTest {
//
//    @Test
//    public void testSendTradeCreateStateSent(){
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
//        GameLogic gameLogic = new GameLogic(playerOne, board);
//        GeneralGameNode generalGameLogic = new GeneralGameNode();
//        GeneralGameNode.setGameLogicInteractor(gameLogic);
//        generalGameLogic.getSelectedOptions().put("PickPlayer", 1);
//        GeneralGameNode.setCurrentPlayer(playerOne);
//        generalGameLogic.getSelectedOptions().put("PickItemSelf", 0);
//        generalGameLogic.getSelectedOptions().put("PickItemOp", 0);
//        SendTradeUseCase sendTrade = new SendTradeUseCase();
//        State actual = sendTrade.create_state(0);
//        ArrayList<String> options = new ArrayList<>();
//        options.add("Accept The Trade");
//        options.add("Decline The Trade");
//        Assertions.assertEquals(actual.getOptions(), options);
//        Assertions.assertEquals(actual.getCurrentPlayerProperty(), propertyOne);
//        Assertions.assertEquals(actual.getTradingPlayerProperty(), propertyTwo);
//        Assertions.assertEquals(actual.getPlayer(), playerOne);
//        Assertions.assertEquals(actual.getTradingOpponent(), playerTwo);
//
//    }
//
//    @Test
//    public void testSendTradeCreateStateNotSent(){
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
//        SendTradeUseCase sendTrade = new SendTradeUseCase();
//        State actual = sendTrade.create_state(1);
//        Assertions.assertEquals(actual.getId(), "main.Main Tree Parent Node");
//    }
//
//}
