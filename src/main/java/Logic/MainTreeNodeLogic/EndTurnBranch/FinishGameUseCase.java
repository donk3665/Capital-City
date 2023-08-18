package Logic.MainTreeNodeLogic.EndTurnBranch;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import Logic.GameNode;
import Entities.Game.Player;
import Logic.MainTreeNodeLogic.MainGameNode;
import Logic.NodeNames;

import java.util.List;

/**
 * This class represents a use case where the game ends either by all but one player going bankrupt, or the
 * max turn limit being reached.
 */
public class FinishGameUseCase extends MainGameNode {
    public FinishGameUseCase(){
        super(NodeNames.FINISH_GAME, null);
    }

    @Override
    public State create_state() {
        State currentState = new State();
        currentState.addOptions("Exit");

        // The case that there is only one player in the player array
        if (getPlayers().size() == 1) {
            List<Player> players = getPlayers();
            Player winningPlayer = players.get(0);
            String winningString = winningPlayer.getName() + " won the game!!!";
            currentState.setDescription(winningString);
        }
        // handles the case where the max turns are met
        else if (mainStates[3] >= mainStates[4]) {
            List<Player> players = getPlayers();
            String player_with_most_money = "";
            int max_money = 0;
            for (Player player : players) {
                if (player.getMoney() > max_money) {
                    player_with_most_money = player.getName();
                    max_money = player.getMoney();
                }
            }
            currentState.setDescription("Max turn reaches, " + player_with_most_money + " has the most money and wins the game.");
        }

        mainStates[0] = 1;
        return currentState;

    }

    @Override
    public GameNode performInput(InputInformation input) {
        return getFactory().getNode(NodeNames.INITIAL_PARENT);
    }
}
