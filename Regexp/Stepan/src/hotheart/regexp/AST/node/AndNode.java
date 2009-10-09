/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.regexp.AST.node;

/**
 *
 * @author m08ksa
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
}
