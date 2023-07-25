//package Logic.InitialNodeLogic;
//
//import Entities.Game.Board;
//import Entities.Input.InputInformation;
//import Entities.Input.State;
//import Logic.GameNode;
//import Logic.NodeLogic;
//import Logic.NodeNames;
//import Persistence.LoadAccess;
///**
// * This class represents the use case where the player has confirmed their choice of loading a saved
// * game.
// */
//public class CreateLoadedGameUseCase extends GameNode {
//    public CreateLoadedGameUseCase() {
//        super(NodeNames.CREATE_LOADED_GAME, null);
//    }
//
//    @Override
//    public State create_state() {
//        State state;
//        //in "Create Loaded Game" node
//        try {
//            LoadAccess load = caseInteractor.getLoadAccess();
//            String[] allSaves = load.checkSaves(load.getFile().getAbsolutePath());
//
//            int integerFile = selectedOptions.get("SaveChoice");
//            Board board = caseInteractor.loadSavedBoard(allSaves[integerFile]);
//            int[] initialStates = caseInteractor.loadInitialStates(allSaves[integerFile]);
//
//            caseInteractor.createGame(board, initialStates);
//            state = caseInteractor.getLogicInteractor().getCurrentState();
//        } catch (Exception IOException){
//            return null;
//        }
//        return state;
//    }
//
//    @Override
//    public GameNode performInput(InputInformation input) {
//        return null;
//    }
//}
