package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.BeginLexeme;
import name.stepa.ml.model.interpreter.lexer.keywords.EndLexeme;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class ExpressionListTreeNode extends SyntaxTreeNode {
    public SyntaxTreeNode[] nodes;

    public ExpressionListTreeNode(SyntaxTreeNode[] nodes, BeginLexeme start, EndLexeme end) {
        super(start, end);
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        sb.append("[expressions start]");
        sb.append('\n');
        for (int i = 0; i < nodes.length; i++) {
            sb.append(nodes[i].toString());
            sb.append('\n');
        }
        sb.append("[expressions end]");
        sb.append('\n');
        return sb.toString();
    }
}
