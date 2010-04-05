//Lebedev Dmitry 2010 (c)
package ast;

public class Addition extends BinaryOperation {

    public Addition(Tree left, Tree right) {
        super(left, right);
    }

    @Override
    public int evaluate() {
        return this.getLeft().evaluate() + this.getRight().evaluate();
    }

    @Override
    public void print() {
        System.out.print("+ ");
        getLeft().print();
        getRight().print();
        
    }
}
