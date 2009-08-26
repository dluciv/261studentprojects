package tree;

import utils.Util;

import java.util.Vector;
import java.util.Collections;

/**
 * @author nastya
 *         Date: 21.08.2009
 *         Time: 3:56:42
 */
public class TreeNode extends TreeElement {
    private Vector<Key> list;
    private int capacity;
    private TreeNode link;

    protected TreeNode root;


    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    protected TreeNode(int capacity, TreeNode root) {
        this(capacity, root, new Vector<Key>());
        link = null;
    }

    protected TreeNode(int capacity) {
        this(capacity, null, new Vector<Key>());
        link = null;
    }

    private TreeNode(int capacity, TreeNode root, Vector<Key> list) {
        this.root = root;
        this.capacity = capacity;
        this.list = list;
        link = null;
    }


    public void add(Key key, Address address) {
        if (key == null) return;
        if (haveChildrenNodes()) {
            //В дереве всегда есть list.size() + 1  дочерний блок,
            // причем ссылка от каждого ключа является таким блоком.
            // Это обусловлено строением дерева
            boolean found = false;
            for (Key listKey : list) {
                if (key.compareTo(listKey) <= 0) {
                    found = true;
                    ((TreeNode) listKey.getLink()).add(key, address);
                }
            }
            if (!found) {
                // Добавляем в "последний" блок, то есть блок, указывающий на
                //все элементы, больше последнего ключа
                //Этот способ работает только на тех уровнях, где есть
                //"дети", так как на самом нижнем уровне
                //необходима ссылка на соседний, на том же уровне, блок
                if (link == null) link = new TreeNode(capacity);
                link.add(key, address);
            }
        } else {
            if (!list.contains(key)) {
                //Если такой ключ еще отсутствует
                TreeLeaf leaf = new TreeLeaf();
                leaf.add(address);
                key.setLink(leaf);
                addKey(key);
            } else {
                //Просто в список данных, соответствующих ключу добавляем,
                // уже существующему в списке, добавляем те данные, которые
                // соответствуют добавляемому ключу
                ((TreeLeaf) list.get(list.indexOf(key)).getLink()).add(address);

            }

        }
    }

    private void addKey(Key key) {
        // Добавляем элемент(с сортировкой)
        list.add(key);
        Collections.sort(list);
        if (list.size() > capacity) {
            //Разбиваем блок на два (на самом деле, просто отсоединяем вторую часть)
            Vector<Key> part2 = Util.tail(list);
            Util.cutTail(list);
            //Они сортированы. Берем последний индекс "остатка"
            if(root == null){
                root =new TreeNode(capacity, null);
            }
            TreeNode rest = new TreeNode(capacity, root, part2);
            root.setLink(rest);
            rest.setLink(link);
            link = null;

            Key newKey = list.lastElement().clone();
            newKey.setLink(this);
            root.addKey(newKey);
//            root =
//            Key newKey = list.lastElement().clone();
//            newKey.setLink(this);
////                System.out.println("newKey = " + newKey);
//            //Берем "родительский элемент" и присоединяем к нему вновь получившийся
//            // блок.
//            System.out.println("part2 = " + part2);
//            if (root == null) {
//                Vector<Key> keys = new Vector<Key>();
//                keys.add(newKey);
//
//                TreeNode newRoot = new TreeNode(capacity, null, keys);
//                TreeNode newNode = new TreeNode(capacity, newRoot, part2);
//                newNode.setLink(link);
//                link = null;
//                newRoot.setLink(newNode);
//                setRoot(newRoot);
//            } else {
//
//                TreeNode newNode = new TreeNode(capacity, root, part2);
//                newNode.setLink(link);
//                link = null;
//                root.setLink(newNode);
//                root.addKey(newKey, address);
//
//
//            }

        }
    }



    private boolean haveChildrenNodes() {
        for (Key key : list) {
            if (key.getLink() != null && key.getLink() instanceof TreeNode) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return list + " (" + root + ")";
    }

    public void setLink(TreeNode link) {
        this.link = link;
    }


    public int listSize() {
        return list.size();
    }

    public Key get(int i){
        return list.get(i);
    }

    public TreeElement getLink() {
        return link;
    }
}
