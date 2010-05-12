package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.keywords.LetLexeme;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 08.05.2010
 * Time: 22:59:01
 * To change this template use File | Settings | File Templates.
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
