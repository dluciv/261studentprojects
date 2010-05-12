package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.keywords.InLexeme;

/**
 * Created by IntelliJ IDEA.
 * User: m08ksa
 * Date: 11.05.2010
 * Time: 12:28:42
 * To change this template use File | Settings | File Templates.
 */
public class InTreeNode extends SyntaxTreeNode {
    public AssignNode assignment;
    public SyntaxTreeNode expression;

    public InTreeNode(AssignNode assignment, SyntaxTreeNode expression) {
        super(assignment.start, expression.end);
        this.assignment = assignment;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "[ " + assignment.toString() + " <in> " + expression.toString() + " ]";
    }
}
