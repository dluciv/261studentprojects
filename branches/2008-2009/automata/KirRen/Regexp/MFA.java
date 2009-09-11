package regexp;

/**
 * @author Renat Akhmedyanov
 */

import gui.IFACheckable;

import java.util.HashSet;
import java.util.Vector;
import java.util.HashMap;

class MFAState {
    HashSet<Integer> group;
    boolean reachable = false;
    Transitions trans = new Transitions();
}

public class MFA implements IFACheckable
{
    HashMap<Integer, MFAState> map = new HashMap<Integer, MFAState>();
    int first = -1;
    HashSet<Integer> fins = new HashSet<Integer>();

    static public MFA buildMFA(DFA dfa, HashSet<Character> alphabet) {
        MFA mfa = new MFA();

        // initial set of groups
        Vector<HashSet<Integer> > groups = new Vector<HashSet<Integer> >();
        HashSet<Integer> initGroup1 = new HashSet<Integer>();
        initGroup1.addAll(dfa.map.keySet());
        initGroup1.removeAll(dfa.fins);
        HashSet<Integer> initGroup2 = new HashSet<Integer>();
        initGroup2.addAll(dfa.fins);
        groups.add(initGroup1);
        groups.add(initGroup2);

        // groups splitting
        while (true) {
            int groupsSizePre = groups.size();
            groups = splitGroups(groups, dfa, alphabet);
            if (groupsSizePre == groups.size()) break;
        }

        // construct states
        for (HashSet<Integer> group: groups) {
            int newState = mfa.map.size() + 1;
            MFAState mfaState = new MFAState();
            mfaState.group = group;
            mfa.map.put(newState, mfaState);
        }

        // fill transitions
        for (int mfaStateId: mfa.map.keySet()) {
            MFAState mfaState = mfa.map.get(mfaStateId);
            for (int dfaStateFrom: mfaState.group) {
                for (Transition t: dfa.map.get(dfaStateFrom).trans.trans) {
                    mfaState.trans.put(t.c, mfa.findStateByDFAState(t.to));
                }
            }
        }

        // set first & fins
        mfa.first = mfa.findStateByDFAState(dfa.first);
        for (int dfaFinState: dfa.fins) {
            mfa.fins.add(mfa.findStateByDFAState(dfaFinState));
        }

        // remove unreachable states
        mfa.traverseReachable(mfa.first);
        Vector<Integer> toRemove = new Vector<Integer>();
        for (int state: mfa.map.keySet()) {
            if (!mfa.map.get(state).reachable)
                toRemove.add(state);
        }
        for (int state: toRemove) {
            mfa.map.remove(state);
        }

        return mfa;
    }

    static private Vector<HashSet<Integer> > splitGroups(Vector<HashSet<Integer> > groups, DFA dfa, HashSet<Character> alphabet) {
        Vector<HashSet<Integer> > newGroups = new Vector<HashSet<Integer> >();
        for (HashSet<Integer> group: groups) {
            HashSet<Integer> processed = new HashSet<Integer>();
            for (int state1: group) {
                if (!processed.contains(state1)) {
                    HashSet<Integer> newGroup = new HashSet<Integer>();
                    newGroup.add(state1);
                    processed.add(state1);
                    for (int state2: group) {
                        if (!processed.contains(state2) & dfaStatesEquivalent(state1, state2, dfa, groups, alphabet)) {
                            newGroup.add(state2);
                            processed.add(state2);
                        }
                    }
                    newGroups.add(newGroup);
                }
            }
        }
        return newGroups;
    }

    static private boolean dfaStatesEquivalent(int state1, int state2, DFA dfa, Vector<HashSet<Integer> > groups, HashSet<Character> alphabet) {
        for (Character cmd: alphabet) {
            int leadTo1 = dfa.leadsTo(state1, cmd);
            int leadTo2 = dfa.leadsTo(state2, cmd);
            for (HashSet<Integer> group: groups) {
                if (group.contains(leadTo1) & !group.contains(leadTo2)) return false;
                if (group.contains(leadTo2) & !group.contains(leadTo1)) return false;
            }
        }
        return true;
    }

    private int findStateByDFAState(int dfaState) {
        for (int state: map.keySet()) {
            if (map.get(state).group.contains(dfaState))
                return state;
        }
        return DFA.BAD_STATE;
    }

    private void traverseReachable(int state) {
        MFAState mfaState = map.get(state);
        if (mfaState.reachable) return;
        mfaState.reachable = true;
        for (Transition t: mfaState.trans.trans) {
            traverseReachable(t.to);
        }
    }

    public String produceDotty() {
        String output = "";
        output += "digraph MFA {\n";
        for (int i: map.keySet()) {
            output += map.get(i).trans.produceDotty(i, first, fins);
        }
        output += "}\n";
        return output;
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
