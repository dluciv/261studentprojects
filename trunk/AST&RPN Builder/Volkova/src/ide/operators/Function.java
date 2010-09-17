package ide.operators;

import ide.operations.Expression;

public class Function implements Expression {

    private Id argument;
    private Expression expression;

    public Function(Id argument, Expression expression) {
        this.argument = argument;
        this.expression = expression;
    }

    public Id getArgument() {
        return argument;
    }

    public Expression getExpression() {
        return expression;
    }
}
