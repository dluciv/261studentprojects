package ru.hotheart.interpreter.syntax;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class SyntaxTreeNode {

    public SyntaxTreeNode left;
    public SyntaxTreeNode right;

    public SyntaxTreeNode(SyntaxTreeNode left, SyntaxTreeNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        String res = "";
        if (left != null) {
            res += left.toString();
        }
        res += " ";
        if (right != null) {
            res += right.toString();
        }
        return res;
    }
}
