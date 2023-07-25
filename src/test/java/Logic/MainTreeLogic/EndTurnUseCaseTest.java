//package Logic.MainTreeLogic;
//
//import Entities.Game.Board;
//import Entities.Game.Cell;
//import Entities.Game.Player;
//import Entities.InternalDataTransfer.State;
//import Logic.GameLogic;
//import Logic.MainTreeNodeLogic.EndTurnBranch.EndTurnUseCase;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class EndTurnUseCaseTest {
//
//    @Test
//    public void testEndTurnUseCaseCreateStateDebt(){
//        Player playerOne = new Player("Player One");
//        playerOne.setMoney(-1);
//        List<Player> players = new ArrayList<>();
//        players.add(playerOne);
//        List<Cell> cells = new ArrayList<>();
//        Board board = new Board(players, cells);
//        new GameLogic(playerOne, board);
//        EndTurnUseCase endTurnUseCase = new EndTurnUseCase();
//        State actual = endTurnUseCase.create_state();
//        ArrayList<String> options = new ArrayList<>();
//        options.add("Ok");
//        Assertions.assertEquals(actual.getId(), "End Turn");
//        Assertions.assertEquals(actual.getOptions(), options);
//    }
//
//}
