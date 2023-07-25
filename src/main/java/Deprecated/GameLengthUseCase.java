//package Logic.InitialNodeLogic;
//import Entities.Input.InputInformation;
//import Entities.Input.State;
//import Logic.GameNode;
//import Logic.NodeNames;
//import java.util.HashMap;
//
///**
// * This class represents the use case where the player has chosen the game length.
// */
//public class GameLengthUseCase extends InitialGameNode {
//
//    HashMap <String, String> options = new HashMap<>(){
//        {
//            put(0+ " rounds","30");
//            put(1+ " rounds","60");
//            put(2+ " rounds","90");
//        }
//    };
//    public GameLengthUseCase(GameNode previousNode) {
//        super(NodeNames.CHOOSE_GAME_LENGTH, previousNode);
//    }
//    @Override
//    public State create_state() {
//        State state = new State();
//        //getting the game length the user requested
//        state.setBackEnable(true);
//        state.addOptions(options.keySet().toArray(new String[0]));
//
//        return state;
//    }
//
//    @Override
//    public GameNode performInput(InputInformation input) {
//        getSelectedOptions().put(getName(), options.get(input.getInput()));
//        return getFactory().getNode(NodeNames.CONFIRM_NEW_GAME, this);
////        if (input != 3){
////            this.getSelectedOptions().put("GameLength", lengthMap.get(input) * getSelectedOptions().get("NumOfPlayers"));
////        }
////        else{
////            this.getSelectedOptions().put("GameLength",-1);
////        }
//    }
//}
