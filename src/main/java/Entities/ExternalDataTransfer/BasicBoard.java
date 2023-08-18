package Entities.ExternalDataTransfer;

import Entities.Game.Cell;

import java.util.List;

public interface BasicBoard {
    List<BasicPlayer> getBasicPlayers();
    List<Cell> getCells();

    int[] getAuctionStates();
}
