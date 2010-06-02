package name.stepa.ml.model.interpreter.lexer;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class ComparisonLexeme extends Lexeme {
    public static final int E = 0;
    public static final int NE = 1;
    public static final int LE = 2;
    public static final int L = 3;
    public static final int GE = 4;
    public static final int G = 5;

    public int type;

    public ComparisonLexeme(int type, int pos, int end) {
        super(pos, end);
        this.type = type;
    }

    @Override
    public String toString() {
        switch (type) {
            case E:
                return "==";
            case LE:
                return "<=";
            case L:
                return "<";
            case GE:
                return ">=";
            case G:
                return ">";
            case NE:
                return "!=";
        }
        return "<error>";
    }
}
