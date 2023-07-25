package Interactors;

import Logic.NodeNames;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;

/**
 * This function is used to create the initial state of the state hashmap
 * the initial outputs are all read from the file states.txt
 */
public class StateOutputReader {
    private final HashMap<NodeNames, String> stateMap;
    private static final String stateString = "src/initialoutputs/states.txt";

    public StateOutputReader(){
        this.stateMap = new HashMap<>();
    }

    /**
     * This is a function that reads the state file and creates the intial hash
     * throws an exception if the file is not found
     */
    public void initStateHash(){
        try {
            File stateFile = new File(stateString);
            Scanner stateFileReader = new Scanner(stateFile);
            while (stateFileReader.hasNextLine()){
                String stateString = stateFileReader.nextLine();
                if (stateString.contains("//")){
                    continue;
                }
                String[] stateMapping = stateString.split(",", 2);
                this.stateMap.put(NodeNames.valueOf(stateMapping[0]), stateMapping[1]);
            }

        } catch (FileNotFoundException e){
            System.out.println("The file is not found");
            e.printStackTrace();
        }
    }

    /**
     * Function that outputs the state hash
     *
     * @return the state hashmap
     */
    public HashMap<NodeNames, String> getStateMap(){
        return this.stateMap;
    }
}
