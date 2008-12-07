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
        NFA automat = Parser.parse( regExp);
        automat.printAutomaton();
        System.out.println('\n');
        DFA DFA = new DFA();
        DFA.Determinator(automat);
        DFA.printAutomaton();
        //String word = in.getLine();
        //System.out.println(automat.checkWord(word, 0, 0));
        MFA MFA =new MFA(); 
        MFA.minimizer(DFA);
        MFA.printAutomaton();

    }
}