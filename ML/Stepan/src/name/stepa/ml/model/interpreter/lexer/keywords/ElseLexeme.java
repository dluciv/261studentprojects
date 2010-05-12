package name.stepa.ml.model.interpreter.lexer.keywords;

import name.stepa.ml.model.interpreter.lexer.Lexeme;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 15:44:34
 * To change this template use File | Settings | File Templates.
 */
public class ElseLexeme extends Lexeme {
    public ElseLexeme(int start) {
        super(start, start + 4);
    }

    @Override
    public String toString() {
        return "<else>";
    }
}
