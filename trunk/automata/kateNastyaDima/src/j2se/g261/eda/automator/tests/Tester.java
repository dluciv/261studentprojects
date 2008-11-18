/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.tests;

import j2se.g261.eda.automator.Automator;
import j2se.g261.eda.automator.NoConditionsException;
import j2se.g261.eda.automator.parser.ParserException;
import j2se.g261.eda.automator.representations.nfa.NFAWalkerException;
import j2se.g261.eda.automator.visualizing.dot.DotException;
import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author nastya
 */
public class Tester {

    private TestItemStorage testStorage;
    protected TestFile current;
    protected ItemFilter resultFilter = null;
    protected TestResultItemStorage results;
    protected Automator currentAutomator = null;
    protected long allTime;
    protected int allPercent = 0;
    protected int allSizeFiles = 0;
    protected int processedFiles = 0;
    private int numberOfMesures = 1000;
    private int cicle = 1;

    public Tester(TestItemStorage[] tests) {
        testStorage = TestItemStorage.concatanate(tests);
        results = new TestResultItemStorage();
        allSizeFiles = testStorage.size();
    }

    public void setCicle(int cicle) {
        this.cicle = cicle;
    }

    public int getNumberOfMesures() {
        return numberOfMesures;
    }

    public void setNumberOfMesures(int numberOfMesures) {
        this.numberOfMesures = numberOfMesures;
    }

    public void start() throws ParserException, NFAWalkerException,
            NoConditionsException, DotException, IOException {
        String lastPattern = "";
        Automator a = new Automator(lastPattern);
        System.out.println("!!!!!!!!!!");
        System.out.println(testStorage.size());
        for (int i = 0; i < testStorage.size(); i++) {
            if (!testStorage.getPattern(i).equals(lastPattern)) {
                lastPattern = testStorage.getPattern(i);
                a = new Automator(lastPattern);
                a.compile();
            }
            TestResultItem tri = test(testStorage.getString(i),
                    testStorage.isMatches(i).equals(TestItemStorage.MATCHES), a, lastPattern);
            processedFiles++;
            results.addTestResult(tri);
            System.out.println(i);
        }
    }

    protected TestResultItem test(String word, boolean expectedResult, Automator a, String pattern) {
        TestResultItem result = new TestResultItem(pattern, word, expectedResult);
        result.setCicle(cicle);
        testNFA(result, a, word, expectedResult);

        testDFA(result, a, word, expectedResult);
        testMinGraph(result, a, word, expectedResult);
        testTable(result, a, word, expectedResult);
        result.setCicle(1);
        return result;
    }

    private void testNFA(TestResultItem testResultItem, Automator a, String word, boolean exp) {
//        for (int i = 0; i < cicle - 1; i++) {
//            word += word;
//        }
        long min = 0;
        long max = 0;
        long avg = 0;
        boolean res = false;
        long all = 0;
        for (int i = 0; i < numberOfMesures; i++) {
            long startNFA = Calendar.getInstance().getTimeInMillis();
            res = a.matchNFAGraph(word);
            long endNFA = Calendar.getInstance().getTimeInMillis();
            long cur = endNFA - startNFA;
            if (i == 0) {
                min = cur;
                max = cur;
            } else {
                if (min > cur) {
                    min = cur;
                }
                if (max < cur) {
                    max = cur;
                }
            }
            all += cur;
        }
        System.out.println("min: " + min);
        System.out.println("max: " + max);
        System.out.println("all: " + all);
        System.out.println("num: " + numberOfMesures);
        System.out.println("avg: " + (long)(all / numberOfMesures));
        testResultItem.setNFA(new Result(res, (long)(all / numberOfMesures), min, max));
    }

    private void testDFA(TestResultItem testResultItem, Automator a, String word, boolean exp) {
//        for (int i = 0; i < cicle - 1; i++) {
//            word += word;
//        }
        long min = 0;
        long max = 0;
        long avg = 0;
        boolean res = false;
        long all = 0;
        for (int i = 0; i < numberOfMesures; i++) {
            long startNFA = Calendar.getInstance().getTimeInMillis();
            res = a.matchDFAGraph(word);
            long endNFA = Calendar.getInstance().getTimeInMillis();
            long cur = endNFA - startNFA;
            if (i == 0) {
                min = cur;
                max = cur;
            } else {
                if (min > cur) {
                    min = cur;
                }
                if (max < cur) {
                    max = cur;
                }
            }
            all += cur;
        }
        testResultItem.setDFA(new Result(res, all / numberOfMesures, min, max));
    }

    private void testMinGraph(TestResultItem testResultItem, Automator a, String word, boolean exp) {
//        for (int i = 0; i < cicle - 1; i++) {
//            word += word;
//        }
        long min = 0;
        long max = 0;
        long avg = 0;
        boolean res = false;
        long all = 0;
        for (int i = 0; i < numberOfMesures; i++) {
            long startNFA = Calendar.getInstance().getTimeInMillis();
            res = a.matchMinGraph(word);
            long endNFA = Calendar.getInstance().getTimeInMillis();
            long cur = endNFA - startNFA;
            if (i == 0) {
                min = cur;
                max = cur;
            } else {
                if (min > cur) {
                    min = cur;
                }
                if (max < cur) {
                    max = cur;
                }
            }
            all += cur;
        }
        testResultItem.setMinGraph(new Result(res, all / numberOfMesures, min, max));
    }

    private void testTable(TestResultItem testResultItem, Automator a, String word, boolean exp) {
//        for (int i = 0; i < cicle - 1; i++) {
//            word += word;
//        }
        long min = 0;
        long max = 0;
        boolean res = false;
        long all = 0;
        for (int i = 0; i < numberOfMesures; i++) {
            long startNFA = Calendar.getInstance().getTimeInMillis();
            res = a.matchTable(word);
            long endNFA = Calendar.getInstance().getTimeInMillis();
            long cur = endNFA - startNFA;
            if (i == 0) {
                min = cur;
                max = cur;
            } else {
                if (min > cur) {
                    min = cur;
                }
                if (max < cur) {
                    max = cur;
                }
            }
            all += cur;
        }
        testResultItem.setTable(new Result(res, all / numberOfMesures, min, max));
    }

    public float getCurrentPercent() {
        if (allSizeFiles == 0) {
            return 100f;
        } else {
            return (float) 100 * processedFiles / allSizeFiles;
        }
    }

    public int getCurrentNumber() {
        return processedFiles;
    }

    public int getAllFileSize() {
        return allSizeFiles;
    }

    public TestResultItemStorage getResults() {
        return results;
    }

    public ItemFilter getResultFilter() {
        return resultFilter;
    }

    public void setResultFilter(ItemFilter resultFilter) {
        this.resultFilter = resultFilter;
        results.setFilter(resultFilter);
    }
}
