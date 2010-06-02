//Lebedev Dmitry 2010 (c)
package ast;

import lexerandparser.Position;

public abstract class BinaryOperation extends Expression {
    private Expression left, right;

    public BinaryOperation(Expression left, Expression right, Position position) {
        this.left = left;
        this.right = right;
        setPositon(position);
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
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
