/*
 * общий класс для типов int, bool, unit..
 * Antonov Kirill(c), 2010
 */
package AST;

public class Type extends Expression {

    private Types Left;
    private Types Right;

    public Type(Types left_node, Types right_node) {
        this.Left = left_node;
        this.Right = right_node;
    }

    public Type(Types type) {
        this.Left = type;
    }

    public Types LeftNode() {
        return Left;
    }

    public Types RightNode() {
        return Right;
    }
}
