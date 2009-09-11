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
public class ValueFilter implements ItemFilter {

    private long min;
    private long max;
    TimeResultTypeObject type;

    public ValueFilter(long min, long max, TimeResultTypeObject type) {
        this.min = min;
        System.out.println(min);
        this.max = max;
        System.out.println(max);
        this.type = type;
        System.out.println(type);
    }

    public boolean include(TestResultItem item) {
        TimeResultType t = type.getType();

        switch (t) {
            case NFA:
                return (min <= item.getNFA().getAverageTime()) && (max >= item.getNFA().getAverageTime());
            case DFA:
                return (min <= item.getDFA().getAverageTime()) && (max >= item.getDFA().getAverageTime());
            case MIN_DFA:
                return (min <= item.getMinGraph().getAverageTime()) && (max >= item.getMinGraph().getAverageTime());
            case TABLE:
                return (min <= item.getTable().getAverageTime()) && (max >= item.getTable().getAverageTime());
            case ALL:
                return ((min <= item.getNFA().getAverageTime()) && (max >= item.getNFA().getAverageTime()) 
                        && (min <= item.getDFA().getAverageTime()) && (max >= item.getDFA().getAverageTime()) 
                        && (min <= item.getMinGraph().getAverageTime()) && (max >= item.getMinGraph().getAverageTime()) 
                        && (min <= item.getTable().getAverageTime()) && (max >= item.getTable().getAverageTime()));
            case SOME:
                return ((min <= item.getNFA().getAverageTime()) && (max >= item.getNFA().getAverageTime()) 
                        || (min <= item.getNFA().getAverageTime()) && (max >= item.getDFA().getAverageTime()) 
                        || (min <= item.getMinGraph().getAverageTime()) && (max >= item.getMinGraph().getAverageTime()) 
                        || (min <= item.getTable().getAverageTime()) && (max >= item.getTable().getAverageTime()));
            default:
                return false;
        }
    }
}
