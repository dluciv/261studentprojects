package name.stepa.ml.model.interpreter.lexer;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 08.05.2010
 * Time: 22:42:51
 * To change this template use File | Settings | File Templates.
 */
public class ComparisonLexeme extends Lexeme {
    public static final int EQUALITY = 0;
    public static final int LE = 1;
    public static final int L = 2;
    public static final int GE = 3;
    public static final int G = 4;

    public int type;

    public ComparisonLexeme(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        switch (type) {
            case EQUALITY:
                return "==";
            case LE:
                return "<=";
            case L:
                return "<";
            case GE:
                return ">=";
            case G:
                return ">";
        }
        return "<error>";
    }
}
