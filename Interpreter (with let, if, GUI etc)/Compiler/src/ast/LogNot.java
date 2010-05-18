//Lebedev Dmitry 2010 (c)
package ast;

import lebedev.Position;

public class LogNot extends UnaryOperation {
    public LogNot(Expression logOperand, Position position) {
        super(logOperand, position);
        
    }
}
