package name.stepa.ml.model.interpreter.execution;

import name.stepa.ml.model.interpreter.Context;
import name.stepa.ml.model.interpreter.syntax.SyntaxTreeNode;
import name.stepa.ml.model.interpreter.values.ExecutionStateValue;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 13.05.2010
 * Time: 0:39:45
 * To change this template use File | Settings | File Templates.
 */
public class ExecutionStackItem {

    public ExecutionStackItem(SyntaxTreeNode parent, Context context) {
        this.parent = parent;
        this.context = context;
    }

    public SyntaxTreeNode parent;
    public Context context;
}
