/*
 *
 *
 * (c) Volkova Ekatetina
 */
package ide.operations;

public class Not implements Expression {

    private Expression expression = null;

    public Not(Expression expression) {
        this.expression = expression;
    }
}
