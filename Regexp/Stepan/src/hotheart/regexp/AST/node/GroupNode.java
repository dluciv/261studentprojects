/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.regexp.AST.node;

/**
 *
 * @author m08ksa
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
