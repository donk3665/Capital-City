package Logic;

import Entities.InternalDataTransfer.InputInformation;
import Entities.InternalDataTransfer.State;
import UseCases.UseCaseInteractor;


import java.util.HashMap;

/**
 * An interface for the different use cases to create a State object containing relevant information to a specific use
 * case.
 */
public abstract class GameNode {
    private static GameLogic gameLogicInteractor;
    public GameLogic getGameLogicInteractor(){
        return gameLogicInteractor;
    }
    /**
     * This method gives the TreeHandlers a reference to the GameLogicInteractor
     * @param interactor - the gameLogicInteractor
     */
    public static void setGameLogicInteractor(GameLogic interactor){
        gameLogicInteractor = interactor;
    }

    static HashMap<NodeNames, String> selectedOptions = new HashMap<>();

    static NodeFactory factory = new NodeFactory();

    public static UseCaseInteractor getCaseInteractor() {
        return caseInteractor;
    }

    public static void setCaseInteractor(UseCaseInteractor caseInteractor) {
        GameNode.caseInteractor = caseInteractor;
    }

    public static UseCaseInteractor caseInteractor;
    private final NodeNames name;

    public GameNode getPreviousNode() {
        return previousNode;
    }

    GameNode previousNode;
    public GameNode(NodeNames name, GameNode previousNode){
        this.name = name;
        this.previousNode = previousNode;
    }
    public NodeNames getName() {
        return name;
    }

    public abstract State create_state();

    public abstract GameNode performInput(InputInformation input);

    /**
     * This method returns a HashMap object stored in the selectedOptions instance attribute.
     * @return a HashMap object stored in the selectedOptions instance attribute.
     */
    public HashMap<NodeNames, String> getSelectedOptions(){
        return selectedOptions;
    }
    public static NodeFactory getFactory() {
        return factory;
    }
}
