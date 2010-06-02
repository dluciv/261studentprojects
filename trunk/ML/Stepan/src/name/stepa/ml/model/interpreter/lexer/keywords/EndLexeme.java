package name.stepa.ml.model.interpreter.lexer.keywords;

import name.stepa.ml.model.interpreter.lexer.Lexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class EndLexeme extends Lexeme {
    public EndLexeme() {
        super(0, 0);
    }

    public EndLexeme(int start) {
        super(start, start + 3);
    }

    @Override
    public String toString() {
        return "<end>";
    }
}
