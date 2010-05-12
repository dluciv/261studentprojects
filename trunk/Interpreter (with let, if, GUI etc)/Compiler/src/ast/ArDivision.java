//Lebedev Dmitry 2010 (c)
package ast;

public class ArDivision extends BinaryOperation {
    public ArDivision(Expression left, Expression right) {
        super(left, right);
    }

//    @Override
//    public int evaluate() {
//        return this.getLeft().evaluate() / this.getRight().evaluate();
//    }
//
//    @Override
//    public void print() {
//        getLeft().print();
//        getRight().print();
//        System.out.print("/ ");
//
//    }
}
