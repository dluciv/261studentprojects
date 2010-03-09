/*
 * OrNode class for Regular Expression Library by Korshakov Stepan
 */
package hotheart.regexp.AST.node;

/**
 * @author Korshakov Stepan
 */
public class OrNode extends AbstractNode {

    public AbstractNode left,  right;

    public OrNode(AbstractNode l, AbstractNode r) {
        left = l;
        right = r;
    }

    @Override
    public String toString() {
        return "Or(" + left.toString() + ", " + right.toString() + ")";
    }

    @Override
    public String getGraphVizString() {
        String res = getGraphVizNodeString("Or");
        res += getChildVizNodeString(left);
        res += getChildVizNodeString(right);
        return res;
    }
}
