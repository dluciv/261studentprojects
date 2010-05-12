/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

public class ExConditional extends Expression { // if "logExpression" then "thenPart" else "elsePart"
    private Expression logExpression;
    private Expression thenPart;
    private Expression elsePart;

    public ExConditional(Expression logExpression, Expression thenPart, Expression elsePart) {
        this.logExpression = logExpression;
        this.thenPart = thenPart;
        this.elsePart = elsePart;
    }

    public Expression getLogExpression() {
        return logExpression;
    }

    public Expression getThenExpression() {
        return thenPart;
    }

    public Expression getElsePart() {
        return elsePart;
    }
}
