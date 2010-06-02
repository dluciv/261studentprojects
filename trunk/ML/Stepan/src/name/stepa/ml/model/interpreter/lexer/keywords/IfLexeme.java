package name.stepa.ml.model.interpreter.lexer.keywords;

import name.stepa.ml.model.interpreter.lexer.Lexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class IfLexeme extends Lexeme {
    public IfLexeme(int start) {
        super(start, start + 2);
    }

    @Override
    public String toString() {
        return "<if>";
    }
}
