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

    public static final int STAR = 1;
    public static final int PLUS = 2;
    public static final int QUESTION = 3;
    public AbstractNode inner;
    public int type;

    public CycleNode(AbstractNode inner, int tp) {
        this.inner = inner;
        type = tp;
    }

    @Override
    public String toString() {
        String res = "Cycle";

        if (type == STAR) {
            res += "*";
        } else if (type == PLUS) {
            res += "+";
        } else if (type == QUESTION) {
            res += "?";
        }

        if (inner != null) {
            return res + "(" + inner.toString() + ")";
        } else {
            return res;
        }
    }
}
