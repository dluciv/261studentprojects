/*
 * class IntValue - class for Integer values. IntValue extends class Value.
 *
 * (c) Vokova Ekaterina
 */
package ide.value;

public class IntValue extends Value {

    private int value;

    public IntValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
