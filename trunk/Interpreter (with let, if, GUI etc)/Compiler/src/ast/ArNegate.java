/*
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package ast;

import lexerandparser.Position;

public class ArNegate extends UnaryOperation {
    public ArNegate(Expression arOperand, Position position) {
        super(arOperand, position);
    }
}
