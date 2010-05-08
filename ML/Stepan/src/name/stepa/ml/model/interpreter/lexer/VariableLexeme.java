package name.stepa.ml.model.interpreter.lexer;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class VariableLexeme extends Lexeme {
    public String value;

    public VariableLexeme(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
