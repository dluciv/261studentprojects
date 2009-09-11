/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.tests.filters;

import j2se.g261.eda.automator.representations.dfa.DFA;
import j2se.g261.eda.automator.representations.nfa.NFA;
import j2se.g261.eda.automator.tests.ItemFilter;
import j2se.g261.eda.automator.tests.Result;
import j2se.g261.eda.automator.tests.TestResultItem;
import j2se.g261.eda.automator.tests.filters.TimeResultTypeObject.TimeResultType;

/**
 *
 * @author nastya
 */
public class DevationFilter implements ItemFilter {

    float deviation;
    TimeResultTypeObject type;
    CompareType compareType;
    ResultType compareWith;

    public DevationFilter(float deviation, TimeResultTypeObject type, CompareType compareType, ResultType compareWith) {
        this.deviation = deviation;
        this.type = type;
        this.compareType = compareType;
        this.compareWith = compareWith;
    }

    private boolean compare(float a, float b, CompareType type) {
        switch (type) {
            case EQUALS:
                return a == b;
            case LESS:
                return a < b;
            case MORE:
                return a > b;
        }
        return false;
    }

    private boolean compareResults(Result res, ResultType resultType, CompareType compareType) {
        switch (resultType) {
            case MIN:
                return compare(res.getAverageTime() / res.getMinTime() - 1f, deviation, compareType);
            case MAX:
                return compare(res.getMaxTime() / res.getAverageTime() - 1f, deviation, compareType);
            case BOTH:
                return compare(res.getMaxTime() / res.getAverageTime() - 1f, deviation, compareType) && compare(res.getAverageTime() / res.getMinTime() - 1f, deviation, compareType);
            case SOME:
                System.out.println("---------------");
                System.out.println(res.getAverageTime() / res.getMinTime() - 1f);
                System.out.println(res.getMaxTime() / res.getAverageTime() - 1f);
                System.out.println("!--------------");
                return compare(res.getMaxTime() / res.getAverageTime() - 1f, deviation, compareType) || compare(res.getAverageTime() / res.getMinTime() - 1f, deviation, compareType);
        }
        return false;
    }

    public boolean include(TestResultItem item) {
        TimeResultType t = type.getType();

        switch (t) {
            case NFA:
                return compareResults(item.getNFA(), compareWith, compareType);
            case DFA:
                return compareResults(item.getDFA(), compareWith, compareType);
            case MIN_DFA:
                return compareResults(item.getMinGraph(), compareWith, compareType);
            case TABLE:
                return compareResults(item.getTable(), compareWith, compareType);
            case ALL:
                return compareResults(item.getTable(), compareWith, compareType) 
                        && compareResults(item.getNFA(), compareWith, compareType) 
                        && compareResults(item.getDFA(), compareWith, compareType) 
                        && compareResults(item.getMinGraph(), compareWith, compareType);
            case SOME:
                return compareResults(item.getTable(), compareWith, compareType) 
                        || compareResults(item.getNFA(), compareWith, compareType) 
                        || compareResults(item.getDFA(), compareWith, compareType) 
                        || compareResults(item.getMinGraph(), compareWith, compareType);
            default:
                return false;
        }
    }

    public enum CompareType {

        MORE, EQUALS, LESS
    }

    public enum ResultType {

        MIN, MAX, BOTH, SOME
    }
}
