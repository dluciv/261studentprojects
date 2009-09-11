package gui.tests;

import tree.IndexableData;

/**
 * Простейший пример данных, которые можно занести в B+дерево, реализованное в пакете
 * <code>tree</code>.  Используется для демонстрации работы дерева в соответствующей вкладке
 *
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 20:44:32
 * @see tree.IndexableData
 * @see tree.Key
 * @see tree.UsableData
 * @see tree.BPlusTree
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
