/*
 * AndNode class for Regular Expression Library by Korshakov Stepan
 */
package hotheart.regexp.AST.node;

/**
 * @author Korshakov Stepan
 */
public class AndNode extends AbstractNode {

    public AbstractNode left,  right;

    public AndNode(AbstractNode l, AbstractNode r) {
        left = l;
        right = r;
    }

    @Override
    public String toString() {
        return "And(" + left.toString() + ", " + right.toString() + ")";
    }

    @Override
    public String getGraphVizString() {
        String res = getGraphVizNodeString("And");
        res += getChildVizNodeString(left);
        res += getChildVizNodeString(right);
        return res;
    }
}
