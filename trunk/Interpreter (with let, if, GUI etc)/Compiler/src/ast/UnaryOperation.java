/*
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package ast;

import lexerandparser.Position;

public abstract class UnaryOperation extends Expression {
    private Expression operand;

    public UnaryOperation(Expression operand, Position position) {
        this.operand = operand;
        setPositon(position);
    }

    public Expression getOperand() {
        return operand;
    }
}
