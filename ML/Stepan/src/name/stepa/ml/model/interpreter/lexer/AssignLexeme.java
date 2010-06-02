package name.stepa.ml.model.interpreter.lexer;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class AssignLexeme extends Lexeme {
    public AssignLexeme(int start) {
        super(start, start+1);
    }

    @Override
    public String toString() {
        return "=";
    }
}
