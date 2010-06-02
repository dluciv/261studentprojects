package name.stepa.ml.model.interpreter.lexer;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class SemicolonLexeme extends Lexeme {
    public SemicolonLexeme(int pos) {
        super(pos, pos + 1);
    }

    @Override
    public String toString() {
        return "<;>";
    }
}
