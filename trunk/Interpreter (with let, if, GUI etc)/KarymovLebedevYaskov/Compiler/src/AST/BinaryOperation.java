//Lebedev Dmitry 2010 (c)
package AST;

public abstract class BinaryOperation extends Tree {
    private Operand left, right;

    public BinaryOperation(Operand left, Operand right) {
        this.left = left;
        this.right = right;
    }

    public Operand getLeft() {
        return left;
    }

    public Operand getRight() {
        return right;
    }

//    @Override
//    public int evaluate() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void print() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
}
