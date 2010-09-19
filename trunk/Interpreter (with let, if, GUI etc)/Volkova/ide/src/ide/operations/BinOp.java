/*
 *
 *
 * (c) Volkova Ekatetina
 */
package ide.operations;

public abstract class BinOp implements Expression {

    private Tree leftNode;
    private Tree rightNode;

    public BinOp(Tree leftNode, Tree rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public Tree getLeftNode() {
        return leftNode;
    }

    public Tree getRightNode() {
        return rightNode;
    }
}
