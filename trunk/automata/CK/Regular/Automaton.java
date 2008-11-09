/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Auto.src.Regular;

/**
 *
 * @author Кирилл
 */
import java.util.*;

public class Automaton {

    int first, fin;
    ArrayList<Transition> transitions = new ArrayList<Transition>();
    static char EMPTY = '$';
    static int stateNum = 0;

    private void setFirst(int newFirst) {
        for (Transition trans : transitions) {
            if (trans.from == this.first) {
                trans.from = newFirst;
            }
            if (trans.to == this.first) {
                trans.to = newFirst;
            }
        }
        this.first = newFirst;
    }

    private void setFin(int newFin) {
        for (Transition trans : transitions) {
            if (trans.from == this.fin) {
                trans.from = newFin;
            }
            if (trans.to == this.fin) {
                trans.to = newFin;
            }
        }
        this.fin = newFin;
    }

    public static Automaton buildQuestion(Automaton auto) {
        auto.transitions.add(new Transition(auto.first, auto.fin, EMPTY));
        return auto;
    }

    public static Automaton buildClosure(Automaton auto) {
        auto = buildQuestion(auto);
        auto.transitions.add(new Transition(auto.fin, auto.first, EMPTY));
        return auto;
    }

    public static Automaton buildAltern(Automaton main,Automaton additional) {
        additional.setFirst(main.first);
        additional.setFin(main.fin);
        main.combine(additional);
        return main;
    }

    public static Automaton buildConcat(Automaton first,Automaton second) {
        second.setFirst(first.fin);
        first.combine(second);
        first.fin = second.fin;
        return first;

    }

    public static Automaton buildPrimitive(char symbol) {
        Automaton automat = new Automaton();
        automat.first = stateNum;
        automat.transitions.add(new Transition(stateNum, ++stateNum, symbol));
        automat.fin = stateNum;
        return automat;
    }

    public void printAutomaton() {
        for (Transition trans : transitions) {
            System.out.println("(" + trans.from + ";" + trans.to + ";" + trans.symbol + ")");
        }
    }

    public boolean checkTail(String word, int currentState, int charPos) {
        if (word .equals("")) {
            return true;
        }
        if (charPos < word.length()) {
            for (Transition trans : transitions) {
                if (trans.from == currentState) {
                    if (trans.symbol == word.charAt(charPos)) {
                        boolean checkTail = this.checkTail(word, trans.to, ++charPos);
                        if (checkTail) {
                            return checkTail;
                        }
                    }
                }
            }
            for (Transition trans : transitions) {
                if (trans.from == currentState) {
                    if (trans.symbol == EMPTY) {
                        boolean checkTail = this.checkTail(word, trans.to, charPos);
                        if (checkTail) {
                            return checkTail;
                        }
                    }
                }
            }
            return false;
        } else if (currentState == this.fin) {
            return true;
        } else {
            return false;
        }
    }

//    public void checkWord() {
//        Input in = new Input();
//        System.out.println("Enter the word you want to check:");
//        String word = in.getLine();
//        System.out.println(checkTail(word, this.first, 0));
//    }

    private void combine(Automaton additional) {
        for (Transition trans : additional.transitions) {
            this.transitions.add(trans);
        }
    }

    private static void determine(Automaton automat) {
        
    }

    private static void minimize(Automaton automat) {
    }

}
