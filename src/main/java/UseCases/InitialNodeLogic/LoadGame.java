package UseCases.InitialNodeLogic;

import Entities.State;
import Interface.NodeLogic;
import Persistence.LoadAccess;
import Persistence.LoadFile;

import java.io.File;

public class LoadGame extends InitialLogic implements NodeLogic {
    public LoadGame() {
        super("Load Game");
    }

    @Override
    public State create_state(int input) {
        State state = beforeLogic();

        //options associated with the next node
        File file = new File("src/gameData");
        LoadAccess load = new LoadFile(file);

        String[] allSaves = load.checkSaves("src/gameData");
        // add save file name to state
        for (String save : allSaves) {
            state.addOptions(save);
        }

        return afterLogic(state);
    }
}
