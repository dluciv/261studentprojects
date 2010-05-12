package name.stepa.ml.model.interpreter.lexer.keywords;

import name.stepa.ml.model.interpreter.lexer.Lexeme;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 15:43:17
 * To change this template use File | Settings | File Templates.
 */
public class IfLexeme extends Lexeme {
    public IfLexeme(int start) {
        super(start, start + 2);
    }

    @Override
    public String toString() {
        return "<if>";
    }
}
