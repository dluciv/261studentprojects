package name.stepa.ml.model.interpreter.syntax;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class ExpressionCallTreeNode extends SyntaxTreeNode {
    public SyntaxTreeNode expression;
    public SyntaxTreeNode argument;

    public ExpressionCallTreeNode(SyntaxTreeNode expression, SyntaxTreeNode argument) {
        super(expression.start, argument.end);
        this.expression = expression;
        this.argument = argument;
    }

    @Override
    public String toString() {
        return "[call " + expression + " (" + argument + ") ]";
    }
}
