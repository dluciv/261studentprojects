//Lebedev Dmitry 2010 (c)
package ast;

import lebedev.Position;

public class Expression extends Tree {

    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPositon(Position position) {
        this.position = position;
    }
//    private Tree left;
//    private Tree right;
//    Expression(Tree left, Tree right) {
//        this.LeftNode = left;
//        this.RightNode = right;
//    }
//
//    public Tree getLeft() {
//        return LeftNode;
//    }
//
//    public Tree getRight() {
//        return RightNode;
//    }
//
//    public void print() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public int evaluate() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
}
