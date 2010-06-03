/*
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package ast;

import lexerandparser.Position;

public class LogLess extends BinaryOperation {
    public LogLess(Expression left, Expression right, Position position) {
        super(left, right, position);
    }
}
