/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interpreter;

import tools.Tool;

public abstract class Value {
    Object value;

    public Integer asInteger() {
        try {
            return (Integer) value;
        } catch (ClassCastException e) {
            Tool.fixError("cast error", Interpreter.getCurPos());
            return 0;
        }
    }

    public Boolean asBolean() {
        try {
            return (Boolean) value;
        } catch (ClassCastException e) {
            Tool.fixError("cast error", Interpreter.getCurPos());
            return true;
        }
    }

    public Object getValue() {
        return value;
    }
}
