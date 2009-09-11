/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.tests.filters;

import j2se.g261.eda.automator.tests.ItemFilter;
import j2se.g261.eda.automator.tests.TestResultItem;

/**
 *
 * @author nastya
 */
public class ResultMatchingFilter implements ItemFilter {

    private boolean match;

    public ResultMatchingFilter(boolean match) {
        this.match = match;
    }

    public boolean include(TestResultItem item) {
        return !(match ^
                ((item.isMatches() == item.getDFA().isMatches()) &&
                (item.isMatches() == item.getMinGraph().isMatches()) &&
                (item.isMatches() == item.getNFA().isMatches()) &&
                (item.isMatches() == item.getTable().isMatches())));
    }
}
