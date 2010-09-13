/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */
public class LetIn implements Expression {

    private Expression condition;
    private Expression expression;

    public LetIn(Expression condition, Expression expression) {
        this.condition = condition;
        this.expression = expression;
    }

    public Expression getCondition() {
        return condition;
    }

    public Expression getExpression() {
        return expression;
    }
}
