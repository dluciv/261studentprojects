package name.stepa.ml.model.interpreter;

import name.stepa.ml.model.interpreter.syntax.SyntaxTreeNode;
import name.stepa.ml.model.interpreter.values.ExecutionStateValue;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 14:56:29
 * To change this template use File | Settings | File Templates.
 */
public class ExecutionStack extends Stack<ExecutionStack.ExecutionStackItem> {
    public static class ExecutionStackItem {

        public ExecutionStackItem(SyntaxTreeNode parent, ExecutionStateValue state, Context context) {
            this.parent = parent;
            this.state = state;
            this.context = context;
        }

        public SyntaxTreeNode parent;
        public ExecutionStateValue state;
        public Context context;

        public ArrayList calculatedExpressions = new ArrayList();
    }
}