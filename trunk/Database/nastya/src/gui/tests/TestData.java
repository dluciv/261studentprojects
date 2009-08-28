package gui.tests;

import tree.IndexableData;

/**
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 20:44:32
 */
public class TestData implements IndexableData<TestKey, TestUsableData> {
    private int a;
    private long b;

    public TestData(int a, long b) {
        this.a = a;
        this.b = b;
    }

    public TestKey extractKey() {
        return new TestKey(null, a);
    }

    public TestUsableData extractUsableData() {
        return new TestUsableData(b);
    }
}
