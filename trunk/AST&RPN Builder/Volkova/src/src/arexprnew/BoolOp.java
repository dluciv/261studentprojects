/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */

//true or false
public class BoolOp implements Expression {

    boolean op = false;

    public BoolOp(boolean op) {
        this.op= op;
    }

    public boolean getBool() {
        return op;
    }
}
