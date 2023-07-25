//package Entities;
//
//import Logic.InitialNodeLogic.*;
//import org.junit.jupiter.api.Test;
//
//class GameLogicTreeTest {
//    private final GameLogicTree tree = createTree();
//
//
//    @Test
//    void getParent() {
//        assert tree.getParent() == null;
//    }
//
//
//    @Test
//    void getName() {
//
//    }
//
//
//    private GameLogicTree createTree(){
//        GameLogicTree gameLength = new GameLogicTree(new GameLengthUseCase());
//        GameLogicTree gameType = new GameLogicTree(new SelectNumberOfPlayersUseCase());
//        gameType.addChild(gameLength);
//
//        GameLogicTree numPlayers = new GameLogicTree(new NumberOfRoundsUseCase());
//        GameLogicTree newGame = new GameLogicTree(new SelectGameModeUseCase());
//        newGame.addChild(numPlayers);
//        newGame.addChild(gameType);
//
//        GameLogicTree loadGame = new GameLogicTree(new SelectSaveUseCase());
//        GameLogicTree startMenu = new GameLogicTree(new SelectGameTypeUseCase());
//        startMenu.addChild(loadGame);
//        startMenu.addChild(newGame);
//
//        return startMenu;
//    }
//}