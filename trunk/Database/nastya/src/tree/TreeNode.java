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
    private TreeNode linkFrom = null;

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
            for (int i = 0; i < list.size(); i++) {
                if (key.compareTo(list.get(i)) <= 0) {
                    found = true;
                    ((TreeNode) list.get(i).getLink()).add(key, address);
                    break;
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
                //Если такой ключ еще отсутствует, начинается "обратная"(вверх по дереву) операция вставки
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
            //Разбиваем блок на два (на самом деле, просто отсоединяем первую часть в отдельный список)
            Vector<Key> newList = Util.firstHalf(list);
            Util.cutFirstHalf(list);

            //Если нам необходимо создать новый корень (то есть мы добрались до вершины и вершину надо расщепить)
            boolean rootWasNull = false;
            if (root == null) {
                root = new TreeNode(capacity, null);
                // это понадобится позже. флаг, указывающий на то, что произошло образование нового корня
                rootWasNull = true;
            }

            //Оба новых списка отсортированы. Берем последний индекс "нового" списка. На самом деле, тут подвох.
            // если посмотреть в вики, берется нижний индекс второй части. но картинка обратная
            //TODO что за фигня
            //Итак, создаем новый ключ и "цепляем" к нему новый список
            Key newKey = newList.lastElement().clone();
            TreeNode newNode = new TreeNode(capacity, root, newList);
            newKey.setLink(newNode);
            // Связано с тем, что у нас не только есть "дети", но и "родители". Для всех
            // элементов дочерних во вновь сгенерированном блоке элементов необходимо сделать "родителем"
            //этот самый новый блок, иначе родителем останется "старый" блок (this) и будут
            // ошибки
            for(int i = 0; i < newList.size(); i++){
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

    public Key get(int i) {
        return list.get(i);
    }

    public TreeElement getLink() {
        return link;
    }

    public void setLinkFrom(TreeNode linkFrom) {
        this.linkFrom = linkFrom;
    }
}
