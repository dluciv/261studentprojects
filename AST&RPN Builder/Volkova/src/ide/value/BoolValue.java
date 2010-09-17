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
