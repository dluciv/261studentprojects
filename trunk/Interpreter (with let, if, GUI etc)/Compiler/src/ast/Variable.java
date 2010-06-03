/*
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package ast;

import lexerandparser.Position;

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
