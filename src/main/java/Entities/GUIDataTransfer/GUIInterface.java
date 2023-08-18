package Entities.GUIDataTransfer;

import Entities.ExternalDataTransfer.BasicBoard;
import Entities.Game.Player;
import Entities.Game.Property;
import Logic.NodeNames;

import java.util.ArrayList;

public interface GUIInterface {

    BasicBoard getCurrentBoard();
    String getRoll();

    String getDescription();

    ArrayList<String> getOptions();

    NodeNames getId();

    Property getBiddingProperty();

    Player getPlayer();

}
