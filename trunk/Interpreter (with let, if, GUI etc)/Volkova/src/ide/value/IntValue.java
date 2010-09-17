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
