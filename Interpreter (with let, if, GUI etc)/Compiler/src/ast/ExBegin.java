//Lebedev Dmitry 2010 (c)
package ast;

import java.util.LinkedList;

public class ExBegin extends Expression {

    private LinkedList<Expression> treeList;

    public ExBegin(LinkedList<Expression> treeList) {
        this.treeList = treeList;
    }

    public LinkedList<Expression> getList() {
        return treeList;
    }
}
