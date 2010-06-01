//Lebedev Dmitry 2010 (c)
package ast;

import lexerandparser.Position;

public class LogLE extends BinaryOperation {

    public LogLE(Expression left, Expression right, Position position) {
        super(left, right, position);
    }
}
