//package Logic.MainTreeNodeLogic;
//
//import Entities.Input.InputInformation;
//import Entities.Input.State;
//import Logic.GameNode;
//import Logic.NodeLogic;
//import Logic.NodeNames;
//
///**
// * This class represents a use case where a tree path is finished and the game has to tell the user something.
// */
//public class InformationUseCase extends MainGameNode {
//
//    public InformationUseCase(GameNode previousNode) {
//        super(NodeNames.INFORMATION, previousNode);
//    }
//
//    /**
//     * This method creates and returns a State object which represents a State object when a path through a tree has
//     * been finished.
//     * @param input An integer representing the user's input. However, this parameter will not be used for this method.
//     * @return A State object indicating that the path in the tree is finished.
//     */
//    @Override
//    public State create_state() {
//        return afterBottomNode();
//    }
//
//    @Override
//    public GameNode performInput(InputInformation input) {
//        return null;
//    }
//}
