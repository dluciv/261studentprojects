package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.keywords.IfLexeme;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 15:51:37
 * To change this template use File | Settings | File Templates.
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
