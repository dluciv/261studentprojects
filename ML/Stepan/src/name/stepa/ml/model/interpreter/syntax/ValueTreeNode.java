package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.ValueLexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class ValueTreeNode extends SyntaxTreeNode {
    public double value;

    public ValueTreeNode(double value, ValueLexeme lexeme) {
        super(lexeme, lexeme);
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
