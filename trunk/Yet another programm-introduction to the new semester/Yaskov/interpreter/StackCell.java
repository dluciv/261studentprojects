/*
 * 
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package interpreter;

public class StackCell {
    private int value;

    public StackCell(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
