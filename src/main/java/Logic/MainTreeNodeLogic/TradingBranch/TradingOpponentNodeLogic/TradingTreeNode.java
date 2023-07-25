package Logic.MainTreeNodeLogic.TradingBranch.TradingOpponentNodeLogic;

import Logic.GameNode;
import Logic.GeneralGameNode;
import Logic.NodeNames;

/**
 * This class handles the logic of trading tree nodes.
 */
public abstract class TradingTreeNode extends GeneralGameNode {
    public TradingTreeNode(NodeNames name, GameNode previousNode) {
        super(name, previousNode);
    }

}
