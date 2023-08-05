package Logic;

import Logic.InitialNodeLogic.*;
import Logic.MainTreeNodeLogic.*;
import Logic.MainTreeNodeLogic.BankruptcyBranch.BankruptcyUseCase;
import Logic.MainTreeNodeLogic.EndTurnBranch.EndTurnUseCase;
import Logic.MainTreeNodeLogic.EndTurnBranch.FinishGameUseCase;
import Logic.MainTreeNodeLogic.ManagePropertyBranch.*;
import Logic.MainTreeNodeLogic.RollBranch.*;
import Logic.MainTreeNodeLogic.RollBranch.AlreadyRolledUseCase;
import Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.AuctionNodeLogic.*;
import Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.BuyUseCase;
import Logic.MainTreeNodeLogic.RollBranch.EmptyPropertyBranch.EmptyPropertySpaceUseCase;
import Logic.MainTreeNodeLogic.SettingsBranch.ExitGameUseCase;
import Logic.MainTreeNodeLogic.SettingsBranch.SaveGameUseCase;
import Logic.MainTreeNodeLogic.SettingsBranch.SettingsMenuUseCase;
import Logic.MainTreeNodeLogic.StealBranch.PerformStealUseCase;
import Logic.MainTreeNodeLogic.StealBranch.StealUseCase;
import Logic.MainTreeNodeLogic.TradingBranch.*;
import Logic.MainTreeNodeLogic.TradingBranch.TradingOpponentNodeLogic.AcceptTradeUseCase;
import Logic.MainTreeNodeLogic.TradingBranch.TradingOpponentNodeLogic.DeclineTradeUseCase;
import Logic.MainTreeNodeLogic.TradingBranch.TradingOpponentNodeLogic.TradingOpponentParentNodeUseCase;

public class NodeFactory {
        public NodeFactory() {

        }
        public GameNode getNode(NodeNames name){
            return getNode(name, null);
        }


        public GameNode getNode(NodeNames name, GameNode beforeNode){
            switch (name){
                // Initial nodes
                case INITIAL_PARENT -> {
                    return new InitialParentUseCase();
                }
                case CREDITS -> {
                    return new CreditsUseCase(beforeNode);
                }
                case SETTINGS_INITIAL -> {
                    return new SettingsInitialUseCase(beforeNode);
                }
                case QUIT_INITIAL -> {
                    return new QuitUseCase();
                }
                case SELECT_GAME_TYPE -> {
                    return new SelectGameTypeUseCase(beforeNode);
                }
                case SELECT_GAME_MODE -> {
                    return new SelectGameModeUseCase(beforeNode);
                }
                case SELECT_SAVE -> {
                    return new SelectSaveUseCase(beforeNode);
                }
                case NUMBER_OF_ROUNDS -> {
                    return new NumberOfRoundsUseCase(beforeNode);
                }
                case SELECT_NUMBER_OF_PLAYERS -> {
                    return new SelectNumberOfPlayersUseCase(beforeNode);
                }
                case CONFIRM_NEW_GAME -> {
                    return new ConfirmNewGameUseCase();
                }
                case CONFIRM_LOADED_GAME -> {
                    return new ConfirmLoadedGameUseCase(beforeNode);
                }
                case NO_SAVES -> {
                    return new NoSavesUseCase();
                }


                //game nodes
                case ALREADY_ROLLED -> {
                    return new AlreadyRolledUseCase(beforeNode);
                }
                case AUCTION_ENTRY -> {
                    return new AuctionEntryUseCase(beforeNode);
                }
                case BANKRUPTCY -> {
                    return new BankruptcyUseCase(beforeNode);
                }
                case BUILD_PROPERTY -> {
                    return new BuildPropertyUseCase(beforeNode);
                }
                case BUY_PROPERTY -> {
                    return new BuyUseCase();
                }
                case CALL_ACTION -> {
                    return new CallActionUseCase();
                }
                case PERFORM_STEAL -> {
                    return new PerformStealUseCase(beforeNode);
                }
                case EMPTY_PROPERTY -> {
                    return new EmptyPropertySpaceUseCase();
                }
                case END_TURN -> {
                    return new EndTurnUseCase(beforeNode);
                }
                case EXIT_GAME -> {
                    return new ExitGameUseCase(beforeNode);
                }
                case FINISH_GAME -> {
                    return new FinishGameUseCase();
                }
                case MAIN_PARENT -> {
                    return new MainParentNodeUseCase();
                }
                case SELECT_PROPERTY -> {
                    return new SelectPropertyUseCase(beforeNode);
                }
                case MORTGAGE -> {
                    return new MortgageUseCase(beforeNode);
                }
                case NO_PROPERTIES -> {
                    return new NoPropertiesUseCase(beforeNode);
                }
                case NOTHING_TO_TRADE -> {
                    return new NothingToTradeUseCase(beforeNode);
                }
                case PICK_ITEM_OPPONENT -> {
                    return new PickItemOpUseCase(beforeNode);
                }
                case PICK_ITEM_SELF -> {
                    return new PickItemSelfUseCase(beforeNode);
                }
                case ROLL -> {
                    return new RollUseCase(beforeNode);
                }
                case SAVE_GAME -> {
                    return new SaveGameUseCase(beforeNode);
                }
                case SELECT_ACTION_PROPERTY -> {
                    return new SelectActionPropertyUseCase(beforeNode);
                }
                case SEND_TRADE -> {
                    return new SendTradeUseCase(beforeNode);
                }
                case SETTINGS -> {
                    return new SettingsMenuUseCase(beforeNode);
                }
                case STEAL -> {
                    return new StealUseCase(beforeNode);
                }
                case UN_MORTGAGE -> {
                    return new UnMortgageUseCase(beforeNode);
                }

                //Trade tree nodes
                case TRADE, PICK_PLAYER -> {
                    return new PickPlayerUseCase(beforeNode);
                }
                case ACCEPT_TRADE -> {
                    return new AcceptTradeUseCase();
                }
                case DECLINE_TRADE -> {
                    return new DeclineTradeUseCase();
                }
                case FOLD -> {
                    return new FoldUseCase();
                }
                case TRADE_OPPONENT_PARENT -> {
                    return new TradingOpponentParentNodeUseCase();
                }

                //Auction tree nodes
                case AUCTION_PARENT -> {
                    return new AuctionParent();
                }
                case HIGH_OPTION -> {
                    return new HighOptionUseCase();
                }
                case MEDIUM_OPTION -> {
                    return new MediumOptionUseCase();
                }
                case LOW_OPTION -> {
                    return new LowOptionUseCase();
                }
                case AUCTION_COMPLETE -> {
                    return new AuctionComplete(beforeNode);
                }


            }
            System.err.println("ERROR IN NODE FACTORY");
            System.exit(1);
            return null;
        }
}
