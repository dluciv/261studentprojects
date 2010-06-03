/*
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package ast;

import lexerandparser.Position;

public class LogGreater extends BinaryOperation {
    public LogGreater(Expression left, Expression right, Position position) {
        super(left, right, position);
    }
}
