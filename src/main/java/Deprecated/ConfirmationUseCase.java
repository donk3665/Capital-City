//package Logic.MainTreeNodeLogic;
//
//import Entities.*;
//import Logic.GameLogic;
//import Logic.GameNode;
//import Logic.NodeLogic;
//import Logic.NodeNames;
//
///**
// * This class represents a use case where the current player has to confirm something.
// */
//public class ConfirmationUseCase extends MainGameNode {
//
//    public ConfirmationUseCase(GameNode previousNode) {
//        super(NodeNames.CONFIRMATION, previousNode);
//    }
//
//    /**
//     * This method returns a State object either containing a State object representing the end of a tree path or one
//     * that aids in allowing the user to confirm their actions.
//     * @return A State object either containing a State object representing the end of a tree path or one that aids in
//     * allowing the user to confirm their actions.
//     */
//    @Override
//    public State create_state() {
//
//        GameLogicTree confirmationReturn = getConfirmationReturn();
//        GameLogic gameLogicInteractor = getGameLogicInteractor();
//
//        //Gives the user another chance to reconsider their actions
//        if (input == 0) {
//            mainStates[0] = 1;
//            gameLogicInteractor.setCurrentTree(confirmationReturn);
//            return gameLogicInteractor.handleTree(input);
//        }
//        else{
//            return afterBottomNode();
//        }
//    }
//
//    @Override
//    public GameNode performInput(InputInformation input) {
//        return null;
//    }
//
//}
