package name.stepa.ml.model.interpreter.syntax;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class BinaryOperationTreeNode extends SyntaxTreeNode {
    public char operation;

    public BinaryOperationTreeNode(SyntaxTreeNode left, SyntaxTreeNode right, char operation) {
        super(left, right);
        this.operation = operation;
    }

    @Override
    public String toString() {
        return super.toString() + " " + Character.toString(operation);
    }
}
