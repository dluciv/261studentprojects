package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.CloseBracketLexeme;
import name.stepa.ml.model.interpreter.lexer.Lexeme;
import name.stepa.ml.model.interpreter.lexer.OpenBracketLexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
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
