package gui.tests;

import tree.UsableData;

/**
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 20:38:44
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
