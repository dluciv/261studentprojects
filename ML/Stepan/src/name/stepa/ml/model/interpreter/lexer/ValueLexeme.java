package name.stepa.ml.model.interpreter.lexer;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class ValueLexeme extends Lexeme {
    public double value;

    public ValueLexeme(double value, int start, int end) {
        super(start, end);
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
