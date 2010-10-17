/*
 *  класс для булевых значений
 * Antonov Kirill(c), 2010
 */
package environment;

public class BoolValue extends Value {

    private boolean booleanVal;

    public BoolValue(boolean value) {
        booleanVal = value;
    }

    public boolean getValue() {
        return booleanVal;
    }
}
