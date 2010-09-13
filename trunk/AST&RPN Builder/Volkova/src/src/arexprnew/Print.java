/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */
public class Print implements Expression {

    private Expression expression;

    public Print(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
