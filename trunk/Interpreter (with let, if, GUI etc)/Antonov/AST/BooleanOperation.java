/*
 * Bool Values
 * Antonov Kirill(c), 2010
 */
package ml.ast;

public class BooleanOperation extends Expression {

    boolean ifTrue = false;

    public BooleanOperation(boolean TRUE) {
        ifTrue = TRUE;
    }

    public boolean getBool() {
        return ifTrue;
    }
}
