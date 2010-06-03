/*
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package ast;

import lexerandparser.Position;

public class LogAnd extends BinaryOperation {
    public LogAnd(Expression left, Expression right, Position position) {
        super(left, right, position);
    }
}
