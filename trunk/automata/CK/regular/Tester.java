package Regular;

import java.io.*;
import java.util.*;

public class Tester {

    private static ArrayList<String> getStrings(File fileName) {
        ArrayList<String> strings = new ArrayList<String>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            while (in.ready()) {
                strings.add(in.readLine());
            }

        } catch (IOException e) {
        }
        return strings;

    }

    public static void test(String testDir, String resultsFile) {
        int i = 1;
        File dir = new File(testDir);
        File results = new File(resultsFile);
        File[] filesList = dir.listFiles();
        results.delete();
        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(results)));
            for (File fileName : filesList) {
                ArrayList<String> exprAndWords = getStrings(fileName);
                String expr = exprAndWords.get(0);
                exprAndWords.remove(0);
                out.println("Test № " + i++);
                out.println(expr);
                NFA nfa = Parser.parse(expr);
                DFA dfa = DFA.determine(nfa);
                MFA mfa = MFA.minimize(dfa);
                for (String word : exprAndWords) {
                    out.println(word);
                    if (nfa.checkWord(word)) {
                        out.println("true");
                    } else {
                        out.println("false");
                    }
                    if (dfa.checkWord(word)) {
                        out.println("true");
                    } else {
                        out.println("false");
                    }
                    if (mfa.checkWord(word)) {
                        out.println("true");
                    } else {
                        out.println("false");
                    }
                    for (int j = 0; j <= 2; ++j) {
                        out.println(autoTest(word, nfa, dfa, mfa)[j]);
                    }
                }
                System.out.println("");
            }
            out.close();
        } catch (FileNotFoundException fnfe) {
        }

    }

    private static void autoTestConsole(String word, NFA nfa, DFA dfa, MFA mfa) {
        double wordWeight = word.getBytes().length;
        double testTime = runNFATest(nfa, word);
        System.out.println(wordWeight / testTime * 1000000);
        testTime = runDFATest(dfa, word);
        System.out.println(wordWeight / testTime * 1000000);
        testTime = runMFATest(mfa, word);
        System.out.println(wordWeight / testTime * 1000000);
    }

    private static long runNFATest(NFA nfa, String word) {
        long startTime = System.nanoTime();
        for (long i = 0; i <= 1000; ++i) {
            nfa.checkWord(word);
        }
        return (System.nanoTime() - startTime);
    }

    private static long runDFATest(DFA dfa, String word) {
        long startTime = System.nanoTime();
        for (int i = 0; i <= 1000; ++i) {
            dfa.checkWord(word);
        }
        return (System.nanoTime() - startTime);
    }

    private static long runMFATest(MFA mfa, String word) {
        long startTime = System.nanoTime();
        for (int i = 0; i <= 1000; ++i) {
            mfa.checkWord(word);
        }
        return (System.nanoTime() - startTime);
    }

    private static double[] autoTest(String word, NFA nfa, DFA dfa, MFA mfa) {
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

