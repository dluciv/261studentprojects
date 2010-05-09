//Lebedev Dmitry 2010 (c)
package AST;

public class LogBoolean extends UnaryOperation {

    private boolean value;

    public LogBoolean(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
