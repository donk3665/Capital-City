package Entities.GUI.Screens;

import Entities.GUI.Description;
import Entities.GUI.Options;
import Logic.NodeNames;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public abstract class Screen {
    /**
     * InstanceVar gameFrame: JFrame that contains all the contents of the game
     */
    protected static JFrame gameFrame;
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static final double widthAdjust = Math.min(screenSize.getWidth()/1920.0, screenSize.getHeight()/1080.0);
    public static final double heightAdjust = Math.min(screenSize.getWidth()/1920.0, screenSize.getHeight()/1080.0);
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    protected static Options options;
    protected static Description description;
    private final ImagePathFactory imagePathFactory = new ImagePathFactory();
    private ArrayList<Component> removableComponents = new ArrayList<>();

    public NodeNames getName() {
        return name;
    }
    private final NodeNames name;


    /**
     * Constructor that configures the JFrame
     */
    public Screen(NodeNames name){
        this.name = name;
    }
    public static void initializeScreen(){
        gameFrame = new JFrame("Capital City");
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setUndecorated(true);
        gameFrame.setLayout(null);
        gameFrame.setResizable(false);

    }
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

    public void addRemovableComponent(Component component, Object constraints, JPanel connect){
        removableComponents.add(component);
        connect.add(component, constraints);
    }

    public void removeComponents(JPanel jpanel){
        removeAll(removableComponents, jpanel);
    }

    /**
     * Remove a component from the Frame
     * @param component: the thing to be removed
     */
    public void remove(Component component, JPanel jpanel){
        jpanel.remove(component);
    }
    public void removeAll(ArrayList<Component> list, JPanel panel){
        for (Component c: list){
            remove(c, panel);
        }
    }
    /**
     * Function to show the frame and set it to close on exit
     */
    public void display(){
        device.setFullScreenWindow(gameFrame);
    }
    /**
     * Refresh the screen
     */
    public void refresh(){
        gameFrame.repaint();
    }

    public abstract void initDisplay();
}
