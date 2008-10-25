/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.tests;

import j2se.g261.eda.automator.Automator;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 *
 * @author nastya
 */
public abstract class Tester {
    private ConcurrentSkipListSet<TestFile<TestItemStorage>> testStorages;
    protected TestFile current;
    protected ItemFilter resultFilter = null;
    protected ConcurrentSkipListSet<TestFile<TestResultItemStorage>> results;
    protected Automator currentAutomator = null;
    protected long allTime;
    protected long allPercent;
    protected long allSize = 0l;
    protected long allSizeFiles = 0l;
    protected long processedSize = 0l;
    protected long processedSizeFiles = 0l;

    public Tester(TestFile[] tests) {
        testStorages = new ConcurrentSkipListSet<TestFile<TestItemStorage>>();
        results = new ConcurrentSkipListSet<TestFile<TestResultItemStorage>>();
        for (TestFile testFile : tests) {
            allSize += ((TestItemStorage)testFile.getStorage()).size();
            allSizeFiles++;
            testStorages.add(testFile);
        }
    }
    
    public ConcurrentSkipListSet<TestFile<TestResultItemStorage>> testAll(){
        for (TestFile testItemStorage : testStorages) {
            current = testItemStorage;
            TestResultItemStorage is = test((TestItemStorage) testItemStorage.getStorage());
            is.setFilter(resultFilter);
            results.add(new TestFile<TestResultItemStorage>(testItemStorage.getFile(), is));
            resetTimer();
        }
        return results;
    }

    protected abstract TestResultItemStorage test(TestItemStorage testItemStorage);
    protected abstract int getCurrentPercent();
    protected abstract long getCurrentTime();
    protected abstract String getCurrentName();
    protected abstract int getAllPercent();
    protected abstract long getAllTime();
    protected abstract void resetTimer();
    protected abstract void loadNewPattern(String pattern);

    public ItemFilter getResultFilter() {
        return resultFilter;
    }

    public void setResultFilter(ItemFilter resultFilter) {
        this.resultFilter = resultFilter;
        for (TestFile<TestResultItemStorage> testFile : results) {
            ((TestResultItemStorage)testFile.getStorage()).setFilter(resultFilter);
        }
    }
    
    
}
