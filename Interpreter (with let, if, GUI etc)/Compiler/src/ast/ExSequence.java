// Lebedev Dmitry 2010 (c)
package ast;

import java.util.LinkedList;

import lebedev.Position;

public class ExSequence extends Expression {

    private LinkedList<Expression> treeList;

    public ExSequence(LinkedList<Expression> treeList, Position position) {
        this.treeList = treeList;
        setPositon(position);
    }

    public LinkedList<Expression> getList() {
        return treeList;
    }
}
