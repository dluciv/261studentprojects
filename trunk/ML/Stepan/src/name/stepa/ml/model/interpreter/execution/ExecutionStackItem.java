package name.stepa.ml.model.interpreter.execution;

import name.stepa.ml.model.interpreter.syntax.SyntaxTreeNode;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class ExecutionStackItem {

    public ExecutionStackItem(SyntaxTreeNode parent) {
        this.parent = parent;
    }

    public SyntaxTreeNode parent;
}
