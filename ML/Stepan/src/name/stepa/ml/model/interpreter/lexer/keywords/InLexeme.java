package name.stepa.ml.model.interpreter.lexer.keywords;

import name.stepa.ml.model.interpreter.lexer.Lexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class InLexeme extends Lexeme {
    public InLexeme(int pos) {
        super(pos, pos + 2);
    }

    @Override
    public String toString() {
        return "<in>";
    }
}
