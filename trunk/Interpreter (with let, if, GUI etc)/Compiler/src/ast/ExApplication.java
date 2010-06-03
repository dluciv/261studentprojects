/*
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package ast;

import lexerandparser.Position;

public class ExApplication extends Expression {
    private Expression function;
    private Expression argument;

    public ExApplication(Expression function, Expression argument, Position position) {
        this.function = function;
        this.argument = argument;
        setPositon(position);
    }

    public Expression getFunction() {
        return function;
    }

    public Expression getArgument() {
        return argument;
    }
}
