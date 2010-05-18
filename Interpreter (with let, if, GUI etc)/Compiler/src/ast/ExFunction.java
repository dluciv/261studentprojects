/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

import lebedev.Position;

public class ExFunction extends Expression {
    private int id;
    private Expression functionBody;

    public ExFunction(int id, Expression functionBody, Position position) {
        this.id = id;
        this.functionBody = functionBody;
        setPositon(position);
    }

    public int getId() {
        return id;
    }

    public Expression getFunctionBody() {
        return functionBody;
    }
}
