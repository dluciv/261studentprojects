package gui.tree;

import tree.Address;

/**
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 20:38:44
 */
public class FileAddress extends Address {
    private long value;

    public FileAddress(long value) {
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
