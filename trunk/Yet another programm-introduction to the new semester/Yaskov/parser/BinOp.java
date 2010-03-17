/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package parser;

public abstract class BinOp {
    private BinOp leftOperand, rightOperand;

    public BinOp(BinOp leftOperand, BinOp rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public BinOp getLeftOperand() {
        return leftOperand;
    }

    public BinOp getRightOperand() {
        return rightOperand;
    }
}
