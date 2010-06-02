package name.stepa.ml.model.interpreter.lexer.keywords;

import name.stepa.ml.model.interpreter.lexer.Lexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class FunLexeme extends Lexeme {
    public FunLexeme(int start) {
        super(start, start + 3);
    }

    @Override
    public String toString() {
        return "<fun>";
    }
}
