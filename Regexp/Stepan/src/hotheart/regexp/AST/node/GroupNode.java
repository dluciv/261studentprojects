/*
 * GroupNode class for Regular Expression Library by Korshakov Stepan
 */
package hotheart.regexp.AST.node;

/**
 * @author Korshakov Stepan
 */
public class GroupNode extends AbstractNode {

    public AbstractNode inner;

    public GroupNode(AbstractNode node) {
        inner = node;
    }

    @Override
    public String toString() {
        return "Group(" + inner.toString() + ")";
    }

    @Override
    public String getGraphVizString() {
        String res = getGraphVizNodeString("Group");
        res += getChildVizNodeString(inner);
        return res;
    }
}
