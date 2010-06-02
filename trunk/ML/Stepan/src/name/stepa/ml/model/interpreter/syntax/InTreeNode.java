package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.keywords.InLexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
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
