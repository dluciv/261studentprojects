//Lebedev Dmitry 2010 (c)
package ast;

import lebedev.Position;

public class LogGE extends BinaryOperation {

    public LogGE(Expression left, Expression right, Position position) {
        super(left, right, position);
    }
}
