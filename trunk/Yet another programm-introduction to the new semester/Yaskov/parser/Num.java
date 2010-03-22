/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package parser;

public class Num extends Expression {
    private int number;

    public Num(Expression leftOperand, Expression rightOperand, int number) {
        super(leftOperand, rightOperand);
        this.number = number;
    }

    public int getValue() {
        return number;
    }
}
