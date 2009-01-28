package Regexp;

/**
 * @author Renat Akhmedyanov
 */

public class Main {

    static void simpleTest(String regexp, String test, boolean should) throws ParserException {
        Parser parser = new Parser(regexp);
        NFA nfa = parser.parse();
        DFA dfa = DFA.buildDFA(nfa, parser.alphabet);
        MFA mfa = MFA.buildMFA(dfa, parser.alphabet);
        if (nfa.check(test) != should) {
            System.out.println("Test for NFA failed");
            System.out.println("RE: " + regexp + ", test: " + test);
        }
        if (dfa.check(test) != should) {
            System.out.println("Test for DFA failed");
            System.out.println("RE: " + regexp + ", test: " + test);
        }
        if (mfa.check(test) != should) {
            System.out.println("Test for MFA failed");
            System.out.println("RE: " + regexp + ", test: " + test);
        }
    }

    public static void main(String[] args) {
        try {
		    /*
            simpleTest("(b|((ab*)a*)|a*|ab)*", "b", true);
            simpleTest("a*", "aaaa", true);
            simpleTest("a*", "", true);
            simpleTest("a*", "aaaab", false);
            simpleTest("(a|b)", "b", true);
            simpleTest("(a|b)", "ab", false);
            simpleTest("vjreijviorejirjvijirjeivjlkfdvjrietu", "vjreijviorejirjvijirjeivjlkfdvjrietu", true);
            simpleTest("aaaaaaaaaabababbababababauwhudwhuahdwuabbab", "aaaaaaaaaababab", false);
            simpleTest("(abcd)|(abcdd)|(abcddd)|(abcdddd)|(abcddddd)|(abcdddddd)", "abcdddddd", true);
            simpleTest("(a|b)*abb", "abababababbabababababbabababababababababababababababababbbbb", false);
            simpleTest("(a|b)*abb", "aaaaaaaaaaaaabb", true);
            simpleTest("(a|b)*abb", "abbbabbbbbbbbbbbbbbbbbbbbbbbbbbbbbabb", true);
            simpleTest("a(b(d|c)|b(e|f)|c)", "ac", true);
            simpleTest("a(b(d|c)|b(e|f))", "ac", false);
			*/

            //Parser parser = new Parser("c(ab)*|(ab)*");
            //Parser parser = new Parser("ab|ab|ab|ab|ab");
            //Parser parser = new Parser("c(abc)*|a(a(bc)*)*|b(bc)*");
			Parser parser = new Parser("a|b");
            //Parser parser = new Parser("(b|((ab*)a*)|a*|ab)*");
            NFA nfa = parser.parse();
            DFA dfa = DFA.buildDFA(nfa, parser.alphabet);
            MFA mfa = MFA.buildMFA(dfa, parser.alphabet);
            //System.out.println(nfa.produceDotty());
            //System.out.println(dfa.produceDotty());
            System.out.println(nfa.produceDotty());
        } catch (ParserException e) {
            System.out.println("Error: " + e.message);
        }
    }
}
