package tree;

import java.io.Serializable;

/**
 * Представляет собой ключ, пригодный для использования в B+дереве.
 * Ключ должен иметь ссылку на некоторый элемент дерева, иметь правила сравнения
 * и метод для создания "такого же" ключа, только с пустой ссылкой (это необходимо для генерации
 * ключей более верхних кровней)
 *
 * <b>ВАЖНО</b>
 * <ul><li>должен быть переопределен метод equals(Object o)</li>
 * <li>неплохо, если определен метод hashCode()</li></ul>
 *
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 16:11:51
 * @see tree.BPlusTree
 * @see tree.TreeElement
 * @see tree.TreeNode
 * @see tree.TreeLeaf
 */

public abstract class Key implements Comparable, Cloneable, Serializable {
    //ссылка на элемент дерева
    protected TreeElement link;
    protected TreeNode<? extends Key, ? extends UsableData> container;

    protected Key(TreeElement link) {
        this.link = link;
    }

    public TreeElement getLink() {
        return link;
    }

    public void setLink(TreeElement link) {
        this.link = link;
    }

    /**
     * Создает новый объект, идентичный текущему
     * @return идентичный текущему ключ
     */
    public abstract Key clone();

    public TreeNode<? extends Key, ? extends UsableData> getContainer() {
        return container;
    }

    public void setContainer(TreeNode<? extends Key, ? extends UsableData> container) {
        this.container = container;
    }
}
