/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.tests;

import java.util.Vector;

/**
 *
 * @author nastya
 */
public class TestResultItemStorage {

    private Vector<TestResultItem> storage;
    public static final String MATCHES = "1";
    public static final String NOT_MATCHES = "0";
    private ItemFilter filter = null;
    private Vector<TestResultItem> filtered;

    public TestResultItemStorage() {
        storage = new Vector<TestResultItem>();
        filtered = new Vector<TestResultItem>();
    }

    public void addTestResult(String pattern, String string, boolean matches) {
        TestResultItem item = new TestResultItem(pattern, string, matches);
        storage.add(item);
        if (filter != null && filter.include(item)) {
            filtered.add(item);
        }

    }

    public TestResultItem getTestResult(int index) {
        return filter == null ? storage.get(index) : filtered.get(index);
    }

    public String getPattern(int index) {
        return filter == null ? storage.get(index).getPattern() : filtered.get(index).getPattern();
    }

    public String getString(int index) {
        return filter == null ? storage.get(index).getString() : filtered.get(index).getString();
    }

    public String isMatches(int index) {
        return filter == null ? (storage.get(index).isMatches() ? MATCHES : NOT_MATCHES)
                : (filtered.get(index).isMatches() ? MATCHES : NOT_MATCHES);
    }

    public void removeTest(int index) {
        if (filter == null) {
            storage.removeElementAt(index);
        } else {
            filtered.removeElementAt(index);
        }
    }

    public int size() {
        return filter == null ? storage.size() : filtered.size();
    }

    public ItemFilter getFilter() {
        return filter;
    }

    public void setFilter(ItemFilter filter) {
        this.filter = filter;
        filtered.clear();
        if (filter == null) return;
        for (TestResultItem testResultItem : storage) {            
            if(filter.include(testResultItem)) filtered.add(testResultItem);
        }
    }
}
