package Regexp;

/**
 * @author Renat Akhmedyanov
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class NFA implements IFiniteStateMachine {
    HashMap<Integer, Transitions> map = new HashMap<Integer, Transitions>();
    int first = -1, fin = -1;
    static char EMPTY = 0;

    // this state will be uniq for all NFA, so we can easily combine several NFAs
    static int nextState = 1;

    private int newState() {
        map.put(nextState, new Transitions());
        nextState++;
        return nextState - 1;
    }

    static public NFA buildLetter(char c) {
        NFA nfa = new NFA();
        nfa.first = nfa.newState();
        nfa.fin = nfa.newState();
		//System.out.println(c+": "+nfa.first+", "+nfa.fin);
        nfa.map.get(nfa.first).put(c, nfa.fin);
        return nfa;
    }

    public void applyOr(NFA nfa) {
        combine(nfa);
        int newFirst = newState();
        int newFin = newState();
        map.get(newFirst).put(EMPTY, first);
        map.get(newFirst).put(EMPTY, nfa.first);
        map.get(fin).put(EMPTY, newFin);
        map.get(nfa.fin).put(EMPTY, newFin);
        first = newFirst;
        fin = newFin;
    }

    public void applyConcat(NFA nfa) {
        combine(nfa);
        map.get(fin).put(EMPTY, nfa.first);
        fin = nfa.fin;
    }

    public void applyClosure() {
        map.get(fin).put(EMPTY, first);
        int newFirst = newState();
        int newFin = newState();
        map.get(newFirst).put(EMPTY, first);
        first = newFirst;
        map.get(fin).put(EMPTY, newFin);
        fin = newFin;
        map.get(first).put(EMPTY, fin);
    }

    public void combine(NFA nfa) {
        for (int state : nfa.map.keySet()) {
            map.put(state, nfa.map.get(state));
        }
    }

    public String produceDotty() {
        String output = "";
        HashSet<Integer> fins = new HashSet<Integer>();
        fins.add(fin);
        output += "digraph NFA {\n";
        for (int i: map.keySet()) {
            output += map.get(i).produceDotty(i, first, fins);
        }
        output += "}\n";
        return output;
    }

    public boolean check(String test) {
        return checkHelper(test, 0, first, 0);
    }

    private boolean checkHelper(String test, int pos, int state, int level) {
        // Without this level check the following test isn't working
        //   simpleTest("(b|((ab*)a*)|a*|ab)*", "b", true);
        if (level > 500) return false;
        if (pos < test.length()) {
            for (Transition t: map.get(state).trans) {
                if (t.c == test.charAt(pos)) {
                    if (checkHelper(test, pos+1, t.to, level+1)) {
                        return true;
                    }
                }
            }
            for (Transition t: map.get(state).trans) {
                if (t.c == EMPTY) {
                    if (checkHelper(test, pos, t.to, level+1)) {
                        return true;
                    }
                }
            }
            return false;
        }
        if (state == this.fin) {
            return true;
        }
        for (Transition t: map.get(state).trans) {
            if (t.c == EMPTY) {
                if (checkHelper(test, pos, t.to, level+1)) {
                    return true;
                }
            }
        }
        return false;
    }
}
