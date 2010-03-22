/*
 * 
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package parser;

public class Variable extends Expression {
    private int id;

    public Variable(Expression leftOperand, Expression rightOperand, int id) {
        super(leftOperand, rightOperand);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
