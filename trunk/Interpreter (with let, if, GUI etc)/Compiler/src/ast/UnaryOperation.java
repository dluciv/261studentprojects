/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

public abstract class UnaryOperation extends Expression {
    private Tree operand;

    public UnaryOperation(Tree operand) {
        this.operand = operand;
    }

    public Tree getOperand() {
        return operand;
    }
}
