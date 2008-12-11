package regular;

/**
 *
 * @author Кирилл
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Input in = new Input();
        String regExp = new String(in.getLine());
        
        NFA nfa = Parser.parse(regExp);
        nfa.printAutomaton();
        DFA dfa = DFA.Determinator(nfa);
        dfa.printAutomaton();
        MFA mfa = MFA.minimizer(dfa);
        mfa.printAutomaton();
        String word = in.getLine();
        while (!word.equals("")) {
<<<<<<< .mine
            System.out.println(nfa.checkWord(word));
            System.out.println(dfa.checkWord(word));
            System.out.println(mfa.checkWord(word));
=======
            System.out.println(NFA.checkWord(word));
            System.out.println(DFA.checkWord(word));
            System.out.println(MFA.checkWord(word));
>>>>>>> .r221
            word = in.getLine();
        }
    }
}