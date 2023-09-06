package GUI.SettingsBranch;

import GUI.Screens.Screen;

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
