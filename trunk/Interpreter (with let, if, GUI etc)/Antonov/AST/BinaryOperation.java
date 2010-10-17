/*
 *General class for tree nodes: /,*,-,+ extends this class
 * Antonov Kirill(c), 2010
 */
package ast;

public abstract class BinaryOperation extends Expression {

    public Expression Left;
    public Expression Right;

    public BinaryOperation(Expression left_node, Expression right_node) {
        this.Left = left_node;
        this.Right = right_node;
    }

    public Expression LeftNode() {
        return Left;
    }

    public Expression RightNode() {
        return Right;
    }
}
