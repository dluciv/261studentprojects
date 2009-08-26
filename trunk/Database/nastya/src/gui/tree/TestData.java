package gui.tree;

import tree.Keyable;
import tree.Key;
import tree.Address;

/**
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 20:44:32
 */
public class TestData implements Keyable<DatabaseKey, FileAddress> {
    private int a;
    private long b;

    public TestData(int a, long b) {
        this.a = a;
        this.b = b;
    }

    public DatabaseKey extractKey() {
        return new DatabaseKey(null, a);
    }

    public FileAddress extractAddress() {
        return new FileAddress(b);
    }
}
