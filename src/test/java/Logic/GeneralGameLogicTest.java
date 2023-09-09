package Logic;

import Entities.Game.Board;
import Entities.Game.Cell;
import Entities.Game.Player;
import Entities.Game.Property;
import Entities.InternalDataTransfer.State;
import Logic.MainTreeNodeLogic.MainParentNodeUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;


public class GeneralGameLogicTest {

    @Test
    public void testAddSwitchOptions(){
        Player playerOne = new Player("Player One", 0);
        Player playerTwo = new Player("Player Two", 1);
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
        new GameLogic(playerOne, board);
        MainParentNodeUseCase mainParentNode = new MainParentNodeUseCase();
        State test_state = mainParentNode.create_state();
        ArrayList<String> options = new ArrayList<>();
        options.add("TRADE");
        options.add("MANAGE_PROPERTY");
        options.add("ROLL");
        options.add("STEAL");
        options.add("END_TURN");
        options.add("BANKRUPTCY");
        Assertions.assertEquals(test_state.getOptions(), options);
    }

//    @Test
//    public void testGetCurrentPlayerIndex(){
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
//        GeneralGameNode.setGameLogicInteractor(gameLogic);
//        GeneralGameNode.setCurrentPlayer(playerOne);
//        Assertions.assertEquals(GeneralGameNode.getCurrentPlayerIndex(), 0);
//    }
//
//    @Test
//    public void testChangePlayers(){
//        Player playerOne = new Player("Player One");
//        Player playerTwo = new Player("Player Two");
//        List<Player> players = new ArrayList<>();
//        players.add(playerOne);
//        players.add(playerTwo);
//        List<Cell> cells = new ArrayList<>();
//        Board board = new Board(players, cells);
//        GameLogic gameLogic = new GameLogic(playerOne, board);
//        GeneralGameNode.setGameLogicInteractor(gameLogic);
//        GeneralGameNode.setCurrentPlayer(playerOne);
//        GeneralGameNode.changePlayers();
//        Assertions.assertEquals(GeneralGameNode.getCurrentPlayer(), playerTwo);
//    }
//
//    @Test
//    public void testAddPlayerStates(){
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
//        GeneralGameNode generalGameLogic = new GeneralGameNode();
//        MainParentNodeUseCase mainParentNode = new MainParentNodeUseCase();
//        State test_state = mainParentNode.create_state(0);
//        generalGameLogic.addPlayersState(test_state);
//        ArrayList<String> options = new ArrayList<>();
//        options.add("Trade");
//        options.add("Manage Property");
//        options.add("Roll The Dice");
//        options.add("Steal");
//        options.add("End Turn");
//        options.add("Settings Menu");
//        options.add("Bankruptcy");
//        options.add("Player Two");
//        Assertions.assertEquals(test_state.getOptions(), options);
//    }

}
