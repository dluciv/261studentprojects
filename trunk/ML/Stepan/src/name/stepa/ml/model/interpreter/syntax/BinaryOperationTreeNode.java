package name.stepa.ml.model.interpreter.syntax;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class BinaryOperationTreeNode extends SyntaxTreeNode {
    public char operation;
    public SyntaxTreeNode left;
    public SyntaxTreeNode right;


    public BinaryOperationTreeNode(SyntaxTreeNode left, SyntaxTreeNode right, char operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "[" + left.toString() + " " + Character.toString(operation) + " " + right.toString() + "]";
    }
}
