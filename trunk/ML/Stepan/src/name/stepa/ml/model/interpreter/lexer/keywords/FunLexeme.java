package name.stepa.ml.model.interpreter.lexer.keywords;

import name.stepa.ml.model.interpreter.lexer.Lexeme;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 16:38:33
 * To change this template use File | Settings | File Templates.
 */
public class FunLexeme extends Lexeme {
    public FunLexeme(int start) {
        super(start, start + 3);
    }

    @Override
    public String toString() {
        return "<fun>";
    }
}
