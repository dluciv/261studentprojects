//Lebedev Dmitry 2010 (c)
package ast;

import lexerandparser.Position;

public class ArAddition extends BinaryOperation {
    public ArAddition(Expression left, Expression right, Position position) {
        super(left, right, position);
    }

//    @Override
//    public int evaluate() {
//        return this.getLeft().evaluate() + this.getRight().evaluate();
//    }
//
//    @Override
//    public void print() {
//        getLeft().print();
//        getRight().print();
//        System.out.print("+ ");
//
//    }
}
