package name.stepa.ml.model.interpreter.lexer;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class ArrowLexeme extends Lexeme {
    public ArrowLexeme(int start) {
        super(start, start + 2);
    }

    @Override
    public String toString() {
        return "[->]";
    }
}
