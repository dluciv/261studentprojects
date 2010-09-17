package ide.operators;

import ide.operations.Expression;

public class StackFrame {

    private Expression listOfCalls;
    private Id argument;
    private Id nameFun;

    public StackFrame(Id nameFun, Expression listOfCalls, Id argument) {
        this.nameFun = nameFun;
        this.argument = argument;
        this.listOfCalls = listOfCalls;
    }

    public Id getNamefun() {
        return nameFun;
    }

    public Expression getListOfCalls() {
        return listOfCalls;
    }

    public Id getArgument() {
        return argument;
    }
}
