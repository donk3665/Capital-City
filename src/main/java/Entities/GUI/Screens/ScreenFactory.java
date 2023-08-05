package Entities.GUI.Screens;

import Logic.NodeNames;

public class ScreenFactory {
    private int lastScreen = 0;
    private GameScreen gameScreen = new GameScreen(null);

    public Screen getNode(NodeNames name){
        switch (name){
            // Initial nodes
            case INITIAL_PARENT, SETTINGS_INITIAL, QUIT_INITIAL, SELECT_GAME_TYPE, SELECT_NUMBER_OF_PLAYERS,
                    CONFIRM_LOADED_GAME, CONFIRM_NEW_GAME, SELECT_SAVE, SELECT_GAME_MODE, NUMBER_OF_ROUNDS, NO_SAVES-> {
                lastScreen = 1;
                return new InitialGameScreen(name);
            }
            case CREDITS -> {
                lastScreen = 2;
                return new CreditsScreen(name);
            }

//            //game nodes
            case ALREADY_ROLLED, AUCTION_ENTRY, BANKRUPTCY, BUILD_PROPERTY,BUY_PROPERTY, CALL_ACTION, PERFORM_STEAL, EMPTY_PROPERTY, END_TURN, EXIT_GAME,
                    FINISH_GAME, MAIN_PARENT, SELECT_PROPERTY, MORTGAGE, NO_PROPERTIES, NOTHING_TO_TRADE, PICK_PLAYER, PICK_ITEM_OPPONENT, PICK_ITEM_SELF, ROLL,
                    SAVE_GAME, SELECT_ACTION_PROPERTY, SEND_TRADE, SETTINGS, STEAL, UN_MORTGAGE, TRADE
                    -> {
                if (lastScreen == 3){
                    gameScreen.setRenderMode(1);
                }
                else {
                    gameScreen.setRenderMode(0);
                }
                lastScreen = 3;
                gameScreen.setName(name);
                return gameScreen;
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
//            //Auction tree nodes
//            case AUCTION_PARENT -> {
//                return new AuctionParent();
//            }
//            case HIGH_OPTION -> {
//                return new HighOptionUseCase();
//            }
//            case MEDIUM_OPTION -> {
//                return new MediumOptionUseCase();
//            }
//            case LOW_OPTION -> {
//                return new LowOptionUseCase();
//            }
//            case AUCTION_COMPLETE -> {
//                return new AuctionComplete(beforeNode);
//            }


        }
        System.err.println("ERROR IN SCREEN FACTORY");
        System.exit(1);
        return null;
    }
}

