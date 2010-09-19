/*
 *
 *
 * (c) Volkova Ekatetina
 */
package ide.operators;

import ide.operations.Expression;

public class Application implements Expression {

    private Expression function;
    private Expression arg;

    public Application(Expression function, Expression arg) {
        this.function = function;
        this.arg = arg;
    }

    public Expression getFunction() {
        return function;
    }

    public Expression getArg() {
        return arg;
    }
}
