package name.stepa.ml.model.interpreter.values.functions;

import name.stepa.ml.model.interpreter.Context;
import name.stepa.ml.model.interpreter.InterpretationCore;
import name.stepa.ml.model.interpreter.syntax.SyntaxTreeNode;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 16:08:13
 * To change this template use File | Settings | File Templates.
 */
public class FunctionValue extends AbstractFunctionValue {

    Context context;
    SyntaxTreeNode expression;
    InterpretationCore core;
    String argumentName;

    public FunctionValue(Context context, SyntaxTreeNode expression, String argumentName, InterpretationCore core) {
        this.context = context;
        this.expression = expression;
        this.argumentName = argumentName;
        this.core = core;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        context.put(argumentName, arg);
        core.getExecutionItem().context = context;
        Object res = core.interpret(expression);
        return res;
    }

    @Override
    public String toString() {
        return "[fun " + argumentName + " -> " + expression.toString() + " ]";
    }
}