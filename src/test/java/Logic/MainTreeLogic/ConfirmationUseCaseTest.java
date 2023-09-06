//package Logic.MainTreeLogic;
//
//import Entities.Game.Board;
//import Entities.Game.Cell;
//import Entities.Game.Player;
//import Entities.InternalDataTransfer.State;
//import Logic.GameLogic;
//import Logic.GeneralGameNode;
//import Logic.MainTreeNodeLogic.ConfirmationUseCase;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ConfirmationUseCaseTest {
//
//    @Test
//    public void testConfirmationUseCaseCreateState(){
//        Player playerOne = new Player("Player One");
//        Player playerTwo = new Player("Player Two");
//        List<Player> players = new ArrayList<>();
//        players.add(playerOne);
//        players.add(playerTwo);
//        List<Cell> cells = new ArrayList<>();
//        Board board = new Board(players, cells);
//        GameLogic gameLogic = new GameLogic(playerOne, board);
//        GeneralGameNode.initialize(playerOne, board, gameLogic);
//        ConfirmationUseCase confirmationUseCase = new ConfirmationUseCase();
//        State actual = confirmationUseCase.create_state(1);
//        Assertions.assertEquals(actual.getId(), "main.Main Tree Parent Node");
//    }
//
//}
