package name.stepa.ml.model.interpreter.lexer;

/**
 * Created by IntelliJ IDEA.
 * User: m08ksa
 * Date: 11.05.2010
 * Time: 12:20:23
 * To change this template use File | Settings | File Templates.
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
