//package Logic.MainTreeNodeLogic.TradingBranch;
//
//import Entities.Game.Player;
//import Entities.InternalDataTransfer.InputInformation;
//import Entities.InternalDataTransfer.State;
//import Logic.GameNode;
//import Logic.MainTreeNodeLogic.MainGameNode;
//import Logic.NodeInterface;
//import Logic.NodeNames;
//
//public class WaitingTradeUseCase extends MainGameNode {
//
//    public WaitingTradeUseCase() {
//        super(NodeNames.WAIT_TRADE, null);
//    }
//
//
//    /**
//     * This method creates a State
//     * @return A State object containing either one that represents the end of a tree path, or one that contains
//     * required to propose the trade.
//     */
//    @Override
//    public State create_state() {
//
//        State currentState = new State();
//        Player currentPlayer =  getBoard().getPlayers().get(Integer.parseInt(getSelectedOptions().get(NodeNames.PICK_PLAYER)));
//        Player tradingOpponent = getCurrentPlayer();
//
//        currentState.setTradingOpponent(tradingOpponent);
//
//        return currentState;
//    }
//
//    @Override
//    public NodeInterface performInput(InputInformation input) {
//        return null;
//
//    }
//}