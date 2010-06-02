package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.keywords.IfLexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class IfTreeNode extends SyntaxTreeNode {
    public SyntaxTreeNode ifExpr, thenExpr, elseExpr;

    public IfTreeNode(SyntaxTreeNode ifExpr, SyntaxTreeNode thenExpr, SyntaxTreeNode elseExpr, IfLexeme lexeme) {
        super(lexeme, elseExpr.end);
        this.ifExpr = ifExpr;
        this.thenExpr = thenExpr;
        this.elseExpr = elseExpr;
    }

    @Override
    public String toString() {
        return "[if " + ifExpr.toString() + " then " + thenExpr.toString() + " else " + elseExpr.toString() + " ]";
    }
}
