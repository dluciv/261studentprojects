/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.tests;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Vector;

/**
 *
 * @author nastya
 */
public class TestResultItemStorage implements Serializable {

    private Vector<TestResultItem> storage;
    public static final String MATCHES = "1";
    public static final String NOT_MATCHES = "0";
    private ItemFilter filter = null;
    private Vector<TestResultItem> filtered;
    private String additionalInfo = "";
    private Vector<String> allPatterns;
    private HashMap<String, Double> bandWidthAll;
    public static boolean IGNORE_NULL_TIME = true;
    private HashMap<String, Integer> counts;

    public TestResultItemStorage() {
        storage = new Vector<TestResultItem>();
        filtered = new Vector<TestResultItem>();
        allPatterns = new Vector<String>();
        bandWidthAll = new HashMap<String, Double>();
        counts = new HashMap<String, Integer>();
    }

    public void addTestResult(TestResultItem item) {
        storage.add(item);
        if (filter == null || filter.include(item)) {
            filtered.add(item);
        }
        if (!allPatterns.contains(item.getPattern())) {
            allPatterns.add(item.getPattern());
        }
        if(!counts.containsKey(item.getPattern())){
            counts.put(item.getPattern(), 1);
        }else{
            int c = counts.get(item.getPattern()) + 1;
            counts.remove(item.getPattern());
            counts.put(item.getPattern(), c);
        }

    }
    
    public int getCountOfItemsForPattern(String pattern){
        return counts.get(pattern);
    }

    public TestResultItem getTestResult(int index) {
        return filtered.get(index);
    }

    public int size() {
        return filtered.size();
    }

    public int allSize(){
        return storage.size();
    }
    public ItemFilter getFilter() {
        return filter;
    }

    public void setFilter(ItemFilter filter) {
        this.filter = filter;
        filtered.clear();
        if (filter == null) {
            filtered = (Vector<TestResultItem>) storage.clone();
            return;
        }
        for (TestResultItem testResultItem : storage) {
            if (filter.include(testResultItem)) {
                filtered.add(testResultItem);
            }
        }

    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Vector<String> getAllPatterns() {
        return allPatterns;
    }

    public static TestResultItemStorage getDataForSerializing(TestResultItemStorage toSer) {
        TestResultItemStorage itemStorage = new TestResultItemStorage();
        for (int i = 0; i < toSer.size(); i++) {
            itemStorage.addTestResult(toSer.getTestResult(i));
        }
        itemStorage.setFilter(null);
        itemStorage.setAdditionalInfo(toSer.getAdditionalInfo());
        return itemStorage;
    }

    public void countBandwidth() {
        ResultTimeStorage result = new ResultTimeStorage();
        for (TestResultItem testResultItem : storage) {
            int length = testResultItem.getString().getBytes().length;
            Vector<Double> times = new Vector<Double>();
            if (!(IGNORE_NULL_TIME && testResultItem.getDFA().getAverageTime() == 0)) {
                times.add((double) length / testResultItem.getDFA().getAverageTime());
            }
            if (!(IGNORE_NULL_TIME && testResultItem.getNFA().getAverageTime() == 0)) {
                times.add((double) length / testResultItem.getNFA().getAverageTime());
            }
            if (!(IGNORE_NULL_TIME && testResultItem.getMinGraph().getAverageTime() == 0)) {
                times.add((double) length / testResultItem.getMinGraph().getAverageTime());
            }
            if (!(IGNORE_NULL_TIME && testResultItem.getTable().getAverageTime() == 0)) {
                times.add((double) length / testResultItem.getTable().getAverageTime());
            }
            if (!times.isEmpty()) {
                result.addTime(testResultItem.getPattern(), 1000000000d * min(times));
            }
        }
        bandWidthAll = result.getBandwidth();
    }

    public double getBandwidthByPattern(String pattern) {
        return bandWidthAll.get(pattern);
    }

    private double min(Vector<Double> aDouble) {
        double min = 0d;
        if (aDouble.size() == 0) {
            return 0;
        }
        if (aDouble.size() > 0) {
            min = aDouble.get(0);
        }
        for (int i = 0; i < aDouble.size(); i++) {
            if (min > aDouble.get(i)) {
                min = aDouble.get(i);
            }
        }
        return min;
    }
}

class ResultTimeStorage {

    private HashMap<String, TimeItem> storage;

    ResultTimeStorage() {
        storage = new HashMap<String, TimeItem>();
    }

    void addTime(String pattern, double time) {
        if (!storage.containsKey(pattern)) {
            storage.put(pattern, new TimeItem(time));
        } else {
            storage.get(pattern).addBandwidth(time);
        }
    }

    HashMap<String, Double> getBandwidth() {
        HashMap<String, Double> res = new HashMap<String, Double>();

        Iterator<Entry<String, TimeItem>> it = storage.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, TimeItem> e = it.next();
            res.put(e.getKey(), e.getValue().getAverage());
        }

        return res;
    }
}

class TimeItem {

    private double bandwidth;
    private int count;

    TimeItem() {
        bandwidth = 0d;
        count = 0;
    }

    TimeItem(double initialTime) {
        bandwidth = initialTime;
        count++;
    }

    void addBandwidth(double time) {
        this.bandwidth += time;
        count++;
    }

    public int getCount() {
        return count;
    }

    public double getTime() {
        return bandwidth;
    }

    public double getAverage() {
        if (count == 0) {
            return 0l;
        } else {
            return (double) bandwidth / count;
        }
    }
}