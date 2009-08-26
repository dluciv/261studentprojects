package tree;

/**
 * @author nastya
 *         Date: 21.08.2009
 *         Time: 3:52:39
 */
public class BTree<D extends Keyable> {
    private TreeNode root;
    private int capacity;

    public BTree(int capacity) {
        this.capacity = capacity;
        root = new TreeNode(capacity);
    }

    public void add(D data){
        Key key = data.extractKey();
        Address address = data.extractAddress();
        root.add(key, address);
        if(root.getRoot() != null){
            //Это означает, что при добавлении элемента, головной пришлось расщепить
            // и теперь у дерева новый корень
            root = root.getRoot();
        }
    }

    @Override
    public String toString() {
        return "----------------\n" + "{BTree: \n" + root + "\n}" + "----------------\n";
    }

    public TreeNode getRoot() {
        return root;
    }
}
