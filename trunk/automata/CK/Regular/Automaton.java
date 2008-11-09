/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Regular;

/**
 *
 * @author Кирилл
 */
import java.util.*;

public class Automaton {

    int first, fin;
    HashMap<Integer, ArrayList<Transition>> states = new HashMap<Integer, ArrayList<Transition>>();
    static char EMPTY = '$';
    static int stateNum = 0;

    private void setFirst(int newFirst) {
        ArrayList<Transition> buf = this.states.get(this.first);
        this.states.remove(this.first);
        this.states.put(newFirst, buf);

        for (ArrayList<Transition> listOfTrans : this.states.values()) {
            for (Transition trans : listOfTrans) {
                if (trans.to == this.first) {
                    trans.to = newFirst;
                }
            }

        }
        this.first = newFirst;
    }

    private void setFin(int newFin) {
        ArrayList<Transition> buf = this.states.get(this.fin);
        this.states.remove(this.fin);
        this.states.put(newFin, buf);
        for (ArrayList<Transition> listOfTrans : this.states.values()) {
            for (Transition trans : listOfTrans) {
                if (trans.to == this.fin) {
                    trans.to = newFin;
                }
            }

        }
        this.fin = newFin;
    }

    public static Automaton buildQuestion(Automaton auto) {
        auto.states.get(auto.first).add(new Transition(auto.fin, EMPTY));
        return auto;
    }

    public static Automaton buildClosure(Automaton auto) {
        auto = buildQuestion(auto);
        auto.states.get(auto.fin).add(new Transition(auto.first, EMPTY));
        return auto;
    }

    public static Automaton buildAltern(Automaton main, Automaton additional) {
        additional.setFirst(main.first);
        additional.setFin(main.fin);
        main.combine(additional);
        return main;
    }

    public static Automaton buildConcat(Automaton first, Automaton second) {
        second.setFirst(first.fin);
        first.combine(second);
        first.fin = second.fin;
        return first;
    }

    public static Automaton buildPrimitive(char symbol) {
        Automaton automat = new Automaton();
        automat.first = stateNum;
        automat.states.put(stateNum, new ArrayList<Transition>());
        automat.states.get(stateNum).add(new Transition(++stateNum, symbol));
        automat.fin = stateNum;
        automat.states.put(automat.fin, new ArrayList<Transition>());
        return automat;
    }

    public void printAutomaton() {

        for (int i = 0; (i <= stateNum) & (this.states.containsKey(i)); ++i) {
            for (Transition trans : this.states.get(i)) {
                System.out.println(i + "->" + trans.to + ":" + trans.symbol);
            }
        }
    }

    public boolean checkWord(String word, int currentState, int pos) {
        if (pos < word.length()) {
            if (this.states.containsKey(currentState)) {
                for (Transition trans : this.states.get(currentState)) {
                    if (trans.symbol == word.charAt(pos)) {
                        boolean checkTail = this.checkWord(word, trans.to, ++pos);
                        if (checkTail) {
                            return checkTail;
                        }
                    }
                }
                for (Transition trans2 : this.states.get(currentState)) {
                    if (trans2.symbol == EMPTY) {
                        boolean checkTail = this.checkWord(word, trans2.to, pos);
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
        for (int i = 0; i <= stateNum; ++i) {
            if (additional.states.containsKey(i)) {
                if (this.states.containsKey(i)) {
                    for (Transition trans : additional.states.get(i)) {
                        this.states.get(i).add(trans);
                    }
                } else {
                    this.states.put(i, additional.states.get(i));
                }
            }
        }
    //this.states.putAll( additional.states);
    }

    private static void determine(Automaton automat) {
    }

    private static void minimize(Automaton automat) {
    }
}
