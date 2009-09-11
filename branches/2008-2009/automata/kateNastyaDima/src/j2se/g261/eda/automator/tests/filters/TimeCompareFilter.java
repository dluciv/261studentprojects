/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.tests.filters;

import j2se.g261.eda.automator.tests.ItemFilter;
import j2se.g261.eda.automator.tests.TestResultItem;
import j2se.g261.eda.automator.tests.filters.TimeResultTypeObject.TimeResultType;

/**
 *
 * @author nastya
 */
public class TimeCompareFilter implements ItemFilter {

    private TimeResultTypeObject firstArg;
    private TimeResultTypeObject secondArg;
    private CompareType type;

    public TimeCompareFilter(TimeResultTypeObject firstArg, CompareType type, TimeResultTypeObject secondArg) {
        this.firstArg = firstArg;
        this.secondArg = secondArg;
        this.type = type;
    }

    public boolean include(TestResultItem item) {
        long first = 0l;
        long second = 0l;

        TimeResultType t = firstArg.getType();
        switch (t) {
            case NFA:
                first = item.getNFA().getAverageTime();
                break;
            case DFA:
                first = item.getDFA().getAverageTime();
                break;
            case MIN_DFA:
                first = item.getMinGraph().getAverageTime();
                break;
            case TABLE:
                first = item.getTable().getAverageTime();
                break;

        }

        t = secondArg.getType();
        switch (t) {
            case NFA:
                second = item.getNFA().getAverageTime();
                break;
            case DFA:
                second = item.getDFA().getAverageTime();
                break;
            case MIN_DFA:
                second = item.getMinGraph().getAverageTime();
                break;
            case TABLE:
                second = item.getTable().getAverageTime();
                break;

        }


        switch (type) {
            case MORE:
                return first > second;
            case MORE_AND_EQUALS:
                return first >= second;
            case EQUALS:
                return first == second;
            case NOT_EQUALS:
                return first != second;
        }
        return false;
    }

    public enum CompareType {

        MORE, MORE_AND_EQUALS, EQUALS, NOT_EQUALS
    }
}



