// Lebedev Dmitry 2010 (c)
// not yet
package ast;

import java.util.LinkedList;

public class ExSequence extends Expression {
    private LinkedList<Expression> treeList;
//    private int position = 0;
//
    public ExSequence(LinkedList<Expression> treeList) {
        this.treeList = treeList;
    }

    public LinkedList<Expression> getList() {
        return treeList;
    }
//
//    public void setPosition(int i) {
//        position = i;
//    }
//
//    public int getPosition() {
//        return position;
//    }
//
//    public void print() {
//        for (Tree tree : TreeList) {
//            tree.print();
//            System.out.print("    ");
//        }
//    }
//
//    public int evaluate() {
//        return TreeList.getLast().evaluate();
//    }
}
