package name.stepa.ml.model.interpreter.lexer;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 15:07:26
 * To change this template use File | Settings | File Templates.
 */
public class BeginLexeme extends Lexeme {
    public BeginLexeme() {
        super(0, 0);
    }

    public BeginLexeme(int start) {
        super(start, start + 5);
    }

    @Override
    public String toString() {
        return "<begin>";
    }
}
