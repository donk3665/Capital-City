package Entities.GUI.Screens;

import Logic.NodeNames;

public class ScreenFactory {

    public Screen getNode(NodeNames name){
        switch (name){
            // Initial nodes
            case INITIAL_PARENT, SETTINGS_INITIAL, CREDITS, QUIT_INITIAL, SELECT_GAME_TYPE, SELECT_NUMBER_OF_PLAYERS,
                    CONFIRM_LOADED_GAME, CONFIRM_NEW_GAME, SELECT_SAVE, SELECT_GAME_MODE, NUMBER_OF_ROUNDS, NO_SAVES-> {
                return new GameScreen(name);
            }

//            case SELECT_GAME_MODE -> {
//                return new SelectGameModeUseCase(beforeNode);
//            }
//            case SELECT_SAVE -> {
//                return new SelectSaveUseCase(beforeNode);
//            }
//            case NUMBER_OF_ROUNDS -> {
//                return new NumberOfRoundsUseCase(beforeNode);
//            }
//            case SELECT_NUMBER_OF_PLAYERS -> {
//                return new SelectNumberOfPlayersUseCase(beforeNode);
//            }
//            case CONFIRM_NEW_GAME -> {
//                return new ConfirmNewGameUseCase();
//            }
//            case CONFIRM_LOADED_GAME -> {
//                return new ConfirmLoadedGameUseCase(beforeNode);
//            }
//            case NO_SAVES -> {
//                return new NoSavesUseCase();
//            }
//
//
//            //game nodes
//            case ALREADY_ROLLED -> {
//                return new AlreadyRolledUseCase(beforeNode);
//            }
//            case AUCTION_ENTRY -> {
//                return new AuctionEntryUseCase(beforeNode);
//            }
//            case BANKRUPTCY -> {
//                return new BankruptcyUseCase(beforeNode);
//            }
//            case BUILD_PROPERTY -> {
//                return new BuildPropertyUseCase(beforeNode);
//            }
//            case BUY_PROPERTY -> {
//                return new BuyUseCase(beforeNode);
//            }
//            case CALL_ACTION -> {
//                return new CallActionUseCase(beforeNode);
//            }
//            case PERFORM_STEAL -> {
//                return new PerformStealUseCase(beforeNode);
//            }
//            case EMPTY_PROPERTY -> {
//                return new EmptyPropertySpaceUseCase(beforeNode);
//            }
//            case END_TURN -> {
//                return new EndTurnUseCase(beforeNode);
//            }
//            case EXIT_GAME -> {
//                return new ExitGameUseCase(beforeNode);
//            }
//            case FINISH_GAME -> {
//                return new FinishGameUseCase();
//            }
//            case MAIN_PARENT -> {
//                return new MainParentNodeUseCase();
//            }
//            case SELECT_PROPERTY -> {
//                return new SelectPropertyUseCase(beforeNode);
//            }
//            case MORTGAGE -> {
//                return new MortgageUseCase(beforeNode);
//            }
//            case NO_PROPERTIES -> {
//                return new NoPropertiesUseCase(beforeNode);
//            }
//            case NOTHING_TO_TRADE -> {
//                return new NothingToTradeUseCase(beforeNode);
//            }
//            case PICK_ITEM_OPPONENT -> {
//                return new PickItemOpUseCase(beforeNode);
//            }
//            case PICK_ITEM_SELF -> {
//                return new PickItemSelfUseCase(beforeNode);
//            }
//            case ROLL -> {
//                return new RollUseCase(beforeNode);
//            }
//            case SAVE_GAME -> {
//                return new SaveGameUseCase(beforeNode);
//            }
//            case SELECT_ACTION_PROPERTY -> {
//                return new SelectActionPropertyUseCase(beforeNode);
//            }
//            case SEND_TRADE -> {
//                return new SendTradeUseCase(beforeNode);
//            }
//            case SETTINGS -> {
//                return new SettingsMenuUseCase(beforeNode);
//            }
//            case STEAL -> {
//                return new StealUseCase(beforeNode);
//            }
//            case UN_MORTGAGE -> {
//                return new UnMortgageUseCase(beforeNode);
//            }
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

