package Logic.InitialNodeLogic;

import Logic.GameNode;
import Logic.NodeNames;

public abstract class InitialGameNode extends GameNode {
    public InitialGameNode(NodeNames name, GameNode previousNode) {
        super(name, previousNode);
    }
}
