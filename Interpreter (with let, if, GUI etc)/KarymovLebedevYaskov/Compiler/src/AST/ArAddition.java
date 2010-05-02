//Lebedev Dmitry 2010 (c)
package AST;

public class ArAddition extends BinaryOperation {
    public ArAddition(Tree left, Tree right) {
        super(left, right);
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
