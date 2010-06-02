package name.stepa.ml.model.interpreter.lexer;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class BeginLexeme extends Lexeme {
    public BeginLexeme() {
        super(0, 0);
    }

    public BeginLexeme(int start) {
        super(start, start + 5);
    }

    @Override
    public String toString() {
        return "<begin>";
    }
}
