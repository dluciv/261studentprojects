package name.stepa.ml.model.interpreter.syntax;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 16:54:57
 * To change this template use File | Settings | File Templates.
 */
public class ExpressionCallTreeNode extends SyntaxTreeNode {
    public SyntaxTreeNode expression;
    public SyntaxTreeNode argument;

    public ExpressionCallTreeNode(SyntaxTreeNode expression, SyntaxTreeNode argument) {
        this.expression = expression;
        this.argument = argument;
    }

    @Override
    public String toString() {
        return "[call " + expression + " (" + argument + ") ]";
    }
}
