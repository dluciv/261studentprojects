/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.regexp.AST.node;

/**
 *
 * @author m08ksa
 */
public class CycleNode extends AbstractNode {

    public AbstractNode inner;

    public CycleNode(AbstractNode node) {
        inner = node;
    }

    @Override
    public String toString() {
        if (inner != null) {
            return "Cycle(" + inner.toString() + ")";
        } else {
            return "Cycle";
        }
    }
}
