/*
 * 
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package parser;

public class Assignment extends Statement {
    public Assignment(int varId, BinOp expression) {
        super(varId, expression);
    }
}
