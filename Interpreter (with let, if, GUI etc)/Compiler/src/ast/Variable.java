/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

public class Variable extends Expression {
    private int id;

    public Variable(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
