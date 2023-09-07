package GUI.Screens;

import Entities.ExternalDataTransfer.BasicBoard;
import Entities.ExternalDataTransfer.BasicPlayer;
import GUI.Description;
import GUI.Options;
import GUI.Screens.ScreenElements.ChatBox;
import GUI.Screens.ScreenElements.ImagePathFactory;
import GUI.Screens.ScreenElements.SoundController;
import GUIDataTransfer.GUIInterface;
import Entities.Game.Cell;
import GUIInteractors.PresenterDisplay;
import Network.ServerListener;
import Logic.NodeNames;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class Screen {

    /**
     * InstanceVar gameFrame: JFrame that contains all the contents of the game
     */
    protected static JFrame gameFrame;
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    static Dimension screenSize = new Dimension((int) device.getDefaultConfiguration().getBounds().getWidth(), (int) device.getDefaultConfiguration().getBounds().getHeight());

    public static final double width = screenSize.getWidth();
    public static final double height = screenSize.getHeight();
    public static final double widthAdjust = Math.min(screenSize.getWidth() / 1920.0, screenSize.getHeight() / 1080.0);
    public static final double heightAdjust = Math.min(screenSize.getWidth() / 1920.0, screenSize.getHeight() / 1080.0);
    protected static Options options;
    protected static Description description;
    private static final ImagePathFactory imagePathFactory = new ImagePathFactory();
    private static GUIInterface state;

    public GUIInterface getState() {
        return state;
    }

    public static void setState(GUIInterface newState) {
        state = newState;
    }
    public void setName(NodeNames name) {
        this.name = name;
    }

    public NodeNames getName() {
        return name;
    }

    NodeNames name;

    protected JLayeredPane gamePane = new JLayeredPane();

    protected static BasicBoard currentBoard;

    protected static BasicPlayer currentPlayer;

    public static BasicPlayer getClientPlayer() {
        return clientPlayer;
    }

    public static void setClientPlayer(BasicPlayer clientPlayer) {
        Screen.clientPlayer = clientPlayer;
    }

    protected static BasicPlayer clientPlayer;



    public static BasicPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(BasicPlayer currentPlayer) {
        Screen.currentPlayer = currentPlayer;
    }

    public static BasicBoard getCurrentBoard() {
        return currentBoard;
    }

    public static void setCurrentBoard(BasicBoard currentBoard) {
        Screen.currentBoard = currentBoard;
    }

    private static PresenterDisplay presenterDisplay;

    private static ServerListener listener;

    private static int tempIndex;

    private static String tempName;

    public static int getTempIndex() {
        return tempIndex;
    }

    public static void setTempIndex(int tempIndex2) {
        tempIndex = tempIndex2;
    }

    public static String getTempName() {
        return tempName;
    }

    public static void setTempName(String tempName2) {
        tempName = tempName2;
    }

    public static ChatBox getRecentChatBox() {
        return recentChatBox;
    }

    public static void setRecentChatBox(ChatBox recentChatBox) {
        Screen.recentChatBox = recentChatBox;
    }

    public static ChatBox recentChatBox;

    static{
        JFXPanel fxPanel = new JFXPanel();

    }

    public static SoundController getSoundController() {
        return musicController;
    }

    public static SoundController musicController = new SoundController();
    /**
     * Constructor that configures the JFrame
     */
    public Screen() {

    }
    public static void setPresenterDisplay(PresenterDisplay display){
        presenterDisplay = display;
    }
    public static PresenterDisplay getPresenterDisplay() {
        return presenterDisplay;
    }

    public static void initializeScreen() {
        gameFrame = new JFrame("Capital City");
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setUndecorated(true);
        gameFrame.setLayout(null);
        gameFrame.setResizable(false);
    }

    public static ServerListener getListener() {
        return listener;
    }

    public static void setListener(ServerListener listener) {
        Screen.listener = listener;
    }

    public void attachPanel() {
        gameFrame.setContentPane(gamePane);
    }

    public abstract void attachNonStaticComponents();

    public static Description getDescription() {
        return description;
    }

    public static void setDescription(Description description) {
        Screen.description = description;
    }

    public static Options getOptions() {
        return options;
    }

    public static void setOptions(Options options) {
        Screen.options = options;
    }

    public ImagePathFactory getImagePathFactory() {
        return imagePathFactory;
    }


    /**
     * Remove a component from the Frame
     *
     * @param component: the thing to be removed
     */
    public void remove(Component component, JPanel jpanel) {
        jpanel.remove(component);
    }

    /**
     * Function to show the frame and set it to close on exit
     */
    public void display() {
        device.setFullScreenWindow(gameFrame);

    }

    /**
     * Refresh the screen
     */
    public void refresh() {
        gameFrame.validate();
        gameFrame.repaint();
    }


    public abstract void setUpGamePane();
    public abstract void handleAsynchronousInput(String input);

    public int getCellIndex(Cell cell){
        List<Cell> cellList = currentBoard.getCells();
        for (int i = 0; i< cellList.size(); i++){
            if (cell.equals(cellList.get(i))){
                return i;
            }
        }
        System.err.println("MISSING CELL");
        return -1;
    }
    public void callSave(){
        getPresenterDisplay().saveGame();
    }
    public void exitToMenu(){
        getPresenterDisplay().exitToMenu();
    }

    public void addDisconnectListener(JButton button){
        button.addActionListener(e->{
            getListener().write("DISCONNECT");
        });
    }
}