package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.ComparisonLexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class CaparisonTreeNode extends SyntaxTreeNode {

    public int operation;
    public SyntaxTreeNode left;
    public SyntaxTreeNode right;


    public CaparisonTreeNode(int operation, SyntaxTreeNode left, SyntaxTreeNode right) {
        super(left.start, right.end);
        this.right = right;
        this.left = left;
        this.operation = operation;
    }

    @Override
    public String toString() {

        String res = "[" + left.toString() + " ";
        switch (operation) {
            case ComparisonLexeme.E:
                res += "==";
                break;
            case ComparisonLexeme.LE:
                res += "<=";
                break;
            case ComparisonLexeme.L:
                res += "<";
                break;
            case ComparisonLexeme.GE:
                res += ">=";
                break;
            case ComparisonLexeme.G:
                res += ">";
                break;
            default:
                res += "<error>";
        }
        res += " " + right.toString() + " ]";
        return res;
    }
}
