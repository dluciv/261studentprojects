/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.tests;

import j2se.g261.eda.automator.Automator;

/**
 *
 * @author nastya
 */
public abstract class Tester {
    private TestItemStorage testStorage;
    protected TestFile current;
    protected ItemFilter resultFilter = null;
    protected TestResultItemStorage results;
    protected Automator currentAutomator = null;
    protected long allTime;
    protected int allPercent = 0;
    protected int allSizeFiles = 0;
    protected int processedFiles = 0;

    public Tester(TestItemStorage[] tests) {
        testStorage = TestItemStorage.concatanate(tests);
        results = new TestResultItemStorage();
        allSizeFiles = testStorage.size();
    }
    
    public void testAll(){
        String lastPattern = "";
        Automator a = new Automator(lastPattern);
        for (int i = 0; i < testStorage.size(); i++) {
            if(!testStorage.getPattern(i).equals(lastPattern)){
                lastPattern = testStorage.getPattern(i);
                a = new Automator(lastPattern);
            }
            TestResultItem tri = test(testStorage.getString(i),
                    testStorage.isMatches(i).equals(TestItemStorage.MATCHES), a);
            processedFiles++;
            results.addTestResult(tri);
        }
    }

    protected abstract TestResultItem test(String word, boolean expectedResult, Automator a);
    protected abstract int getCurrentPercent();
    protected abstract String getCurrentNumber();
    protected abstract long getAllTime();

    public ItemFilter getResultFilter() {
        return resultFilter;
    }

    public void setResultFilter(ItemFilter resultFilter) {
        this.resultFilter = resultFilter;
        results.setFilter(resultFilter);
    }
    
    
}
