/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

import lebedev.Position;

public abstract class UnaryOperation extends Expression {
    private Tree operand;

    public UnaryOperation(Tree operand, Position position) {
        this.operand = operand;
        setPositon(position);
    }

    public Tree getOperand() {
        return operand;
    }
}
