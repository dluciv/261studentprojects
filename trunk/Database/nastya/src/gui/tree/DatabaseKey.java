package gui.tree;

import tree.Key;
import tree.TreeElement;

/**
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 20:39:23
 */
public class DatabaseKey extends Key {
    private int value;

    public DatabaseKey(TreeElement link, int value) {
        super(link);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Key clone() {
        return new DatabaseKey(null, value);
    }

    public int compareTo(Object o) {
        if(o == null) return 1;
        if(o.getClass() != getClass()) return 1;
        return Integer.valueOf(value).compareTo(Integer.valueOf(((DatabaseKey)o).value));
    }

    @Override
    public String toString() {
        return "{" + value + "}";
    }
}
