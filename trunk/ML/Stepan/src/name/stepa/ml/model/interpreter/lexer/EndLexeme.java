package name.stepa.ml.model.interpreter.lexer;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 15:08:13
 * To change this template use File | Settings | File Templates.
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
