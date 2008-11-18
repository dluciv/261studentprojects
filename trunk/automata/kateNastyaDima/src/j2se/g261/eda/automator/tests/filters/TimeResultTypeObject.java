/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.tests.filters;

/**
 *
 * @author nastya
 */
public class TimeResultTypeObject {

    private TimeResultType type;
    private String name;
    public final static TimeResultTypeObject RESULT_NFA =
            new TimeResultTypeObject(TimeResultType.NFA, "NFA");
    public final static TimeResultTypeObject RESULT_DFA =
            new TimeResultTypeObject(TimeResultType.DFA, "DFA");
    public final static TimeResultTypeObject RESULT_MIN_DFA =
            new TimeResultTypeObject(TimeResultType.MIN_DFA, "MDFA");
    public final static TimeResultTypeObject RESULT_TABLE = new TimeResultTypeObject(TimeResultType.TABLE, "TABLE");

    private TimeResultTypeObject(TimeResultType type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    TimeResultType getType() {
        return type;
    }

    enum TimeResultType {

        NFA, DFA, MIN_DFA, TABLE
    }
}

