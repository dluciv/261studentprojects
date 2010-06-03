/*
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package ast;

import lexerandparser.Position;

public class LogInequality extends BinaryOperation {
    public LogInequality(Expression left, Expression right, Position position) {
        super(left, right, position);
    }
}
