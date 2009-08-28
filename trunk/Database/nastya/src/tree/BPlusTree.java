package tree;

import java.io.Serializable;

/**
 * Представляет собой B+ дерево. Реализовано на основании <a href = http://en.wikipedia.org/wiki/B%2Btree>B+Tree</a>
 *
 * @author nastya
 *         Date: 21.08.2009
 *         Time: 3:52:39
 * @see tree.IndexableData
 *
 *
 */
public class BPlusTree<D extends IndexableData> implements Serializable{
    private TreeNode root;
    private int capacity;

    /**
     * Создает дерево с определенной "вместимостью" узлов
     * @param capacity максимальное количество ключей в одном "блоке"
     */
    public BPlusTree(int capacity) {
        this.capacity = capacity;
        //Создаем "голову" дерева
        root = new TreeNode(capacity);
    }

    /**
     * Добавляет в дерево некоторые данные, создавая для них ключ. Информация, добавляемая в дерево, определяется
     * самим типом данных
     *
     * @param data объект, данные о котором необходимо добавить в дерево
     */
    public void add(D data){
        Key key = data.extractKey();
        UsableData usableData = data.extractUsableData();
        root.add(key, usableData);
        if(root.getRoot() != null){
            //Это означает, что при добавлении элемента, головной пришлось расщепить
            // и теперь у дерева новый корень
            root = root.getRoot();
        }
    }

    @Override
    public String toString() {
        return "----------------\n" + "{BPlusTree: \n" + root + "\n}" + "----------------\n";
    }

    public TreeNode getRoot() {
        return root;
    }
}
