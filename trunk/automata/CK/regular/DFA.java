/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Regular;

/**
 *
 * @author Кирилл
 */
import java.util.HashMap;
import java.util.ArrayList;
import java.util.TreeSet;

public class DFA {

    HashMap<Integer, DFAstateStatus> states = new HashMap<Integer, DFAstateStatus>();
    ArrayList<Integer> fins = new ArrayList<Integer>();
    int first;
    int stateNum = 0;
    int pos = 0;
    TreeSet<String> alphabet = new TreeSet();
    static char EMPTY = '$';
    static int BAD = -1;

    public void Determinator(NFA NFA) {
        int currentState;
        this.setAlphabet();
        ArrayList<Integer> firstState = new ArrayList<Integer>();
        firstState.add(NFA.first);
        ArrayList<Integer> DFAbeg = reachedBy(EMPTY, firstState, NFA);
        this.first = stateNum;
        this.newState(this.first, DFAbeg, false);
        while (this.hasUnmarkedStates()) {
            currentState = this.getUnmarked();
            this.mark(currentState);
            for (String letter : alphabet) {
                char symb = letter.charAt(0);
                ArrayList<Integer> next = reachedBy(EMPTY, reachedBy(symb, this.states.get(currentState).statesList, NFA), NFA);
                if (!next.isEmpty()) {
                    int num = this.getNum(next);
                    if (num == BAD) {
                        this.newState(++stateNum, next, false);
                        this.addTrans(currentState, stateNum, symb);
                    } else {
                        this.addTrans(currentState, num, symb);
                    }
                }
            }
        }
        this.setFinalStates(NFA.fin);
    }

    private void setFinalStates(int NFAfin) {
        for (int state : this.states.keySet()) {
            if (this.states.get(state).statesList.contains(NFAfin)) {
                this.fins.add(state);
            }
        }
    }

    private boolean hasUnmarkedStates() {
        for (int state : this.states.keySet()) {
            if (!this.states.get(state).marked) {
                return true;
            }
        }
        return false;
    }

    private int getNum(ArrayList<Integer> NFAstatesList) {
        for (int DFAstate : this.states.keySet()) {
            if (NFAstatesList.equals(this.states.get(DFAstate).statesList)) {
                return DFAstate;
            }
        }
        return BAD;
    }

    private void newState(int DFAstate, ArrayList<Integer> NFAstates, boolean marked) {
        this.states.put(DFAstate, new DFAstateStatus(NFAstates, marked, new ArrayList<Transition>()));

    }

    private int getUnmarked() {
        for (int state : this.states.keySet()) {
            if (this.states.get(state).marked == false) {
                return state;
            }
        }
        return BAD;
    }

    private void mark(int state) {
        this.states.get(state).marked = true;
    }

    private static ArrayList<Integer> reachedBy(char symb, ArrayList<Integer> states, NFA NFA) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (symb == EMPTY) {
            result.addAll(states);
        }
        for (Integer state : states) {
            for (Transition trans : NFA.states.get(state)) {
                if (trans.symbol == symb) {
                    if (!result.contains(trans.to)) {
                        result.add(trans.to);
                    }
                }
            }
        }
         return result;
    }

    public void setAlphabet() {
        for (char symb = 'a'; symb <= 'z'; symb++) {
            this.alphabet.add("" + symb);
        }
    }
//    protected static ArrayList<String> getSymbols( ArrayList<Integer> NFAstates, NFA NFA){
//        ArrayList<String> alphabet = new ArrayList<String>();
//        for(int state: NFAstates){
//            for(Transition trans: NFA.getTrans(state)){
//                if( !alphabet.contains(trans.symbol)) {
//                    alphabet.add("" + trans.symbol);
//                }               
//            }
//        } 
//        return alphabet;
//    }
    protected void addTrans(int from, int to, char by) {
        this.states.get(from).trans.add(new Transition(to, by));
    }

    protected int whereTo(char symb, int from) {
        for (Transition trans : this.states.get(from).trans) {
            if (trans.symbol == symb) {
                return trans.to;
            }
        }
        return BAD;
    }

    public void printAutomaton() {
        for (int i : this.states.keySet()) {
            for (Transition trans : this.states.get(i).trans) {
                System.out.println(i + "->" + trans.to + ":" + trans.symbol);
            }
        }
    }

    public boolean checkWord(String word, int currentState) {
    if (pos < word.length()) {
        for (Transition trans : this.states.get(currentState).trans) {
                if (trans.symbol == word.charAt(pos)) {
                    ++pos;
                    boolean checkTail = this.checkWord(word, trans.to);
                    return checkTail;
                }
            }
            return false;
        }
        if (this.fins.contains(currentState)) {
            return true;
        }
        return false;

    }

    public void prepareForNextWord() {
        this.pos = 0;
    }
}
