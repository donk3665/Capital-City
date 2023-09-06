package Network;
import Logic.GameLogic;
import Logic.GeneralGameNode;

public class GameInfoInterpreter {
    private GameLogic interactor;
    public GameInfoInterpreter(GameLogic interactor){
        this.interactor = interactor;
    }

    public void interpretString(String[] message) {
        String returnMessage;
        switch (message[0]) {
            case "MOVE" -> {
                GeneralGameNode.getCurrentPlayer().setPosition(Integer.parseInt(message[1]));
            }
            case "ACTION_SPACE_CARD" ->{
                returnMessage = GeneralGameNode.performAction(Integer.parseInt(message[1]));
            }
            case "PAID_RENT" ->{
                returnMessage = GeneralGameNode.payProperty(GeneralGameNode.getCurrentPlayer());
            }
            case "BUY" ->{
                GeneralGameNode.buyProperty(GeneralGameNode.getCurrentPlayer());
            }


        }

    }
}
