package name.stepa.ml.model.interpreter.lexer;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 15:43:59
 * To change this template use File | Settings | File Templates.
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
