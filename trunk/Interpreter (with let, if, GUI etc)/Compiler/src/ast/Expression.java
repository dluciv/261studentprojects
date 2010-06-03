//Lebedev Dmitry 2010 (c)
package ast;

import lexerandparser.Position;

public class Expression {

    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPositon(Position position) {
        this.position = position;
    }
}
