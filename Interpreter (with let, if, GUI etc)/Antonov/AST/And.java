/*
 * && operation for bool expressions
 * Antonov Kirill(c), 2010
 */
package ast;

public class And extends BinaryOperation {

    public And(Expression leftNode, Expression rightNode) {
        super(leftNode, rightNode);
    }
}
