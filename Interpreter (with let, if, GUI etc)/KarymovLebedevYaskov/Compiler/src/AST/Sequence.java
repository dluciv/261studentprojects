//Lebedev Dmitry 2010 (c)
package AST;

import java.util.LinkedList;

public class Sequence implements Tree {

    private LinkedList<Tree> TreeList;
    private int position = 0;

    public Sequence(LinkedList<Tree> TreeList) {
        super();
        this.TreeList = TreeList;
    }

    public void setPosition(int i) {
        position = i;
    }

    public int getPosition() {
        return position;
    }

    public void print() {
        for (Tree tree : TreeList) {
            tree.print();
            System.out.print("    ");
        }
    }

    public int evaluate() {
        return TreeList.getLast().evaluate();
    }
}
