
package Regular;

/**
 *
 * @author Кирилл
 */
import java.io.*;
public class Visualizator {
    public static void printNFA(NFA nfa) {
        File dot = new File("NFA.dot");
        dot.delete();
        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(dot)));
            out.println("digraph NFA {");
            for (int i : nfa.states.keySet()) {
                if (i == nfa.first) {
                    out.println(i + "[shape =doublecircle]");
                }
                if (i == nfa.fin) {
                    out.println(i + "[color=red]");
                }
                for (Transition trans : nfa.states.get(i)) {

                    out.println(i + "->" + trans.to + "[taillabel = \"" + trans.symbol + "\"]");
                }
            }
            out.println("}");
            out.close();//dot -v out.dot -T png -O
            Runtime.getRuntime().exec(Paths.DOT + " -v NFA.dot -Tgif -o NFA.gif");
        //Runtime.getRuntime().exec(Paths.VIEWER + " -T NFA.gif");

        } catch (IOException io) {
        }
    }
    public static void printDFA(DFA dfa) {
        String fileName = dfa.getClass().getCanonicalName().substring(8);
        File dot = new File(fileName + ".dot");
        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(dot)));
            out.println("digraph " + fileName + "{");
            for (int i : dfa.states.keySet()) {
                if ( i == dfa.first) {
                    out.println(i + "[shape = doublecircle]");
                }
                if (dfa.fins.contains(i)) {
                    out.println(i + "[color=red]");
                }
                for (Transition trans : dfa.states.get(i).trans) {
                    out.println(i + " -> " + trans.to + "[taillabel = \"" + trans.symbol + "\"]");
                }
            }
            out.println("}");
            out.close();
            Runtime.getRuntime().exec(Paths.DOT + " -v " + fileName + ".dot -Tgif  -o " + fileName + ".gif");
//            Runtime.getRuntime().exec(Paths.VIEWER + fileName +"gif .gif");

        } catch (IOException io) {
        }
    }

}
