/*
 * CycleNode class for Regular Expression Library by Korshakov Stepan
 */
package hotheart.regexp.AST.node;

/**
 * @author Korshakov Stepan
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

    private String getCycleSymbol() {
        if (type == STAR) {
            return "*";
        } else if (type == PLUS) {
            return "+";
        } else if (type == QUESTION) {
            return "?";
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        String res = "Cycle" + getCycleSymbol();

        if (inner != null) {
            return res + "(" + inner.toString() + ")";
        } else {
            return res;
        }
    }

    @Override
    public String getGraphVizString() {
        String res = getGraphVizNodeString("Cycle" + getCycleSymbol());
        res += getChildVizNodeString(inner);
        return res;
    }
}
