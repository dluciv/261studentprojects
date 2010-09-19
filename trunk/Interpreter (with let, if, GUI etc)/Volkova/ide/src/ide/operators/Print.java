/*
 *
 *
 * (c) Volkova Ekatetina
 */
package ide.operators;

import ide.operations.Expression;

public class Print implements Expression {

    private Expression expression;

    public Print(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
