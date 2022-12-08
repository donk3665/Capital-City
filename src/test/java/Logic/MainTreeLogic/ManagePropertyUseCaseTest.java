package Logic.MainTreeLogic;

import Entities.*;
import UseCases.Logic.GameLogic;
import UseCases.Logic.MainTreeNodeLogic.ManagePropertyUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ManagePropertyUseCaseTest {

    @Test
    public void testManagePropertyCreateStateNoProperties(){
        Player playerOne = new Player("Player One");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        Property test_property = new Property("Name", "Blue", 100, 100, new int[6],
                null, 50, 0, false);
        List<Cell> cells = new ArrayList<>();
        cells.add(test_property);
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        ManagePropertyUseCase managePropertyUseCase = new ManagePropertyUseCase();
        State actual = managePropertyUseCase.create_state(0);
        Assertions.assertEquals(actual.getId(), "User Has No Properties (Manage Properties)");
        Assertions.assertEquals(actual.isBackEnable(), false);
    }

    @Test
    public void testManagePropertyCreateStateWithProperties(){
        Player playerOne = new Player("Player One");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        Property test_property = new Property("Name", "Blue", 100, 100, new int[6],
                playerOne, 50, 0, false);
        playerOne.addProperty(test_property);
        List<Cell> cells = new ArrayList<>();
        cells.add(test_property);
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        ManagePropertyUseCase managePropertyUseCase = new ManagePropertyUseCase();
        State actual = managePropertyUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Name");
        Assertions.assertEquals(actual.getId(), "Manage Property");
        Assertions.assertEquals(actual.isBackEnable(), true);
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
