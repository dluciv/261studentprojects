/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package parser;

public class Keyword extends Statement {
    public Keyword(int varId, BinOp expression) {
        super(varId, expression);
    }
}
