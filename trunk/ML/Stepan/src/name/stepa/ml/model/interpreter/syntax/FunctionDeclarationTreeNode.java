package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.keywords.FunLexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class FunctionDeclarationTreeNode extends SyntaxTreeNode {
    public SyntaxTreeNode expression;
    public String argumentName;

    public FunctionDeclarationTreeNode(SyntaxTreeNode expression, String argumentName, FunLexeme lexeme) {
        super(lexeme, expression.end);
        this.expression = expression;
        this.argumentName = argumentName;
    }

    @Override
    public String toString() {
        return "[fun " + argumentName + " -> " + expression.toString() + " ]";
    }
}
