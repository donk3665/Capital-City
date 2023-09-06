package main;

import GUIInteractors.PresenterDisplay;

import java.io.File;
import java.net.URISyntaxException;

public class Main {

    private final static String persistenceFilePath = System.getProperty("user.home") + File.separator + "Documents" + File.separator +
            "CapitalCity" + File.separator + "resources" + File.separator + "gameSaves";

    /**
     * Entry point to the application
     */
    public static void main(String[] args) throws URISyntaxException {

        PresenterDisplay display = new PresenterDisplay();
        File customDir = new File(persistenceFilePath);
        if (!customDir.exists()){
            if (customDir.mkdirs()){
                System.out.println(customDir + " created");
            }
            else {
                System.out.println(customDir + " created");
            }
        }
        display.playGame(customDir);


    }
}

