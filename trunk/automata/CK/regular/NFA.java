package Regular;

/**
 * 
 * @author ������
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class NFA {

    int first, fin;
    HashMap<Integer, ArrayList<Transition>> states = new HashMap<Integer, ArrayList<Transition>>();
    int pos = 0;
    static TreeSet<String> alphabet = new TreeSet<String>();
    static int stateNum = 0;
    static char EMPTY = '$';


    protected ArrayList<Transition> getTrans(int state) {
        return this.states.get(state);
    }

    public TreeSet<String> getAlphabet(String expr) {
        for (int i = 0; i < expr.length(); ++i) {
            if ((expr.charAt(i) != '*') & (expr.charAt(i) != '(') & (expr.charAt(i) != ')')
                    & (expr.charAt(i) != '|') & (expr.charAt(i) != '?')) {
                alphabet.add("" + expr.charAt(i));
            }
        }
        return alphabet;
    }


    protected void setFirst(int newFirst) {
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

    protected void setFin(int newFin) {
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

    public static NFA buildQuestion(NFA auto) {
        auto.states.get(auto.first).add(new Transition(auto.fin, EMPTY));
        return auto;
    }

    public static NFA buildClosure(NFA auto) {
        auto.states.get(auto.fin).add(new Transition(auto.first, EMPTY));
        auto.addEmptyBeg();
        auto.addEmptyEnd();
        auto = buildQuestion(auto);
        return auto;
    }

    public static NFA buildAltern(NFA main, NFA additional) {
        additional.setFirst(main.first);
        additional.setFin(main.fin);
        main.combine(additional);
        return main;
    }

    public static NFA buildConcat(NFA first, NFA second) {
        second.setFirst(first.fin);
        first.combine(second);
        first.fin = second.fin;
        return first;
    }

    public static NFA buildPrimitive(char symbol) {
        NFA automat = new NFA();
        automat.first = stateNum;
        automat.states.put(stateNum, new ArrayList<Transition>());
        automat.states.get(stateNum).add(new Transition(++stateNum, symbol));
        automat.fin = stateNum;
        automat.states.put(automat.fin, new ArrayList<Transition>());
        stateNum++;
        return automat;
    }


    public void printAutomaton() {
        for (int i : this.states.keySet()) {
            for (Transition trans : this.states.get(i)) {
                System.out.println(i + "->" + trans.to + ":" + trans.symbol);
            }
        }
        System.out.println('\n');
    }


    public boolean checkWord(String word) {
        prepareForNextWord();
        return checkTail(word, first);
    }

    private boolean checkTail(String word, int currentState) {
        if (pos < word.length()) {
            for (Transition trans : states.get(currentState)) {
                if (trans.symbol == word.charAt(pos)) {
                    ++pos;
                    if (checkTail(word, trans.to)) {
                        return true;
                    } else {
                        --pos;
                    }
                }
            }
            for (Transition trans2 : states.get(currentState)) {
                if (pos < word.length()) {
                    if (trans2.symbol == EMPTY) {
                        if (checkTail(word, trans2.to)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        if (currentState == this.fin) {
            return true;
        }
        if (this.hasEmptyTrans(currentState)) {
            for (Transition trans : states.get(currentState)) {
                if (trans.symbol == EMPTY) {
                    if (checkTail(word, trans.to)) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    private boolean hasEmptyTrans(int state) {
        for (Transition trans : this.states.get(state)) {
            if (trans.symbol == EMPTY) {
                return true;
            }
        }
        return false;

    }

    private void combine(NFA additional) {
        for (int state : additional.states.keySet()) {
            if (this.states.containsKey(state)) {
                for (Transition trans : additional.states.get(state)) {
                    this.states.get(state).add(trans);
                }
            } else {
                this.states.put(state, additional.states.get(state));
            }
        }
    }

    private void addEmptyBeg() {
        this.states.put(++stateNum, new ArrayList<Transition>());
        this.states.get(stateNum).add(new Transition(this.first, EMPTY));
        this.first = stateNum;
    }

    private void addEmptyEnd() {
        this.states.put(++stateNum, new ArrayList<Transition>());
        this.states.get(this.fin).add(new Transition(stateNum, EMPTY));
        this.fin = stateNum;
    }

    protected void newState(int state) {
        this.states.put(state, new ArrayList<Transition>());
    }

    protected void addTrans(int from, int to, char by) {
        this.states.get(from).add(new Transition(to, by));
    }

    public void prepareForNextWord() {
        this.pos = 0;
    }
}
