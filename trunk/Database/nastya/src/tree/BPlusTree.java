package tree;

import dbentities.Condition;
import dbentities.Card;

import java.io.*;

import database.index.DatabaseKey;
import database.index.AddressData;

/**
 * Представляет собой B+ дерево. Реализовано на основании <a href = http://en.wikipedia.org/wiki/B%2Btree>B+Tree</a>
 *
 * @author nastya
 *         Date: 21.08.2009
 *         Time: 3:52:39
 * @see tree.IndexableData
 */
public class BPlusTree<D extends IndexableData<K, U>, K extends Key, U extends UsableData> implements Serializable {
    private TreeNode<K, U> root;
    private int capacity;

    /**
     * Создает дерево с определенной "вместимостью" узлов
     *
     * @param capacity максимальное количество ключей в одном "блоке"
     */
    public BPlusTree(int capacity) {
        this.capacity = capacity;
        //Создаем "голову" дерева
        root = new TreeNode<K, U>(capacity);
    }

    /**
     * Добавляет в дерево некоторые данные, создавая для них ключ. Информация, добавляемая в дерево, определяется
     * самим типом данных
     *
     * @param data объект, данные о котором необходимо добавить в дерево
     */
    public void add(D data) {
        K key = data.extractKey();
        U usableData = data.extractUsableData();
        root.add(key, usableData);
        if (root.getRoot() != null) {
            //Это означает, что при добавлении элемента, головной пришлось расщепить
            // и теперь у дерева новый корень
            root = root.getRoot();
        }
    }

    @Override
    public String toString() {
        return "----------------\n" + "{BPlusTree: \n" + root + "\n}" + "----------------\n";
    }

    public TreeNode<K, U> getRoot() {
        return root;
    }

    /**
     * Предоставляет способ взять "следующий" в дереве ключ. Это означает, что мы берем
     * ключ, находящийся в дереве правее данного, но не обязаетльно в текущем контейнере
     * (листе).
     * <b>ВАЖНО</b> этот метод имеет смысл только для работы с листьями (то есть, в данном случае,
     * самым нижнем уровнем узлов)
     *
     * @param current ключ, следующий после которого необходимо найти
     * @return ключ, следующий за текущим в дереве. Если в данном узле ключей не осталось,
     *         то берутся ключи из того узла, на который ссылается этот как на следующий. Если данный
     *         ключ является последним в дереве, то возвращает <code>null</code>
     */
    public K keyAfter(K current) {
        //TODO тут облом с образцами
        TreeNode<K, U> container = (TreeNode<K, U>) current.getContainer();
        if (current == null) return null;
        int index = container.indexOfKey(current);
        if (index != -1 && index + 1 < container.keyCount()) {
            return container.getKey(index + 1);
        } else if (index + 1 >= container.keyCount()) {
            if (container.getLink() == null) {
                return null;
            } else if (container.getLink() instanceof TreeNode) {
                return ((TreeNode<K, U>) container.getLink()).firstKey();
            }
        }
        return null;
    }

    public K find(K startCondition) {
          return find(root, startCondition);
    }

    private K find(TreeNode<K, U> node, K startCondition) {
        if(node == null) return null;
        if(node.haveChildrenNodes()){
             K suitableKey = node.getSuitableKey(startCondition);
            if(suitableKey == null){
                return find((TreeNode<K, U>)node.getLink(), startCondition);
            }else{
                return find((TreeNode<K, U>)suitableKey.getLink(), startCondition);
            }
        }else{
            return node.getSuitableKey(startCondition);
        }
    }

    public static <D extends IndexableData<K, U>, K extends Key, U extends UsableData> BPlusTree<D, K, U> deserialize(File choosedFile) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(choosedFile));
        BPlusTree<D, K, U> tree = (BPlusTree<D, K, U>) in.readObject();
        in.close();
        return tree;

    }
}
