package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.Lexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
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
