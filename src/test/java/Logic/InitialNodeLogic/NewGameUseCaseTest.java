package Logic.InitialNodeLogic;

import Entities.InternalDataTransfer.State;
import Logic.NodeNames;
import Persistence.LoadFile;
import Persistence.SaveFile;
import UseCases.UseCaseInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class NewGameUseCaseTest {

    @Test
    public void testNewGameUseCaseCreateState(){
        new UseCaseInteractor(new LoadFile
                (new File("src/gameData/test.txt")), new SaveFile(new File("src/gameData/test.txt")));
        SelectGameModeUseCase newGameUseCase = new SelectGameModeUseCase(null);
        State actual = newGameUseCase.create_state();
        ArrayList<String> options = new ArrayList<>();
        options.add("Normal mode");
        options.add("Rich mode");
        Assertions.assertEquals(actual.getId(), NodeNames.INITIAL_PARENT);
        Assertions.assertTrue(actual.isBackEnable());
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
