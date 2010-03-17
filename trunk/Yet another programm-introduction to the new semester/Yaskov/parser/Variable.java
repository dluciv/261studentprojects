/*
 * 
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package parser;

public class Variable extends BinOp {
    private int id;

    public Variable(BinOp leftOperand, BinOp rightOperand, int id) {
        super(leftOperand, rightOperand);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
