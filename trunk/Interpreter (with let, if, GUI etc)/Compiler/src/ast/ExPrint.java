/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

public class ExPrint extends Expression {
    private Expression exToPrint;

    public ExPrint(Expression exToPrint) {
        this.exToPrint = exToPrint;
    }

    public Expression getExToPrint(){
        return exToPrint;
    }
}
