// CLASS DEPRECATED
//
//
//
// package Logic.MainTreeNodeLogic;
//
//import Entities.*;
//import Logic.GameLogic;
//import Logic.GameNode;
//import Logic.NodeNames;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
///**
// * This class represents a use case where the current player has to choose a player to trade with.
// */
//public class PickPlayerUseCase extends MainGameNode{
//
//    public PickPlayerUseCase(GameNode previousNode) {
//        super(NodeNames.PICK_PLAYER, previousNode);
//    }
//
//    /**
//     * This method creates a State object and adds the selected player to the State object. It will also add options to
//     * the State object. These options are the items from the inventory of the selected player.
//     * @return A State object containing the selected player and a list of the chosen player's properties.
//     */
//    @Override
//    public State create_state() {
//        Board board = getBoard();
//        GameLogic gameLogicInteractor = getGameLogicInteractor();
//        Player currentPlayer = getCurrentPlayer();
//        HashMap<NodeNames, String> selectedOptions = getSelectedOptions();
//
//        State currentState = new State();
//        currentState.setId(getName());
//        currentState.setBackEnable(true);
//        //adds the chosen player index in selected options (who the current player wants to trade with)
//
//
////        /*
////        We asked them for an input in reference to a list with their player removed,
////        thus we have to add 1 to their input in certain cases
////        */
////        if (input >= getCurrentPlayerIndex()) {
////            input += 1;
////        }
////        //getting the player the user wants to trade with
////        selectedOptions.put("PickPlayer", input);
////
////        //provide item options from the inventory of the selected player
////        Player selectedPlayer = board.getPlayers().get(input);
////        ArrayList<Property> playerProperties = selectedPlayer.getProperties();
////        if (playerProperties.isEmpty() || currentPlayer.getProperties().isEmpty()){
////            gameLogicInteractor.transverseCurrentTree(1);
////            currentState = gameLogicInteractor.handleTree(0);
////            return currentState;
////        }
////        //using "i" starting from 0 to number of properties the player has - 1
////        for (Property playerProperty : playerProperties) {
////            currentState.addOptions(playerProperty.getName());
////        }
//
//        return currentState;
//    }
//
//    @Override
//    public GameNode performInput(InputInformation input) {
//        return null;
//    }
//}
