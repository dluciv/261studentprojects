package ide.operators;

import ide.operations.Expression;

public class StackFrame {

    private Expression listOfCalls;
    private int addres;
    private Id nameFun;

    public StackFrame(Id nameFun, Expression listOfCalls, int addres) {
        this.nameFun = nameFun;
        this.addres = addres;
        this.listOfCalls = listOfCalls;
    }

    public Id getNamefun() {
        return nameFun;
    }

    public Expression getListOfCalls() {
        return listOfCalls;
    }

    public Integer getAddres() {
        return addres;
    }
}
