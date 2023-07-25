package Logic.MainTreeLogic;

import Entities.InternalDataTransfer.State;
import Logic.MainTreeNodeLogic.SettingsBranch.SettingsMenuUseCase;
import Logic.NodeNames;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SettingsMenuUseCaseTest {

    @Test
    public void testSettingsMenuUseCaseCreateState(){
        SettingsMenuUseCase settingsMenuUseCase = new SettingsMenuUseCase(null);
        State actual = settingsMenuUseCase.create_state();
        Assertions.assertEquals(actual.getId(), NodeNames.SETTINGS);
        Assertions.assertTrue(actual.isBackEnable());
    }

}
