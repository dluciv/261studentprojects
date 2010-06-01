//Lebedev Dmitry 2010 (c)
package ast;

import lexerandparser.Position;

public class LogNot extends UnaryOperation {
    public LogNot(Expression logOperand, Position position) {
        super(logOperand, position);
        
    }
}
