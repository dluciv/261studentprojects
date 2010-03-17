/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package parser;

public class Num extends BinOp {
    private int number;

    public Num(BinOp leftOperand, BinOp rightOperand, int number) {
        super(leftOperand, rightOperand);
        this.number = number;
    }

    public int getValue() {
        return number;
    }
}
