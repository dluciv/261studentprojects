package name.stepa.ml.model.interpreter.lexer;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 16:39:53
 * To change this template use File | Settings | File Templates.
 */
public class ArrowLexeme extends Lexeme {
    public ArrowLexeme(int start) {
        super(start, start + 2);
    }

    @Override
    public String toString() {
        return "[->]";
    }
}
