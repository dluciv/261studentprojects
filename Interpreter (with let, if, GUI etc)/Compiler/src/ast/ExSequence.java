// Lebedev Dmitry 2010 (c)

package ast;

public class ExSequence extends Expression {
    private Expression left, right;

    public ExSequence(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }
}
