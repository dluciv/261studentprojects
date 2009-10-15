/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.regexp.AST.node;

/**
 *
 * @author m08ksa
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
