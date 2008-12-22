package Regular;

/**
 * @author Кирилл
 */
import java.io.*;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        try{
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String regExp = in.readLine();
//
//        NFA nfa = Parser.parse(regExp);
//        nfa.printAutomaton();
//        DFA dfa = DFA.determine(nfa);
//        dfa.printAutomaton();
//        MFA mfa = MFA.minimize(dfa);
//        mfa.printAutomaton();
//        String word = in.readLine();
//        System.out.println(nfa.checkWord(word));
//        System.out.println(dfa.checkWord(word));
//        System.out.println(mfa.checkWord(word));
//        } catch (IOException io){            
//        }
        Tester.test(Paths.testDir, Paths.resultsFile);
    }
}