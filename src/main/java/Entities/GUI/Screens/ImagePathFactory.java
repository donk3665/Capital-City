package Entities.GUI.Screens;

import Logic.NodeNames;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePathFactory {

    private final ImageIcon[] bgs = new ImageIcon[4];

    public String getBackButtonPath(){
        return "/Images/BackButton.png";
    }

    public ImagePathFactory(){
        bgs[0] = new ImageIcon(ImageCreator.scaleImage(ImageCreator.getImageFromPath("/backgrounds/Bg1.jpg"), 1920, 1080));
        bgs[1] = new ImageIcon(ImageCreator.scaleImage(ImageCreator.getImageFromPath("/backgrounds/credits.jpg"), 1920, 1080));
        bgs[2] = new ImageIcon(ImageCreator.scaleImage(ImageCreator.getImageFromPath("/backgrounds/inGameBg.jpg"), 1920, 1080));
        //bgs[3] = new ImageIcon(ImageCreator.scaleImage(ImageCreator.getImageFromPath("/backgrounds/credits.jpg"), 1920, 1080));
    }

    public String[] getButtonPaths(NodeNames node){
        switch (node){
            case INITIAL_PARENT, SETTINGS_INITIAL, CREDITS, QUIT_INITIAL, SELECT_GAME_TYPE, SELECT_NUMBER_OF_PLAYERS,
                    CONFIRM_LOADED_GAME, CONFIRM_NEW_GAME, SELECT_SAVE, SELECT_GAME_MODE, NUMBER_OF_ROUNDS, NO_SAVES-> {
                String[] paths = new String[]{
                        "/Images/MenuAssets/button1.png", "/Images/MenuAssets/button2.png",
                        "/Images/MenuAssets/button3.png", "/Images/MenuAssets/button4.png",
                        "/Images/MenuAssets/button5.png", "/Images/MenuAssets/button6.png",
                        "/Images/MenuAssets/button7.png", "/Images/MenuAssets/button8.png",
                        "/Images/MenuAssets/button9.png",
                };
                return paths;
            }
            case ALREADY_ROLLED, AUCTION_ENTRY, BANKRUPTCY, BUILD_PROPERTY,BUY_PROPERTY, CALL_ACTION, PERFORM_STEAL, EMPTY_PROPERTY, END_TURN, EXIT_GAME,
                    FINISH_GAME, MAIN_PARENT, SELECT_PROPERTY, MORTGAGE, NO_PROPERTIES, NOTHING_TO_TRADE, PICK_PLAYER, PICK_ITEM_OPPONENT, PICK_ITEM_SELF, ROLL,
                    SAVE_GAME, SELECT_ACTION_PROPERTY, SEND_TRADE, SETTINGS, STEAL, UN_MORTGAGE, TRADE
                    -> {
                String[] paths = new String[]{
                        "/Images/MenuAssets/button1.png", "/Images/MenuAssets/button2.png",
                        "/Images/MenuAssets/button3.png", "/Images/MenuAssets/button4.png",
                        "/Images/MenuAssets/button5.png", "/Images/MenuAssets/button6.png",
                        "/Images/MenuAssets/button7.png", "/Images/MenuAssets/button8.png",
                };
                return paths;
            }
        }
        System.err.println("ERROR IN BUTTON IMAGE FACTORY");
        System.exit(1);
        return null;
    }
    public String[] getUIImagePaths(){
        String[] paths = new String[]{
                "/Images/InGameAssets/Board.png", "/Images/InGameAssets/ChatContainer.png",
                "/Images/InGameAssets/OptionContainer.png", "/Images/InGameAssets/TopLeftBorder.png",
                "/Images/InGameAssets/TopMiddleBorder.png", "/Images/InGameAssets/TopRightBorder.png",
        };
        return paths;
    }

    public ImageIcon getBackgrounds(int index){
        return bgs[index];
    }
}
