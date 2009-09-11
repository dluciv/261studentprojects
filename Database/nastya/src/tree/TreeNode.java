package tree;

import utils.Util;

import java.util.Vector;
import java.util.Collections;

import dbentities.Condition;

/**
 * @author nastya
 *         Date: 21.08.2009                                    Usa
 *         Time: 3:56:42
 */
public class TreeNode<K extends Key, U extends UsableData> implements TreeElement {
    private Vector<K> list;
    private int capacity;
    private TreeNode<K, U> link;
    private TreeNode<K, U> linkFrom = null;

    protected TreeNode<K, U> root;


    public TreeNode<K, U> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<K, U> root) {
        this.root = root;
    }

    protected TreeNode(int capacity, TreeNode<K, U> root) {
        this(capacity, root, new Vector<K>());
        link = null;
    }

    protected TreeNode(int capacity) {
        this(capacity, null, new Vector<K>());
        link = null;
    }

    private TreeNode(int capacity, TreeNode<K, U> root, Vector<K> list) {
        this.root = root;
        this.capacity = capacity;
        this.list = list;
        link = null;
    }


    public void add(K key, U usableData) {
        if (key == null) return;
        if (haveChildrenNodes()) {
            //В дереве всегда есть list.size() + 1  дочерний блок,
            // причем ссылка от каждого ключа является таким блоком.
            // Это обусловлено строением дерева
            boolean found = false;
            for (int i = 0; i < list.size(); i++) {
                if (key.compareTo(list.get(i)) <= 0) {
                    found = true;
                    ((TreeNode) list.get(i).getLink()).add(key, usableData);
                    break;
                }
            }
            if (!found) {
                // Добавляем в "последний" блок, то есть блок, указывающий на
                //все элементы, больше последнего ключа
                //Этот способ работает только на тех уровнях, где есть
                //"дети", так как на самом нижнем уровне
                //необходима ссылка на соседний, на том же уровне, блок
                if (link == null) link = new TreeNode<K, U>(capacity);
                link.add(key, usableData);
            }
        } else {
            if (!list.contains(key)) {
                //Если такой ключ еще отсутствует, начинается "обратная"(вверх по дереву) операция вставки
                TreeLeaf<U> leaf = new TreeLeaf<U>();
                leaf.add(usableData);
                key.setLink(leaf);
                addKey(key);
            } else {
                //Просто в список данных, соответствующих ключу добавляем,
                // уже существующему в списке, добавляем те данные, которые
                // соответствуют добавляемому ключу
                ((TreeLeaf) list.get(list.indexOf(key)).getLink()).add(usableData);

            }

        }
    }

    private void addKey(K key) {

        // Добавляем элемент(с сортировкой)
        list.add(key);
        Collections.sort(list);
        key.setContainer(this);
        if (list.size() > capacity) {
            //Разбиваем блок на два (на самом деле, просто отсоединяем первую часть в отдельный список)
            Vector<K> newList = Util.firstHalf(list);
            Util.cutFirstHalf(list);

            //Если нам необходимо создать новый корень (то есть мы добрались до вершины и вершину надо расщепить)
            boolean rootWasNull = false;
            if (root == null) {
                root = new TreeNode<K, U>(capacity, null);
                // это понадобится позже. флаг, указывающий на то, что произошло образование нового корня
                rootWasNull = true;
            }

            //Оба новых списка отсортированы. Берем последний индекс "нового" списка. На самом деле, тут подвох.
            // если посмотреть в вики, берется нижний индекс второй части. но картинка обратная
            //TODO что за фигня
            //Итак, создаем новый ключ и "цепляем" к нему новый список
            K newKey = (K) newList.lastElement().clone();
            TreeNode<K, U> newNode = new TreeNode<K, U>(capacity, root, newList);
            newKey.setLink(newNode);
            // Связано с тем, что у нас не только есть "дети", но и "родители". Для всех
            // элементов дочерних во вновь сгенерированном блоке элементов необходимо сделать "родителем"
            //этот самый новый блок, иначе родителем останется "старый" блок (this) и будут
            // ошибки
            for(int i = 0; i < newList.size(); i++){
                newList.get(i).setContainer(newNode);
                if(newList.get(i).getLink() instanceof TreeNode){
                    ((TreeNode)newList.get(i).getLink()).setRoot(newNode);
                }
            }

            if (!haveChildrenNodes()) {
                if(linkFrom != null){
                    linkFrom.setLink(newNode);
                    newNode.setLinkFrom(linkFrom);
                }
                newNode.setLink(this);
                linkFrom = newNode;
            }

            // Добавляем к корневому элементу новый ключ
            root.addKey(newKey);
            // Не забываем, что если образовался новый корень, то надо "старый" список, то есть обчиканный
            // текущий элемент надо добавить в новый корень как "список бОльших элементов"
            if (rootWasNull) {
                root.setLink(this);
           }

        }
    }


    public boolean haveChildrenNodes() {
        for (K key : list) {
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

    public void setLink(TreeNode<K, U> link) {
        this.link = link;
    }


    public int keyCount() {
        return list.size();
    }

    public K getKey(int i) {
        return list.get(i);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public TreeElement getLink() {
        return link;
    }

    public void setLinkFrom(TreeNode<K, U> linkFrom) {
        this.linkFrom = linkFrom;
    }



    public K firstKey(){
        return (list == null || list.isEmpty()) ? null : list.firstElement();
    }

    public int indexOfKey(K o) {
        return list.indexOf(o);
    }

    public K getSuitableKey(K keyToFind) {
        if(keyToFind == null) return null;
        for (K key : list) {
            if(keyToFind.compareTo(key) < 1){
                return key;
            }
        }
        return null;
    }
}
