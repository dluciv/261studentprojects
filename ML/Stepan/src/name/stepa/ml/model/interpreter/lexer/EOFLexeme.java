package name.stepa.ml.model.interpreter.lexer;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class EOFLexeme extends Lexeme{
    public EOFLexeme() {
        super(0, 0);
    }

    @Override
    public String toString() {
        return "#";
    }
}
