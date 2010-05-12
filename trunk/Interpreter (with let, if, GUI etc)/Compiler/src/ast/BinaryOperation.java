//Lebedev Dmitry 2010 (c)
package ast;

public abstract class BinaryOperation extends Expression {
    private Tree left, right;

    public BinaryOperation(Tree left, Tree right) {
        this.left = left;
        this.right = right;
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
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
