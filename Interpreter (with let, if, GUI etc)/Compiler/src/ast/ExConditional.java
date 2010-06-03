/*
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package ast;

import lexerandparser.Position;

public class ExConditional extends Expression { // if "logExpression" then "thenPart" else "elsePart"
    private Expression logExpression;
    private Expression thenPart;
    private Expression elsePart;

    public ExConditional(Expression logExpression, Expression thenPart, Expression elsePart, Position position) {
        this.logExpression = logExpression;
        this.thenPart = thenPart;
        this.elsePart = elsePart;
        setPositon(position);
    }

    public Expression getLogExpression() {
        return logExpression;
    }

    public Expression getThenExpression() {
        return thenPart;
    }

    public Expression getElseExpression() {
        return elsePart;
    }
}
