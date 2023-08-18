package Entities.GUI.Screens;

import Logic.NodeNames;

import javax.swing.*;

public class ImagePathFactory {

    private final ImageIcon[] bgs = new ImageIcon[5];

    public String getBackButtonPath(){
        return "/Images/BackButton.png";
    }


    public ImagePathFactory(){
        bgs[0] = new ImageIcon(ImageCreator.getAndScaleImage("/backgrounds/Bg1.jpg", 1920, 1080));
        bgs[1] = new ImageIcon(ImageCreator.getAndScaleImage("/backgrounds/credits.jpg", 1920, 1080));
        bgs[2] = new ImageIcon(ImageCreator.getAndScaleImage("/backgrounds/inGameBg.jpg", 1920, 1080));
        bgs[3] = new ImageIcon(ImageCreator.getAndScaleImage("/backgrounds/AuctionBg.jpg", 1920, 1080));
        bgs[4] = new ImageIcon(ImageCreator.getAndScaleImage("/backgrounds/winBg.jpg", 1920, 1080));
    }

    public String[] getButtonPaths(){
        String[] paths = new String[]{
                        "/Images/MenuAssets/button1.png", "/Images/MenuAssets/button2.png",
                        "/Images/MenuAssets/button3.png", "/Images/MenuAssets/button4.png",
                        "/Images/MenuAssets/button5.png", "/Images/MenuAssets/button6.png",
                        "/Images/MenuAssets/button7.png", "/Images/MenuAssets/button8.png",
                        "/Images/MenuAssets/button9.png",};
        return paths;
//        switch (node){
//            case INITIAL_PARENT, SETTINGS_INITIAL, CREDITS, QUIT_INITIAL, SELECT_GAME_TYPE, SELECT_NUMBER_OF_PLAYERS,
//                    CONFIRM_LOADED_GAME, CONFIRM_NEW_GAME, SELECT_SAVE, SELECT_GAME_MODE, NUMBER_OF_ROUNDS, NO_SAVES-> {
//                String[] paths = new String[]{
//                        "/Images/MenuAssets/button1.png", "/Images/MenuAssets/button2.png",
//                        "/Images/MenuAssets/button3.png", "/Images/MenuAssets/button4.png",
//                        "/Images/MenuAssets/button5.png", "/Images/MenuAssets/button6.png",
//                        "/Images/MenuAssets/button7.png", "/Images/MenuAssets/button8.png",
//                        "/Images/MenuAssets/button9.png",
//                };
//                return paths;
//            }
//            case ALREADY_ROLLED, AUCTION_ENTRY, BANKRUPTCY, BUILD_PROPERTY,BUY_PROPERTY, CALL_ACTION, PERFORM_STEAL, EMPTY_PROPERTY, END_TURN, EXIT_GAME,
//                    FINISH_GAME, MAIN_PARENT, SELECT_PROPERTY, MORTGAGE, NO_PROPERTIES, NOTHING_TO_TRADE, PICK_PLAYER, PICK_ITEM_OPPONENT, PICK_ITEM_SELF, ROLL,
//                    SAVE_GAME, SELECT_ACTION_PROPERTY, SEND_TRADE, SETTINGS, STEAL, UN_MORTGAGE, TRADE, AUCTION_PARENT
//                    -> {
//                String[] paths = new String[]{
//                        "/Images/MenuAssets/button1.png", "/Images/MenuAssets/button2.png",
//                        "/Images/MenuAssets/button3.png", "/Images/MenuAssets/button4.png",
//                        "/Images/MenuAssets/button5.png", "/Images/MenuAssets/button6.png",
//                        "/Images/MenuAssets/button7.png", "/Images/MenuAssets/button8.png",
//                };
//                return paths;
//            }
//        }
//        System.err.println("ERROR IN BUTTON IMAGE FACTORY");
//        System.exit(1);
//        return null;
    }
    public String[] getAuctionUIImagePaths(){
        String[] paths = new String[]{
                "/Images/InGameAssets/AuctionAssets/Picture2.png", "/Images/InGameAssets/AuctionAssets/Picture3.png",
                "/Images/InGameAssets/AuctionAssets/Picture4.png", "/Images/InGameAssets/AuctionAssets/Picture5.png",
                "/Images/InGameAssets/AuctionAssets/Picture6.png", "/Images/InGameAssets/AuctionAssets/Picture7.png",
                "/Images/InGameAssets/AuctionAssets/AuctionOptionsContainer.png"
        };
        return paths;
    }

    public String[] getUIImagePaths(){
        String[] paths = new String[]{
                "/Images/InGameAssets/MainScreenAssets/Board.png", "/Images/InGameAssets/MainScreenAssets/ChatContainer.png",
                "/Images/InGameAssets/MainScreenAssets/OptionContainer.png", "/Images/InGameAssets/MainScreenAssets/TopLeftBorder.png",
                "/Images/InGameAssets/MainScreenAssets/TopMiddleBorder.png", "/Images/InGameAssets/MainScreenAssets/TopRightBorder.png",
        };
        return paths;
    }
    public String[] getEndingImagePaths(){
        String[] paths = new String[]{
                "/Images/InGameAssets/EndingAssets/Picture6.png","/Images/InGameAssets/EndingAssets/Picture1.png","/Images/InGameAssets/MainScreenAssets/ChatContainer.png"
        };
        return paths;
    }
    public static String getPlayerIconPath(int playerIndex){
        return "/Images/InGameAssets/Icons/Picture"+playerIndex+".png";
    }

    public ImageIcon getBackgrounds(int index){
        return bgs[index];
    }
}
