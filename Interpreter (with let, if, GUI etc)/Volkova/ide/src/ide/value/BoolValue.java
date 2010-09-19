/*
 * class BoolValue - class for boolean values. BoolValue extends class Value.
 *
 * (c) Vokova Ekaterina
 */
package ide.value;

public class BoolValue extends Value {

    private boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    public boolean getBoolValue() {
        return value;
    }
}
