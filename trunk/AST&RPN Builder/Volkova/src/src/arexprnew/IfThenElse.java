/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */
public class IfThenElse implements Expression {

    private Expression condition;
    private Expression expression1;
    private Expression expression2;

    public IfThenElse(Expression condition, Expression expression1, Expression expression2) {
        this.condition = condition;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    public Expression getCondition() {
        return condition;
    }

    public Expression getExpression1() {
        return expression1;
    }

    public Expression getExpression2() {
        return expression2;
    }
}
