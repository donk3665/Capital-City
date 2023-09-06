package Persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * General interface for loading with files
 */
public interface LoadAccess {
    ArrayList<ArrayList<String[]>> loadGame(String stream) throws FileNotFoundException;

    ArrayList<String> getRawData(String file)throws FileNotFoundException;
    String[] checkSaves();
    ArrayList<String[]> loadProperties() throws FileNotFoundException;

    List<String> loadCards(String place) throws IOException;

}
