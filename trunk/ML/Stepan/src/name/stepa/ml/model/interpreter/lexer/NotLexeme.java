package name.stepa.ml.model.interpreter.lexer;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class NotLexeme extends Lexeme {
    public NotLexeme(int start) {
        super(start, start + 1);
    }

    @Override
    public String toString() {
        return "<not>";
    }
}
