// Lebedev Dmitry 2010 (c)
package ast;

import java.util.LinkedList;

public class ExSequence extends Expression {

    private LinkedList<Expression> treeList;

    public ExSequence(LinkedList<Expression> treeList) {
        this.treeList = treeList;
    }

    public LinkedList<Expression> getList() {
        return treeList;
    }
}
