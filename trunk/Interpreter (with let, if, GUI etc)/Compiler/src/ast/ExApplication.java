/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

import lebedev.Position;

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
