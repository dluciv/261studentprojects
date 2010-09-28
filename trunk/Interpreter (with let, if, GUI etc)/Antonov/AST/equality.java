/*
 * Equality node
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.ast;

public class Equality extends BinaryOperation {

    public Equality(Tree leftNode, Tree rightNode) {
        super(leftNode, rightNode);
    }
}
