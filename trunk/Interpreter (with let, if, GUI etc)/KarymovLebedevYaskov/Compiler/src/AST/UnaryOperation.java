/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AST;

public abstract class UnaryOperation extends Tree{
    private Tree operand;

    public UnaryOperation(Tree operand) {
        this.operand = operand;
    }

    public Tree getOperand() {
        return operand;
    }
}
