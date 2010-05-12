package name.stepa.ml.model.interpreter.lexer;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class Lexeme {
    public int start;
    public int end;

    public Lexeme(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
