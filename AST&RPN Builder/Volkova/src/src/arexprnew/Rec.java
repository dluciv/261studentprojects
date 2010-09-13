/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arexprnew;

/**
 *
 * @author kate
 */
public class Rec {
    private Id id;
    private Expression expression;

    public Rec(Id id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    public Id getId() {
        return id;
    }

    public Expression getExpression() {
        return expression;
    }
}
