/*
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package interpreter;

import tools.Tool;

public abstract class Value {
    Object value;

    public Integer asInteger() {
        try {
            return (Integer) value;
        } catch (ClassCastException e) {
            Tool.fixError("cast to integer error", Interpreter.getCurPos());
            return 0;
        }
    }

    public Boolean asBolean() {
        try {
            return (Boolean) value;
        } catch (ClassCastException e) {
            Tool.fixError("cast to boolean error", Interpreter.getCurPos());
            return true;
        }
    }

    public Object getValue() {
        return value;
    }
}
