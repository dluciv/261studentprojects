/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

import lexerandparser.Position;

public class ExPrint extends Expression {
    private Expression exToPrint;

    public ExPrint(Expression exToPrint, Position position) {
    	this.exToPrint = exToPrint;
    	setPositon(position);
    }

    public Expression getExToPrint(){
        return exToPrint;
    }
}
