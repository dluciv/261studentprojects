package name.stepa.ml.model.interpreter;

import name.stepa.ml.model.interpreter.syntax.SyntaxTreeNode;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 14:56:29
 * To change this template use File | Settings | File Templates.
 */
public class ExecutionStack {
    public class ExecutionStackItem
    {
        public SyntaxTreeNode parent;
    }
}
