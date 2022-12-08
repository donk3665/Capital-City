package Logic;

import Entities.*;
import UseCases.Logic.GameLogic;
import UseCases.Logic.GeneralGameLogic;
import UseCases.Logic.MainTreeNodeLogic.MainParentNodeUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GeneralGameLogicTest {

    @Test
    public void testAddSwitchOptions(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        Property propertyOne = new Property("Property One", "Blue", 100, 100, new int[6],
                playerOne, 50, 0, false);
        Property propertyTwo = new Property("Property Two", "Blue", 100, 100, new int[6],
                playerTwo, 50, 0, false);
        playerOne.addProperty(propertyOne);
        playerTwo.addProperty(propertyTwo);
        List<Cell> cells = new ArrayList<>();
        cells.add(propertyOne);
        cells.add(propertyTwo);
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        GeneralGameLogic generalGameLogic = new GeneralGameLogic();
        MainParentNodeUseCase mainParentNode = new MainParentNodeUseCase();
        State test_state = mainParentNode.create_state(0);
        generalGameLogic.addSwitchOptions(test_state);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Trade");
        options.add("Manage Property");
        options.add("Roll The Dice");
        options.add("Steal");
        options.add("End Turn");
        options.add("Settings Menu");
        options.add("Bankruptcy");
        options.add("Trade");
        options.add("Manage Property");
        options.add("Roll The Dice");
        options.add("Steal");
        options.add("End Turn");
        options.add("Settings Menu");
        options.add("Bankruptcy");
        Assertions.assertEquals(test_state.getOptions(), options);
    }

    @Test
    public void testGetCurrentPlayerIndex(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        Property propertyOne = new Property("Property One", "Blue", 100, 100, new int[6],
                playerOne, 50, 0, false);
        Property propertyTwo = new Property("Property Two", "Blue", 100, 100, new int[6],
                playerTwo, 50, 0, false);
        playerOne.addProperty(propertyOne);
        playerTwo.addProperty(propertyTwo);
        List<Cell> cells = new ArrayList<>();
        cells.add(propertyOne);
        cells.add(propertyTwo);
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        GeneralGameLogic generalGameLogic = new GeneralGameLogic();
        generalGameLogic.setGameLogicInteractor(gameLogic);
        generalGameLogic.setCurrentPlayer(playerOne);
        Assertions.assertEquals(generalGameLogic.getCurrentPlayerIndex(), 0);
    }

    @Test
    public void testChangePlayers(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        List<Cell> cells = new ArrayList<>();
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        GeneralGameLogic generalGameLogic = new GeneralGameLogic();
        generalGameLogic.setGameLogicInteractor(gameLogic);
        generalGameLogic.setCurrentPlayer(playerOne);
        generalGameLogic.changePlayers();
        Assertions.assertEquals(generalGameLogic.getCurrentPlayer(), playerTwo);
    }

    @Test
    public void testAddPlayerStates(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        Property propertyOne = new Property("Property One", "Blue", 100, 100, new int[6],
                playerOne, 50, 0, false);
        Property propertyTwo = new Property("Property Two", "Blue", 100, 100, new int[6],
                playerTwo, 50, 0, false);
        playerOne.addProperty(propertyOne);
        playerTwo.addProperty(propertyTwo);
        List<Cell> cells = new ArrayList<>();
        cells.add(propertyOne);
        cells.add(propertyTwo);
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        GeneralGameLogic generalGameLogic = new GeneralGameLogic();
        MainParentNodeUseCase mainParentNode = new MainParentNodeUseCase();
        State test_state = mainParentNode.create_state(0);
        generalGameLogic.addPlayersState(test_state);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Trade");
        options.add("Manage Property");
        options.add("Roll The Dice");
        options.add("Steal");
        options.add("End Turn");
        options.add("Settings Menu");
        options.add("Bankruptcy");
        options.add("Player Two");
        Assertions.assertEquals(test_state.getOptions(), options);
    }

}
