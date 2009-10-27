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

    public Tree(int vcharacter, int vweight) {
        character = vcharacter;
        leaf = true;
        weight = vweight;
    }

    Tree(int weight, Tree left, Tree right) {

    }

    public void increaseWeight(int val) {
        weight += val;
    }
    
    public void setLchild(Tree chld) {
        lchild = chld;
    }

    public void setRchild(Tree chld) {
        rchild = chld;
    }

    public boolean isLeaf() {
        return this.leaf;
    }
}