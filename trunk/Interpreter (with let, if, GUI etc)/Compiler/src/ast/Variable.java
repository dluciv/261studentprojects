/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lebedev.Position;

public class Variable extends Expression {

    private int id;
    private Position position;

    public Variable(int id, Position position) {
        this.id = id;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
