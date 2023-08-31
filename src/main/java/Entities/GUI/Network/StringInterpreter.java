package Entities.GUI.Network;
import Logic.GameLogic;
import Logic.GeneralGameNode;

public class StringInterpreter{
    private GameLogic interactor;
    public StringInterpreter(GameLogic interactor){
        this.interactor = interactor;
    }

    public void interpretString(String[] message) {
        switch (message[0]) {
            case "MOVE" -> {
                GeneralGameNode.getClientPlayer().setPosition(Integer.parseInt(message[1]));
            }

        }

    }
}
