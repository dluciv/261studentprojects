//Lebedev Dmitry 2010 (c)
package ast;

public class Division extends BinaryOperation {

    public Division(Tree left, Tree right) {
        super(left, right);
    }

    @Override
    public int evaluate() {
        return this.getLeft().evaluate() / this.getRight().evaluate();
    }

    @Override
    public void print() {
        getLeft().print();
        getRight().print();
        System.out.print("/ ");
                
    }
}
