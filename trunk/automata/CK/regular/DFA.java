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
    static TreeSet<String> alphabet = new TreeSet<String>();
    static final char EMPTY = '$';
    static final int BAD = -1;

    public static DFA Determinator(NFA nfa) {
        DFA dfa = new DFA();
        int currentState;
        ArrayList<Integer> firstState = new ArrayList<Integer>();
        
        alphabet = NFA.alphabet;
        firstState.add(nfa.first);
        ArrayList<Integer> DFAbeg = reachedBy(EMPTY, firstState, nfa);
        dfa.first = dfa.stateNum;
        dfa.newState(dfa.first, DFAbeg, false);
        while (dfa.hasUnmarkedStates()) {
            currentState = dfa.getUnmarked();
            dfa.mark(currentState);
            for (String letter : alphabet) {
                char symb = letter.charAt(0);
                ArrayList<Integer> next = reachedBy(EMPTY, reachedBy(symb, dfa.states.get(currentState).statesList, nfa), nfa);

                if (!next.isEmpty()) {
                    int num = dfa.getNum(next);
                    if (num == BAD) {
                        dfa.newState(++dfa.stateNum, next, false);
                        dfa.addTrans(currentState, dfa.stateNum, symb);
                    } else {
                        dfa.addTrans(currentState, num, symb);
     }
                }
            }
        }
        dfa.setFinalStates(nfa.fin);
        return dfa;

    }

    private void setFinalStates(int NFAfin) {
        for (int state : states.keySet()) {
            if (states.get(state).statesList.contains(NFAfin)) {
                fins.add(state);
            }
        }
    }

    private boolean hasUnmarkedStates() {
        for (int state : states.keySet()) {
            if (!states.get(state).marked) {
                return true;
            }
        }
        return false;
    }

    private int getNum(ArrayList<Integer> NFAstatesList) {
        for (int DFAstate : states.keySet()) {
            if (NFAstatesList.equals(states.get(DFAstate).statesList)) {
                return DFAstate;
            }
        }
        return BAD;
    }

    private void newState(int DFAstate, ArrayList<Integer> NFAstates, boolean marked) {
        states.put(DFAstate, new DFAstateStatus(NFAstates, marked, new ArrayList<Transition>()));

    }

    private int getUnmarked() {
        for (int state : states.keySet()) {
            if (states.get(state).marked == false) {
                return state;
            }
        }
        return BAD;
    }

    private void mark(int state) {
        states.get(state).marked = true;
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


    protected void addTrans(int from, int to, char by) {
        states.get(from).trans.add(new Transition(to, by));
    }

    protected int whereTo(char symb, int from) {
        for (Transition trans : states.get(from).trans) {
            if (trans.symbol == symb) {
                return trans.to;
            }
        }
        return BAD;
    }

    public void printAutomaton() {
        for (int i : states.keySet()) {
            for (Transition trans : states.get(i).trans) {
                System.out.println(i + "->" + trans.to + ":" + trans.symbol);
            }
        }
        System.out.println('\n');
    }

    public boolean checkWord(String word){
    	prepareForNextWord();
    	return checkTail( word, first);
    }
    public boolean checkTail(String word, int currentState) {
    if (pos < word.length()) {
        for (Transition trans : states.get(currentState).trans) {
                if (trans.symbol == word.charAt(pos)) {
                    ++pos;
                    boolean checkTail = checkTail(word, trans.to);
                    return checkTail;
                }
            }
            return false;
        }
        if (fins.contains(currentState)) {
            return true;
        }
        return false;

    }

    public void prepareForNextWord() {
        pos = 0;
    }
}
