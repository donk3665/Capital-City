package Entities.GUI.Screens;

import Logic.NodeNames;

public class ImagePathFactory {
    private String[] paths;

    public String getBackButtonPath(){
        return "/Images/BackButton.png";
    }
    public String[] getButtonPaths(NodeNames node){
        switch (node){
            case INITIAL_PARENT, SETTINGS_INITIAL, CREDITS, QUIT_INITIAL, SELECT_GAME_TYPE, SELECT_NUMBER_OF_PLAYERS,
                    CONFIRM_LOADED_GAME, CONFIRM_NEW_GAME, SELECT_SAVE, SELECT_GAME_MODE, NUMBER_OF_ROUNDS, NO_SAVES-> {
                paths = new String[]{
                        "/Images/MenuAssets/button1.png", "/Images/MenuAssets/button2.png",
                        "/Images/MenuAssets/button3.png", "/Images/MenuAssets/button4.png",
                        "/Images/MenuAssets/button5.png", "/Images/MenuAssets/button6.png",
                        "/Images/MenuAssets/button7.png", "/Images/MenuAssets/button8.png",
                        "/Images/MenuAssets/button9.png",
                };
                return paths;
            }
        }
        System.err.println("ERROR IN BUTTON IMAGE FACTORY");
        System.exit(1);
        return null;
    }
    public String[] getImagePaths(NodeNames node){
        switch (node){
//            case QUIT_INITIAL-> {
//                paths = new String[]{
//                        "/Images/MenuAssets/button1.png",
//                };
//                return paths;
//            }
            default -> {
                return null;
            }
        }
    }
}
