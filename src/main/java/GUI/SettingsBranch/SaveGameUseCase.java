package GUI.SettingsBranch;

import GUI.Screens.Screen;


/**
 * This class represents the use case where the users of the game wants to save the game.
 */
public class SaveGameUseCase implements GUINodeInterface {

    String [] options = new String[]{"Yes", "No"};

    Screen currentScreen;
    public SaveGameUseCase(Screen currentScreen){
        this.currentScreen = currentScreen;
    }
    /**
     * This method returns a State object containing the option to confirm the saving of the game and sets up the State
     * object with to prepare for the saving of the game.
     * @return A State object containing the added confirmation option to save the game and sets up some instance
     * attributes  to prepare for the save.
     */
    public String[] getOptions(){
        return options;
    }


    public GUINodeInterface performInput(String input) {
        if (input.equals(options[0])){
            currentScreen.callSave();
            return null;
        }
        return new SettingsMenuUseCase(currentScreen);

    }

}
