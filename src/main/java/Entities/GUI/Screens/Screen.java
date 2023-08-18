package Entities.GUI.Screens;

import Entities.ExternalDataTransfer.BasicBoard;
import Entities.ExternalDataTransfer.BasicPlayer;
import Entities.GUI.Description;
import Entities.GUI.Options;
import Entities.GUIDataTransfer.GUIInterface;
import Entities.Game.Cell;
import Logic.NodeNames;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;


public abstract class Screen {
    /**
     * InstanceVar gameFrame: JFrame that contains all the contents of the game
     */
    protected static JFrame gameFrame;

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static final double width = screenSize.getWidth();
    public static final double height = screenSize.getHeight();

    public static final double widthAdjust = Math.min(screenSize.getWidth() / 1920.0, screenSize.getHeight() / 1080.0);
    public static final double heightAdjust = Math.min(screenSize.getWidth() / 1920.0, screenSize.getHeight() / 1080.0);
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
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

    //BackgroundPanel mainPanel = new BackgroundPanel();

    protected JLayeredPane gamePane = new JLayeredPane();

    protected static BasicBoard currentBoard;

    protected static BasicPlayer currentPlayer;

    protected static ChatBox actualChatBox;

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


    /**
     * Constructor that configures the JFrame
     */
    public Screen() {

    }

    public static void initializeScreen() {
        gameFrame = new JFrame("Capital City");
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setUndecorated(true);
        gameFrame.setLayout(null);
        gameFrame.setResizable(false);
    }

    public void attachPanel() {
        attachNonStaticComponents();
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
        gameFrame.repaint();
    }


    public abstract void setUpGamePane();

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
}