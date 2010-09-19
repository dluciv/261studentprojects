/*
 * class StackFrame - representation one cell of stack.
 *
 * (c) Vokova Ekaterina
 */
package ide.operators;

import ide.operations.Expression;

public class StackFrame {

    private Expression argument;
    private Id nameFun;

    public StackFrame(Id nameFun, Expression argument) {
        this.nameFun = nameFun;
        this.argument = argument;
    }

    public Id getNamefun() {
        return nameFun;
    }

    public Expression getArgument() {
        return argument;
    }
}
