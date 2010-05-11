package name.stepa.ml.model.interpreter.lexer;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 09.05.2010
 * Time: 15:39:57
 * To change this template use File | Settings | File Templates.
 */
public class NotLexeme extends Lexeme {
    public NotLexeme(int start) {
        super(start, start + 1);
    }

    @Override
    public String toString() {
        return "<not>";
    }
}
