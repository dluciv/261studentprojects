/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

public class ExFunction extends Expression {
    private int id;
    private Expression functionBody;

    public ExFunction(int id, Expression functionBody) {
        this.id = id;
        this.functionBody = functionBody;
    }

    public int getId() {
        return id;
    }

    public Expression getFunctionBody() {
        return functionBody;
    }
}
