/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AST;

public class LogOperand extends Operand {
    private boolean value;

    public LogOperand(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
