package Regular;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

        regExp = Parser.markEnd(regExp);
        NFA NFA = Parser.parse(regExp);
        NFA.printAutomaton();
        System.out.println('\n');
        DFA DFA = new DFA();
        DFA.Determinator(NFA);
        DFA.printAutomaton();
        MFA MFA = new MFA();
        MFA.minimizer(DFA);
        System.out.println('\n');
        MFA.printAutomaton();
        String word = in.getLine();
        while (!word.equals("")) {
            System.out.println(NFA.checkWord(word, NFA.first));
            System.out.println(DFA.checkWord(word, DFA.first));
            System.out.println(MFA.checkWord(word, MFA.first));
            NFA.prepareForNextWord();
            DFA.prepareForNextWord();
            MFA.prepareForNextWord();
            word = in.getLine();
        }
    }
}