/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Regular;

/**
 *
 * @author ������
 */
import java.util.*;

public class NFA {

    int first, fin;
    HashMap<Integer, ArrayList<Transition>> states = new HashMap<Integer, ArrayList<Transition>>();
    
    static char EMPTY = '$';
    static int stateNum = 0;

    protected ArrayList<Transition> getTrans(int state) {
        return this.states.get(state);
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
        for ( int i: this.states.keySet()) {
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
    protected void newState( int state){
        this.states.put(state, new ArrayList<Transition>());
    }
    protected void addTrans( int from, int to, char by ){
        this.states.get(from).add(new Transition(to, by));
    }
}

