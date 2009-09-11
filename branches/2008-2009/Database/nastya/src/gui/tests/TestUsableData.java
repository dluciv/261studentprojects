package gui.tests;

import tree.UsableData;

/**
 * "Содержимое" для тестовых данных, которые можно занести в B+дерево, реализованное в пакете
 * <code>tree</code>.  Используется для демонстрации работы дерева в соответствующей вкладке
 *
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 20:38:44
 * @see tree.IndexableData
 * @see tree.Key
 * @see tree.UsableData
 * @see tree.BPlusTree
 */
public class TestUsableData implements UsableData {
    private long value;

    public TestUsableData(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + "l";
    }
}
