/*
 *
 *
 * (c) Volkova Ekatetina
 */
package ide.operators;

import ide.operations.Expression;

public class Binding implements Expression {

    private Id id;
    private Expression expression;
    private Expression value;

    public Binding(Id id, Expression expression, Expression value) {
        this.id = id;
        this.expression = expression;
        this.value = value;
    }

    public Id getId() {
        return id;
    }

    public Expression getExpression() {
        return expression;
    }

    public Expression getValue() {
        return value;
    }
}
