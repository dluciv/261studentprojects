package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.IdentifierLexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class VariableTreeNode extends SyntaxTreeNode {
    public String variable;

    public VariableTreeNode(String variable, IdentifierLexeme lexeme) {
        super(lexeme, lexeme);
        this.variable = variable;
    }

    @Override
    public String toString() {
        return variable;
    }
}
