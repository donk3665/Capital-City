//package Entities;
//import Entities.Game.*;
//import Interactors.CornerTilePerformActionInteractor;
//import Interactors.GameCreation;
//import UseCases.CornerTilePerformActionUseCase;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//
//public class CornerTilesTest {
//
//    public CornerTilesTest() {
//    }
//
//    private final Player player1 = new Player("Player1");
//    @Test
//    public void performActionInJailTest() throws IOException {
//        ArrayList<String> players = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
//        GameCreation creation = new GameCreation();
//        String[] property = {"0", "1", "2",
//                "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
//        ArrayList<String[]> properties = new ArrayList<>();
//        for (int i = 0; i < 40; i++){
//            properties.add(property);
//        }
//
//        creation.createNewBoard(players, properties);
//        CornerTiles t = new JailSpace();
//        player1.setInJail(true);
//        CornerTilePerformActionUseCase cornerTileInteractor = new CornerTilePerformActionInteractor();
//        Assertions.assertEquals(" You are still in Jail", cornerTileInteractor.performAction(player1, t));
//    }
//
//    @Test
//    public void performActionNotInJailTest() throws IOException {
//        ArrayList<String> players = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
//        GameCreation creation = new GameCreation();
//        String[] property = {"0", "1", "2",
//                "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
//        ArrayList<String[]> properties = new ArrayList<>();
//        for (int i = 0; i < 40; i++){
//            properties.add(property);
//        }
//        creation.createNewBoard(players, properties);
//        CornerTiles t = new JailSpace();
//        player1.setInJail(false);
//        CornerTilePerformActionUseCase cornerTileInteractor = new CornerTilePerformActionInteractor();
//        Assertions.assertEquals(" You're visiting Jail", cornerTileInteractor.performAction(player1, t));
//    }
//    @Test
//    public void performActionFreeParkingTest() throws IOException {
//        ArrayList<String> players = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
//        GameCreation creation = new GameCreation();
//        String[] property = {"0", "1", "2",
//                "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
//        ArrayList<String[]> properties = new ArrayList<>();
//        for (int i = 0; i < 40; i++){
//            properties.add(property);
//        }
//        creation.createNewBoard(players, properties);
//        CornerTiles t = new FreeParking();
//        CornerTilePerformActionUseCase cornerTileInteractor = new CornerTilePerformActionInteractor();
//        Assertions.assertEquals(" You landed on Free Parking!", cornerTileInteractor.performAction(player1, t));
//    }
//    @Test
//    public void performActionGoToJailTest() throws IOException {
//        ArrayList<String> players = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
//        GameCreation creation = new GameCreation();
//        String[] property = {"0", "1", "2",
//                "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
//        ArrayList<String[]> properties = new ArrayList<>();
//        for (int i = 0; i < 40; i++){
//            properties.add(property);
//        }
//
//        creation.createNewBoard(players, properties);
//        CornerTiles t = new GoToJail();
//        CornerTilePerformActionUseCase cornerTileInteractor = new CornerTilePerformActionInteractor();
//        Assertions.assertEquals(" Go to Jail!", cornerTileInteractor.performAction(player1, t));
//        Assertions.assertEquals(player1.getPosition(), 10);
//        Assertions.assertTrue(player1.isInJail());
//    }
//    @Test
//    public void performActionPassGoTest() throws IOException{
//        ArrayList<String> players = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
//        GameCreation creation = new GameCreation();
//        String[] property = {"0", "1", "2",
//                "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
//        ArrayList<String[]> properties = new ArrayList<>();
//        for (int i = 0; i < 40; i++){
//            properties.add(property);
//        }
//
//        creation.createNewBoard(players, properties);
//        CornerTiles t = new PassGo();
//        CornerTilePerformActionUseCase cornerTileInteractor = new CornerTilePerformActionInteractor();
//        Assertions.assertEquals(" Passed Go!, Collected 200", cornerTileInteractor.performAction(player1, t));
//    }
//}
