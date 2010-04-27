//Lebedev Dmitry 2010 (c)
package AST;

public class BinaryOperation extends Expression {

    public BinaryOperation(Tree left, Tree right) {
        super(left, right);
    }

    @Override
    public int evaluate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void print() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
