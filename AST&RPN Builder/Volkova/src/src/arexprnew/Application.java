/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */
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
