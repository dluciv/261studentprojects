package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.keywords.LetLexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class AssignNode extends SyntaxTreeNode {
    public String variable;
    public SyntaxTreeNode assignExpression;

    public AssignNode(String variable, SyntaxTreeNode assignExpression, LetLexeme lexeme) {
        super(lexeme, assignExpression.end);
        this.assignExpression = assignExpression;
        this.variable = variable;
    }

    @Override
    public String toString() {
        return "[assign " + variable + " <- " + assignExpression.toString() + "]";
    }
}
