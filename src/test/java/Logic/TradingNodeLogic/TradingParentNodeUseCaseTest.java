//package Logic.TradingNodeLogic;
//
//import Entities.*;
//import Logic.GameLogic;
//import Logic.MainTreeNodeLogic.TradingBranch.TradingOpponentNodeLogic.TradingOpponentParentNodeUseCase;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TradingParentNodeUseCaseTest {
//
//    @Test
//    public void testTradingParentNodeCreateState(){
//        Player playerOne = new Player("Player One");
//        List<Player> players = new ArrayList<>();
//        players.add(playerOne);
//        List<Cell> cells = new ArrayList<>();
//        Board board = new Board(players, cells);
//        new GameLogic(playerOne, board);
//        TradingOpponentParentNodeUseCase tradingParentNodeUseCase = new TradingOpponentParentNodeUseCase();
//        State actual = tradingParentNodeUseCase.create_state(0);
//        ArrayList<String> options = new ArrayList<>();
//        options.add("Trade");
//        options.add("Manage Property");
//        options.add("Roll The Dice");
//        options.add("Steal");
//        options.add("End Turn");
//        options.add("Settings Menu");
//        options.add("Bankruptcy");
//        Assertions.assertEquals(actual.getId(), "Trading Parent Node");
//        Assertions.assertEquals(actual.getOptions(), options);
//    }
//
//}
