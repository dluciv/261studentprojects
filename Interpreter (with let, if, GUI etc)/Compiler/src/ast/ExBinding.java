/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

public class ExBinding extends Expression { // let "id" = "letExpression" in "inExpression"
    private int id;
    private Expression letExpression;
    private Expression inExpression;

    public ExBinding(int id, Expression letExpression, Expression inExpression) {
        this.id = id;
        this.letExpression = letExpression;
        this.inExpression = inExpression;
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
