package TreeHandlers.MainTreeLogic;

import Entities.State;
import TreeHandlers.MainTreeNodeLogic.NoProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class NoPropertiesTest {

    @Test
    public void testNoPropertiesCreateCase(){
        NoProperties noProperties = new NoProperties();
        State actual = noProperties.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Ok");
        Assertions.assertEquals(actual.getId(), "User Has No Properties (Manage Properties)");
        Assertions.assertEquals(actual.getOptions(), options);
        Assertions.assertEquals(actual.getDescription(), "You have no properties :(");
    }

}
