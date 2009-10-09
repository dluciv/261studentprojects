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
}
