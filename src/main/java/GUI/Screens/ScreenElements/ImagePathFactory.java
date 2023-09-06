package GUI.Screens.ScreenElements;

import GUI.Screens.Screen;

import javax.swing.*;

public class ImagePathFactory {

    private final ImageIcon[] bgs = new ImageIcon[7];

    public String getBackButtonPath(){
        return "/Images/BackButton.png";
    }


    public ImagePathFactory(){
        bgs[0] = new ImageIcon(ImageCreator.getAndScaleImage("/backgrounds/Bg1.jpg", (int) Screen.width, (int) Screen.height));
        bgs[1] = new ImageIcon(ImageCreator.getAndScaleImage("/backgrounds/credits.jpg", (int) Screen.width, (int) Screen.height));
        bgs[2] = new ImageIcon(ImageCreator.getAndScaleImage("/backgrounds/inGameBg.jpg", (int) Screen.width, (int) Screen.height));
        bgs[3] = new ImageIcon(ImageCreator.getAndScaleImage("/backgrounds/AuctionBg.jpg", (int) Screen.width, (int) Screen.height));
        bgs[4] = new ImageIcon(ImageCreator.getAndScaleImage("/backgrounds/winBg.jpg", (int) Screen.width, (int) Screen.height));
        bgs[5] = new ImageIcon(ImageCreator.getAndScaleImage("/backgrounds/connectionBg.jpg", (int) Screen.width, (int) Screen.height));
        bgs[6] = new ImageIcon(ImageCreator.getAndScaleImage("/backgrounds/joinBg.jpg", (int) Screen.width, (int) Screen.height));
    }

    public String[] getButtonPaths(){
        String[] paths = new String[]{
                        "/Images/MenuAssets/button1.png", "/Images/MenuAssets/button2.png",
                        "/Images/MenuAssets/button3.png", "/Images/MenuAssets/button4.png",
                        "/Images/MenuAssets/button5.png", "/Images/MenuAssets/button6.png",
                        "/Images/MenuAssets/button7.png", "/Images/MenuAssets/button8.png",
                        "/Images/MenuAssets/button9.png",};
        return paths;
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
    public String[] getConnectionImagePaths(){
        String[] paths = new String[]{
                "/Images/InGameAssets/ConnectionAssets/Picture5.png","/Images/InGameAssets/ConnectionAssets/Picture6.png","/Images/InGameAssets/ConnectionAssets/Picture7.png"
        };
        return paths;
    }
    public String[] getJoinImagePaths(){
        String[] paths = new String[]{
                "/Images/InGameAssets/JoinAssets/Picture3.png","/Images/InGameAssets/JoinAssets/Picture4.png"
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
