package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.CloseBracketLexeme;
import name.stepa.ml.model.interpreter.lexer.Lexeme;
import name.stepa.ml.model.interpreter.lexer.OpenBracketLexeme;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 20:03:52
 * To change this template use File | Settings | File Templates.
 */
public class BracketsTreeNode extends SyntaxTreeNode {
    public SyntaxTreeNode inner;

    public BracketsTreeNode(SyntaxTreeNode inner, OpenBracketLexeme start, CloseBracketLexeme end) {
        super(start, end);
        this.inner = inner;
    }

    @Override
    public String toString() {
        return "( " + inner.toString() + " )";
    }
}
