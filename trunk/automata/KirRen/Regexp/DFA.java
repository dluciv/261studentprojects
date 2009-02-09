package Regexp;

/**
 * @author Renat Akhmedyanov
 */

import java.util.HashMap;
import java.util.HashSet;

class DFAState {
    HashSet<Integer> nfaStatesSet;
    boolean marked = false;
    Transitions trans = new Transitions();
}

public class DFA {
    static int BAD_STATE = -1;
    HashMap<Integer, DFAState> map = new HashMap<Integer, DFAState>();
    int first = -1;
    HashSet<Integer> fins = new HashSet<Integer>();

    public static DFA buildDFA(NFA nfa, HashSet<Character> alphabet) {
        DFA dfa = new DFA();

        HashSet<Integer> firstSet = new HashSet<Integer>();
        firstSet.add(nfa.first);
        dfa.first = dfa.newDFAState();
        dfa.map.get(dfa.first).nfaStatesSet = getEClosure(firstSet, nfa);

        while (true) {
            DFAState currentDFAState = dfa.getUnmarkedState();
            if (currentDFAState == null) break;
            
            currentDFAState.marked = true;
            for (Character cmd: alphabet) {
                HashSet<Integer> newStateSet = getReachable(cmd, currentDFAState.nfaStatesSet, nfa);
                newStateSet = getEClosure(newStateSet, nfa);
                if (!newStateSet.isEmpty()) {
                    int dfaToState = dfa.searchState(newStateSet);
                    if (dfaToState == BAD_STATE) {
                        dfaToState = dfa.newDFAState();
                        dfa.map.get(dfaToState).nfaStatesSet = newStateSet;
                    }
                    currentDFAState.trans.put(cmd, dfaToState);
                }
            }
        }

        for (int dfaState: dfa.map.keySet()) {
            if (dfa.map.get(dfaState).nfaStatesSet.contains(nfa.fin))
                dfa.fins.add(dfaState);
        }

        return dfa;
    }

    private static HashSet<Integer> getReachable(char cmd, HashSet<Integer> nfaStatesSet, NFA nfa) {
        HashSet<Integer> output = new HashSet<Integer>();
        if (cmd == NFA.EMPTY) {
            output.addAll(nfaStatesSet);
        }
        for (Integer state: nfaStatesSet) {
            Transitions ts = nfa.map.get(state);
            for (Transition trans: ts.trans) {
                if (trans.c == cmd) {
                    output.add(trans.to);
                }
            }
        }
        return output;
    }

    private static HashSet<Integer> getEClosure(HashSet<Integer> nfaStatesSet, NFA nfa) {
        HashSet<Integer> output = getReachable(NFA.EMPTY, nfaStatesSet, nfa);
        int outputSizePre = output.size();
        while (true) {
            output = getReachable(NFA.EMPTY, output, nfa);
            if (outputSizePre == output.size()) break;
            outputSizePre = output.size();
        }
        return output;
    }

    private int newDFAState() {
        DFAState s = new DFAState();
        s.marked = false;
        int state = map.size() + 1;
        map.put(state, s);
        return state;
    }

    private DFAState getUnmarkedState() {
        for (Integer i: map.keySet()) {
            if (!map.get(i).marked) {
                return map.get(i);
            }
        }
        return null;
    }

    private int searchState(HashSet<Integer> set) {
        for (Integer i: map.keySet()) {
            if (map.get(i).nfaStatesSet.equals(set))
                return i;
        }
        return BAD_STATE;
    }

    public String produceDotty() {
        String output = "";
        output += "digraph DFA {\n";
        for (int i: map.keySet()) {
            output += map.get(i).trans.produceDotty(i, first, fins);
        }
        output += "}\n";
        return output;
    }

    public int leadsTo(int state, char cmd) {
        DFAState s = map.get(state);
        for (Transition t: s.trans.trans) {
            if (t.c == cmd)
                return t.to;
        }
        return BAD_STATE;
    }

    public boolean check(String test) {
        return checkHelper(test, 0, first);
    }

    private boolean checkHelper(String test, int pos, int state) {
        if (pos < test.length()) {
            for (Transition t: map.get(state).trans.trans) {
                if (t.c == test.charAt(pos)) {
                    return checkHelper(test, pos+1, t.to);
                }
            }
            return false;
        }
        return fins.contains(state);
    }
}
