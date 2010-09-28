/*
 * Bool Values
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.ast;

public class BooleanOp extends Expression {

    boolean ifTrue = false;

    public BooleanOp(boolean TRUE) {
        ifTrue = TRUE;
    }

    public boolean getBool() {
        return ifTrue;
    }
}
