package name.stepa.ml.model.interpreter.lexer.keywords;

import name.stepa.ml.model.interpreter.lexer.Lexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class ThenLexeme extends Lexeme {
    public ThenLexeme(int start) {
        super(start, start + 4);
    }

    @Override
    public String toString() {
        return "<then>";
    }
}
