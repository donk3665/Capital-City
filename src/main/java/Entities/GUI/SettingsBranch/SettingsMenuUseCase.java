package Entities.GUI.SettingsBranch;

import Entities.GUI.Screens.Screen;
import Entities.Game.OrderedStringHashmap;
import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeInterface;
import Logic.NodeNames;
import org.w3c.dom.Node;

import java.util.HashMap;

/**
 * This class represents a use case where the settings menu is to be set up.
 */
public class SettingsMenuUseCase implements GUINodeInterface {

    private Screen currentScreen;
    OrderedStringHashmap<GUINodeInterface> options;

    public SettingsMenuUseCase(Screen currentScreen) {
        this.currentScreen = currentScreen;
        options = new OrderedStringHashmap<>(){
            {
                put("SAVE", new SaveGameUseCase(currentScreen));
                put("EXIT_GAME", new ExitGameUseCase(currentScreen));
                put("BACK", null);
            }
        };
    }

    /**
     * This method creates a State object for the initialization of the settings menu.
     * @return A State object set up for the display of the settings menu.
     */
    public String[] getOptions(){
        return options.getKeys();
    }

    @Override
    public GUINodeInterface performInput(String input) {
        return options.get(input);
    }

}
