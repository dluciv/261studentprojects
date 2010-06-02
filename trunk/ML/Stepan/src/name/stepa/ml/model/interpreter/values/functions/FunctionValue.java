package name.stepa.ml.model.interpreter.values.functions;

import name.stepa.ml.model.interpreter.Context;
import name.stepa.ml.model.interpreter.InterpretationCore;
import name.stepa.ml.model.interpreter.syntax.SyntaxTreeNode;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class FunctionValue {

    public Context context;
    public SyntaxTreeNode expression;
    public InterpretationCore core;
    public String argumentName;

    public FunctionValue(Context context, SyntaxTreeNode expression, String argumentName, InterpretationCore core) {
        this.context = context;
        this.expression = expression;
        this.argumentName = argumentName;
        this.core = core;
    }

    @Override
    public String toString() {
        return "[fun " + argumentName + " -> " + expression.toString() + " ]";
    }
}