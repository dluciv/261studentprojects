/*
 * Equality node
 * Antonov Kirill(c), 2010
 */
package ast;

public class Equality extends BinaryOperation {

    public Equality(Expression leftNode, Expression rightNode) {
        super(leftNode, rightNode);
    }
}
