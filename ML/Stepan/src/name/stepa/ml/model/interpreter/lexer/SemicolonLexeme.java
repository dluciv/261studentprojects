package name.stepa.ml.model.interpreter.lexer;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 14:42:54
 * To change this template use File | Settings | File Templates.
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
