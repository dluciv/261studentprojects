/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package parser;

public abstract class Statement extends AbstractTree {
    public Statement(AbstractTree leftOperand, AbstractTree rightOperand) {
        super(leftOperand, rightOperand);
    }
    /*private int varId;
    private Expression expression;

    public Statement(int varId, Expression expression) {
        this.varId = varId;
        this.expression = expression;
    }

    public int getVarId() {
        return varId;
    }

    public Expression getExpression() {
        return expression;
    }*/

}
