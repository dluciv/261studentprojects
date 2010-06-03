/*
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package ast;

import lexerandparser.Position;

public class LogEquality extends BinaryOperation {
    public LogEquality(Expression left, Expression right, Position position) {
        super(left, right, position);
    }
}
