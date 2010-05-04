/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AST;

public abstract class UnaryOperation extends Tree{
    private Operand operand;

    public UnaryOperation(Operand operand) {
        this.operand = operand;
    }

    public Operand getOperand() {
        return operand;
    }
}
