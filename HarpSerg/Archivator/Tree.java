package archiver;

/**
 *
 * @author HarpSerg
 */
class Tree {

    public Tree lchild;
    public Tree rchild;
    public boolean leaf;
    public int character;
    public int weight;

    public Tree() {

    }

    public boolean isLeaf() {
        return this.leaf;
    }
}