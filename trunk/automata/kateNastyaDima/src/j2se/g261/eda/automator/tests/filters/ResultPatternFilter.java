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
public class ResultPatternFilter implements ItemFilter{

    String pattern;

    public ResultPatternFilter(String pattern) {
        this.pattern = pattern;
    }
    
        
    public boolean include(TestResultItem item) {
        return item.getPattern().equals(pattern);
    }

}
