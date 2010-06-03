/*
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package ast;

import lexerandparser.Position;

public class ExBinding extends Expression { // let "id" = "letExpression" in "inExpression"
    private int id;
    private Expression letExpression;
    private Expression inExpression;

    public ExBinding(int id, Expression letExpression, Expression inExpression, Position position) {
        this.id = id;
        this.letExpression = letExpression;
        this.inExpression = inExpression;
        setPositon(position);
    }

    public int getId() {
        return id;
    }

    public Expression getLetExpression() {
        return letExpression;
    }

    public Expression getInExpression() {
        return inExpression;
    }
}
