package mulya;

import Regexp.NFA;
import Regexp.DFA;
import Regexp.MFA;


public class Statistics
{
    private static long runNFATest(NFA nfa, String word) {
        long startTime = System.nanoTime();
        for (long i = 0; i <= 1000; ++i) {
            nfa.check(word);
        }
        return (System.nanoTime() - startTime);
    }

    private static long runDFATest(DFA dfa, String word) {
        long startTime = System.nanoTime();
        for (int i = 0; i <= 1000; ++i) {
            dfa.check(word);
        }
        return (System.nanoTime() - startTime);
    }

    private static long runMFATest(MFA mfa, String word) {
        long startTime = System.nanoTime();
        for (int i = 0; i <= 1000; ++i) {
            mfa.check(word);
        }
        return (System.nanoTime() - startTime);
    }

    public static double[] autoTest(String word, NFA nfa, DFA dfa, MFA mfa) {
        double[] data = new double[3];
        double wordWeight = word.getBytes().length;
        double testTime = runNFATest(nfa, word);
        data[0] = wordWeight / testTime * 1000000;
        testTime = runDFATest(dfa, word);
        data[1] = wordWeight / testTime * 1000000;
        testTime = runMFATest(mfa, word);
        data[2] = wordWeight / testTime * 1000000;
        return data;
    }
}
