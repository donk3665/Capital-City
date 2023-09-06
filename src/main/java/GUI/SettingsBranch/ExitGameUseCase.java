package GUI.SettingsBranch;

import GUI.Screens.Screen;

/**
 * This class represents the use case where the users want to end the game.
 */
public class ExitGameUseCase implements GUINodeInterface {

    String [] options = new String[]{"Yes", "No"};

    private Screen currentScreen;
    public ExitGameUseCase(Screen currentScreen){
        this.currentScreen = currentScreen;
    }
    public String[] getOptions(){
        return options;
    }

    @Override
    public GUINodeInterface performInput(String input) {
        if (input.equals(options[0])){
            currentScreen.exitToMenu();
            return null;
        }
        return new SettingsMenuUseCase(currentScreen);
    }

}
