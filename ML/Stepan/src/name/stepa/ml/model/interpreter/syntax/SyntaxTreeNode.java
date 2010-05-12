package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.Lexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class SyntaxTreeNode {
    public Lexeme start, end;

    public SyntaxTreeNode(Lexeme start, Lexeme end) {
        this.start = start;
        this.end = end;
    }
}
