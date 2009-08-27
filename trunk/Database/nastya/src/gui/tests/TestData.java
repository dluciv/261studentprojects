package gui.tests;

import tree.Keyable;

/**
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 20:44:32
 */
public class TestData implements Keyable<TestKey, TestAddress> {
    private int a;
    private long b;

    public TestData(int a, long b) {
        this.a = a;
        this.b = b;
    }

    public TestKey extractKey() {
        return new TestKey(null, a);
    }

    public TestAddress extractAddress() {
        return new TestAddress(b);
    }
}
