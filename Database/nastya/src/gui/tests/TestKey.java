package gui.tests;

import tree.Key;
import tree.TreeElement;

/**
 * Ключ для тестовых данных, которые можно занести в B+дерево, реализованное в пакете
 * <code>tree</code>.  Используется для демонстрации работы дерева в соответствующей вкладке
 *
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 20:39:23
 * @see tree.IndexableData
 * @see tree.Key
 * @see tree.UsableData
 * @see tree.BPlusTree
 */
public class TestKey extends Key {
    private int value;

    public TestKey(TreeElement link, int value) {
        super(link);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Key clone() {
        return new TestKey(null, value);
    }

    public int compareTo(Object o) {
        if(o == null) return 1;
        if(o.getClass() != getClass()) return 1;
        return Integer.valueOf(value).compareTo(Integer.valueOf(((TestKey)o).value));
    }

    @Override
    public String toString() {
        return "{" + value + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestKey that = (TestKey) o;

        if (value != that.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
