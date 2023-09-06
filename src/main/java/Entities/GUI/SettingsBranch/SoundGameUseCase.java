package Entities.GUI.SettingsBranch;

import Entities.GUI.Screens.Screen;
import Entities.GUI.SettingsController.SettingsFunction;
import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeInterface;
import Logic.NodeNames;
import org.w3c.dom.Node;

/**
 * This class represents the use case where the users want to end the game.
 */
public class SoundGameUseCase implements GUINodeInterface {

    String [] options = new String[]{"BACK"};

    private Screen currentScreen;
    public SoundGameUseCase(Screen currentScreen){
        this.currentScreen = currentScreen;
    }

    public String[] getOptions(){
        return options;
    }


    @Override
    public GUINodeInterface performInput(String input) {
        return new SettingsMenuUseCase(currentScreen);
    }


}
