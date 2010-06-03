/*
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package ast;

import lexerandparser.Position;

public class LogOr extends BinaryOperation {
    public LogOr(Expression left, Expression right, Position position) {
        super(left, right, position);
    }
}
