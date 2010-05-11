package name.stepa.ml.model.interpreter.lexer;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class BinaryOperationLexeme extends Lexeme {
    public char operation;

    public BinaryOperationLexeme(char operation, int pos) {
        super(pos, pos + 1);
        this.operation = operation;
    }

    @Override
    public String toString() {
        return Character.toString(operation);
    }
}
