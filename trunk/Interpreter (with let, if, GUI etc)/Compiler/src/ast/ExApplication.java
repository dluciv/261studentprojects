/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

public class ExApplication extends Expression {
    private Expression function;
    private Expression argument;

    public ExApplication(Expression function, Expression argument) {
        this.function = function;
        this.argument = argument;
    }

    public Expression getFunction() {
        return function;
    }

    public Expression getArgument() {
        return argument;
    }
}
