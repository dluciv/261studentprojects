//Lebedev Dmitry 2010 (c)
package AST;

public class ArDivision extends BinaryOperation {
    public ArDivision(ArOperand left, ArOperand right) {
        super(left, right);
    }

    public ArDivision(Tree left, Tree right) {
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
