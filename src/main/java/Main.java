import Entities.Stupid;
import GUI.PresenterDisplay;
import java.io.File;

public class Main {
    private final static String persistenceFilePath = "src/main/resources/gameSaves/";


    /**
     * Entry point to the application
     */
    public static void main(String[] args) {
        PresenterDisplay display = new PresenterDisplay();
        Stupid.setMonitorAppear(Integer.parseInt(args[0]));
        File file = new File(persistenceFilePath);
        display.playGame(file);
    }
}

