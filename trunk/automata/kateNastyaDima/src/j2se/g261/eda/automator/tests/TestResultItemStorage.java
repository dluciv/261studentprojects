/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.tests;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author nastya
 */
public class TestResultItemStorage implements Serializable{

    private Vector<TestResultItem> storage;
    public static final String MATCHES = "1";
    public static final String NOT_MATCHES = "0";
    private ItemFilter filter = null;
    private Vector<TestResultItem> filtered;
    private String additionalInfo = "";
    private Vector<String> allPatterns;


    public TestResultItemStorage() {
        storage = new Vector<TestResultItem>();
        filtered = new Vector<TestResultItem>();
        allPatterns = new Vector<String>();
    }
    
    public void addTestResult(TestResultItem item) {
        storage.add(item);
        if (filter == null || filter.include(item)) {
            filtered.add(item);
        }
        if(!allPatterns.contains(item.getPattern())){
            allPatterns.add(item.getPattern());
        }

    }

    public TestResultItem getTestResult(int index) {
        return filtered.get(index);
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
        if (filter == null){
            filtered = (Vector<TestResultItem>) storage.clone();
            return;
        }
        for (TestResultItem testResultItem : storage) {            
            if(filter.include(testResultItem)) filtered.add(testResultItem);
        }
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    
    public Vector<String> getAllPatterns(){
        return allPatterns;
    }
    
    public static TestResultItemStorage getDataForSerializing(TestResultItemStorage toSer){
        TestResultItemStorage itemStorage = new TestResultItemStorage();
        for (int i = 0; i < toSer.size(); i++) {
            itemStorage.addTestResult(toSer.getTestResult(i));            
        }
        itemStorage.setFilter(null);
        itemStorage.setAdditionalInfo(toSer.getAdditionalInfo());
        return itemStorage;
    }
    
}
