package name.stepa.ml.model.interpreter.lexer;

/**
 * Created by IntelliJ IDEA.
 * User: m08ksa
 * Date: 11.05.2010
 * Time: 12:07:31
 * To change this template use File | Settings | File Templates.
 */
public class LetLexeme extends Lexeme {
    public LetLexeme(int start) {
        super(start, start + 3);
    }

    @Override
    public String toString() {
        return "<let>";
    }
}
