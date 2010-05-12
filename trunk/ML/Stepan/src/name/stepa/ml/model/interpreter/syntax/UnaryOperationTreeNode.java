package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.Lexeme;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 09.05.2010
 * Time: 15:45:58
 * To change this template use File | Settings | File Templates.
 */
public class UnaryOperationTreeNode extends SyntaxTreeNode {
    public static final int NOT = 1;
    public static final int MINUS = 2;
    public int operation;
    public SyntaxTreeNode argument;

    public UnaryOperationTreeNode(int operation, SyntaxTreeNode argument, Lexeme lexeme) {
        super(lexeme, argument.end);
        this.argument = argument;
        this.operation = operation;
    }

    @Override
    public String toString() {
        String res = null;
        switch (operation) {
            case NOT:
                res = "!";
                break;
            case MINUS:
                res = "-";
                break;
            default:
                res = "<error>";
                break;
        }
        return res + " " + argument.toString();
    }
}
