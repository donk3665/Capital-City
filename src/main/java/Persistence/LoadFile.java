package Persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
/**
 * Class which is responsible for the loading of files into text files
 */
public class LoadFile implements LoadAccess{
    private File file;

    public LoadFile(File file){
        this.file = file;
    }

    public void setFile(String newFile){
        file = new File(newFile);
    }


    /**
     * Searches the given folder denoted by folderPath and returns an array
     * of Files inside the given folder. Used by UseCaseInteractor to display
     * a selection of save files to the user when selecting which file to load a game from.
     *
     * @return  an array of the user's save files.
     */

    public String[] checkSaves(){
        // checkSaves searches the given folder and returns an array of file names
        // the user selects the save file from the list of file names
        // loadGame takes in the filePath of the selected file name
        // and returns a String array of Player and Board data
        // the GameCreationInteractor will handle creating the class instances

        File folder = new File(file.getAbsolutePath());
        return folder.list();
    }


    /**
     * Loads the game data from this.file.
     *
     * @return an Arraylist of 3 Arraylists of String[] arrays which represent saved data.
     *         The first Arraylist of String[] arrays represents either Player instance
     *         attributes or Property instance attributes of a Property owned by a Player.
     *         The second Arraylist of String[] arrays represents key-value pairs for a
     *         Player to their position on the Board. The third Arraylist of String[]
     *         arrays represents the main states for Tree data.
     *
     * @throws FileNotFoundException in the case that this.file does not exist
     */

    public ArrayList<ArrayList<String[]>> loadGame(String filePath) throws FileNotFoundException {
        // loadGame reads the given filePath and returns an ArrayList of String arrays
        // each new line on in the txt file given by filePath contains the instance attributes of a Player instance
        // the Board instance attributes are separated from the Player instance attributes by a header "Board"
        // the Board instance attributes are stored in the last element of the ArrayList

        ArrayList<ArrayList<String[]>> gameInfo = new ArrayList<>();
        ArrayList<String[]> players = new ArrayList<>();
        ArrayList<String[]> savedTree = new ArrayList<>();
        boolean player = false;
        boolean tree = false;

        File gameData = new File(file.getPath() + "/" + filePath);
        Scanner scan = new Scanner(gameData);

        while (scan.hasNextLine()) {
            String data = scan.nextLine();

            switch (data.trim()) {
                case "playerStart":
                    player = true;
                    break;
                case "playerEnd":
                    player = false;
                    break;
                case "treeStart":
                    tree = true;
                    break;
                case "treeEnd":
                    tree = false;
                    break;
            }

            if (player){
                String[] playerAttributes = data.trim().split(","); // denotes either a Player instance or a Property instance owned by a Player
                players.add(playerAttributes);
            } else if (tree) {
                String[] treeData = data.trim().split(","); // denotes an array of Integers representing mainStates
                savedTree.add(treeData);
            }
        }
        players.remove(0);
        savedTree.remove(0);
        gameInfo.add(players);
        gameInfo.add(savedTree);

        return gameInfo;
    }

    @Override
    public ArrayList<String> getRawData(String filePath) throws FileNotFoundException {
        ArrayList<String> data = new ArrayList<>();
        File gameData = new File(file.getPath() + "/" + filePath);
        Scanner scan = new Scanner(gameData);

        while (scan.hasNextLine()) {
            String line =scan.nextLine();
            data.add(line);
        }
        return data;
    }


    /**
     * Loads all the instance attributes of properties in properties.txt as an array of String values.
     *
     * @return an Arraylist of String[] subarrays where each String[] array contains instance attributes of a Property
     * @throws FileNotFoundException in case there is an error with finding the properties.txt file
     */
    public ArrayList<String[]> loadProperties() throws FileNotFoundException {
        // return array of all properties in txt file as Strings
        // GameCreationInteractor will parse Strings to create Property instances
        ArrayList<String[]> allProperties = new ArrayList<>();
        InputStream properties = getClass().getResourceAsStream("/save/properties.txt");
        assert properties != null;
        Scanner scan = new Scanner(properties);

        while (scan.hasNextLine()) {
            String property = scan.nextLine();
            String[] propertyAttributes = property.split(",");
            allProperties.add(propertyAttributes);
        }
        return allProperties;
    }


    /**
     * Return a list of all the cards as a list of Strings
     * @return a list of strings that represents all the cards in the game
     * @throws IOException in case there was an error reading the file
     *
     */

    @Override
    public List<String> loadCards(String place){
        InputStream stream = getClass().getResourceAsStream(place);
        ArrayList<String> data = new ArrayList<>();
        Scanner scan = new Scanner(stream);

        while (scan.hasNextLine()) {
            String line =scan.nextLine();
            data.add(line);
        }

        return data;

    }
}
