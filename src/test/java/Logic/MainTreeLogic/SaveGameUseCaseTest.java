package Logic.MainTreeLogic;

import Entities.InternalDataTransfer.State;
import Logic.MainTreeNodeLogic.SettingsBranch.SaveGameUseCase;
import Logic.NodeNames;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SaveGameUseCaseTest {

    @Test
    public void testSaveGameUseCaseCreateState(){
        SaveGameUseCase saveGameUseCase = new SaveGameUseCase(null);
        State actual = saveGameUseCase.create_state();
        ArrayList<String> options = new ArrayList<>();
        options.add("Ok");
        Assertions.assertEquals(actual.getId(), NodeNames.SAVE_GAME);
        Assertions.assertTrue(actual.isSaveGame());
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
