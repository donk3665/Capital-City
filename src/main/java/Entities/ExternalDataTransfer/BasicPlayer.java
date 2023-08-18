package Entities.ExternalDataTransfer;

import Entities.Game.Property;

import java.util.ArrayList;

public interface BasicPlayer {
    int getJailCards();
    int getPosition();
    boolean isInJail();
    String getName();
    int getMoney();
    ArrayList<Property> getProperties();
    int getPlayerIndex();
}
