/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arexprnew;

/**
 *
 * @author kate
 */
public class FunctionRec implements Expression{

    private Id id;
    private Expression expression;

    public FunctionRec(Id id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    public Id getId() {
        return id;
    }
}
