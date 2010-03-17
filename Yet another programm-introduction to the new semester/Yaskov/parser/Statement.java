/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package parser;

public abstract class Statement {
    private int varId;
    private BinOp expression;

    public Statement(int varId, BinOp expression) {
        this.varId = varId;
        this.expression = expression;
    }

    public int getVarId() {
        return varId;
    }

    public BinOp getExpression() {
        return expression;
    }
}
