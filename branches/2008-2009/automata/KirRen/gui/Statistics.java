package gui;

import regexp.NFA;
import regexp.DFA;
import regexp.MFA;

import java.util.Random;
import java.util.Vector;

/**
 * @author Murashov Kirill
 */

public class Statistics
{
    private static long run(IFACheckable fa, String word)
    {
        long startTime = System.nanoTime();
        for (long i = 0; i <= 1000; ++i) {
            fa.check(word);
        }
        return (System.nanoTime() - startTime);

    }
    
    public static double[] autoTest(String word, NFA nfa, DFA dfa, MFA mfa) {
        double[] data = new double[3];
        double wordWeight = word.getBytes().length;
        double testTime = run(nfa, word);
        data[0] = wordWeight / testTime * 1000000;
        testTime = run(dfa, word);
        data[1] = wordWeight / testTime * 1000000;
        testTime = run(mfa, word);
        data[2] = wordWeight / testTime * 1000000;
        return data;
    }

    public static int campTest(int length, int number, NFA nfa, MFA mfa)
    {
        String tmpWord;
        int res = 0;

        Vector<Character> chars = nfa.getVectorCharFromMapThithoutE();
        int size = chars.size();

        Random r = new Random();
        for (int i = 0; i < number; i++)
        {
            tmpWord = "";
            for (int j = 0; j < length; j++)
            {
                int idx = r.nextInt(size);
                tmpWord += chars.get(idx);
            }
            if (mfa.check(tmpWord))
            {
                res++;
            }
        }
        return res;
    }


}
