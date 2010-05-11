package name.stepa.ml.model.interpreter.lexer;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class CloseBracketLexeme extends Lexeme {
    public CloseBracketLexeme(int start) {
        super(start, start + 1);
    }

    @Override
    public String toString() {
        return ")";
    }
}
