/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AST;

public class LogOperand extends Expression {
    private Boolean value;

    public LogOperand(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }
}
