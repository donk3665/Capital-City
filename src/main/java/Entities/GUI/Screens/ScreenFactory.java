package Entities.GUI.Screens;

import Entities.GUI.Screens.ScreenElements.InitialGameScreen;
import Logic.NodeNames;

public class ScreenFactory {
 //   private int lastScreen = 0;
    //private GameScreen gameScreen = new GameScreen();

    private Screen [] screenArray = new Screen[7];

    public ScreenFactory(){
        screenArray[0] = new InitialGameScreen();
        screenArray[1] = new CreditsScreen();
        screenArray[2] = new GameScreen();
        screenArray[3] = new AuctionScreen();
        screenArray[4] = new EndingScreen();
        screenArray[5] = new ConnectionScreen();
        screenArray[6] = new JoinScreen();

       for (Screen screen: screenArray){
           screen.gamePane.setDoubleBuffered(true);
           screen.gamePane.setBounds(0,0,(int) Screen.width,(int) Screen.height);
           screen.setUpGamePane();
       }
    }




    public Screen getNode(NodeNames name){
        switch (name){
            // Initial nodes
            case INITIAL_PARENT, SETTINGS_INITIAL, QUIT_INITIAL,SELECT_GAME_LOBBY, SELECT_GAME_TYPE, SELECT_NUMBER_OF_PLAYERS,
                    CONFIRM_LOADED_GAME, CONFIRM_NEW_GAME, SELECT_SAVE, SELECT_GAME_MODE, NUMBER_OF_ROUNDS, NO_SAVES, MULTIPLAYER_LOBBY-> {
       //         lastScreen = 1;
                return screenArray[0];
            }
            case CREDITS -> {
      //          lastScreen = 2;
                return screenArray[1];
            }
            case HOST_GAME, JOIN_GAME -> {
   //             lastScreen = 3;
                return screenArray[5];
            }
            case SELECT_GAME_CLIENT -> {
 //               lastScreen = 4;
                return screenArray[6];
            }

//            //game nodes
            case ALREADY_ROLLED, AUCTION_ENTRY, BANKRUPTCY, BUILD_PROPERTY,BUY_PROPERTY, CALL_ACTION, PERFORM_STEAL, EMPTY_PROPERTY, END_TURN, EXIT_GAME,
                     MAIN_PARENT, SELECT_PROPERTY, MORTGAGE, NO_PROPERTIES, NOTHING_TO_TRADE, PICK_PLAYER, PICK_ITEM_OPPONENT, PICK_ITEM_SELF, ROLL,
                    SAVE_GAME, SELECT_ACTION_PROPERTY, SEND_TRADE, SETTINGS, STEAL, UN_MORTGAGE, TRADE
                    -> {
//                if (lastScreen == 3){
//                    ((GameScreen)screenArray[2]).setRenderMode(1);
//                }
//                else {
//                    ((GameScreen)screenArray[2]).setRenderMode(0);
//                }
//                lastScreen = 4;
                screenArray[2].setName(name);
                return screenArray[2];
            }


//
//            //Trade tree nodes
//            case TRADE, PICK_PLAYER -> {
//                return new PickPlayerUseCase(beforeNode);
//            }
//            case ACCEPT_TRADE -> {
//                return new AcceptTradeUseCase();
//            }
//            case DECLINE_TRADE -> {
//                return new DeclineTradeUseCase();
//            }
//            case FOLD -> {
//                return new FoldUseCase();
//            }
//            case TRADE_OPPONENT_PARENT -> {
//                return new TradingOpponentParentNodeUseCase();
//            }
//
            //Auction tree nodes
            case AUCTION_PARENT, LOW_OPTION, MEDIUM_OPTION, HIGH_OPTION, AUCTION_COMPLETE -> {
                return screenArray[3];
            }
            case FINISH_GAME -> {
                return screenArray[4];
            }


        }
        System.err.println("ERROR IN SCREEN FACTORY");
        System.exit(1);
        return null;
    }
}

