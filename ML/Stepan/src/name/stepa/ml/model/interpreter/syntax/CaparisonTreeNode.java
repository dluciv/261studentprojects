package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.ComparisonLexeme;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 09.05.2010
 * Time: 0:25:05
 * To change this template use File | Settings | File Templates.
 */
public class CaparisonTreeNode extends SyntaxTreeNode {

    public int operation;

    public CaparisonTreeNode(int operation, SyntaxTreeNode left, SyntaxTreeNode right) {
        super(left, right);
        this.operation = operation;
    }

    @Override
    public String toString() {
        switch (operation) {
            case ComparisonLexeme.EQUALITY:
                return "==";
            case ComparisonLexeme.LE:
                return "<=";
            case ComparisonLexeme.L:
                return "<";
            case ComparisonLexeme.GE:
                return ">=";
            case ComparisonLexeme.G:
                return ">";
        }
        return "<error>";
    }
}
