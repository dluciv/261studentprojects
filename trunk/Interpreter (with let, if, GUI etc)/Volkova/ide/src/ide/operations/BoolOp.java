/*
 *
 *
 * (c) Volkova Ekatetina
 */
package ide.operations;

public class BoolOp implements Expression {

    boolean op = false;

    public BoolOp(boolean op) {
        this.op = op;
    }

    public boolean getBool() {
        return op;
    }
}
