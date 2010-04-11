/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package parser;

public class Keyword extends Statement {
    public Keyword(AbstractTree leftOperand, AbstractTree rightOperand) {
        super(leftOperand, rightOperand);
    }
}
