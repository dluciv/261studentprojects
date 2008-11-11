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

    public void addTestResult(TestResultItem item) {
        storage.add(item);
        if (filter == null || filter.include(item)) {
            filtered.add(item);
        }

    }

    public TestResultItem getTestResult(int index) {
        return filtered.get(index);
    }

    public void removeTest(int index) {
        if (filter == null) {
            if(filtered.contains(storage.get(index))){
                filtered.removeElementAt(index);
            }
            storage.removeElementAt(index);
        }
    }

    public int size() {
        return filtered.size();
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
