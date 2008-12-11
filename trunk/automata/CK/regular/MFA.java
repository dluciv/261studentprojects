/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regular;

/**
 *
 * @author Кирилл
 */
import java.util.ArrayList;

public class MFA extends DFA {

    public void minimizer(DFA DFA) {
        this.setAlphabet();
        ArrayList<ArrayList<Integer>> statesSet = getMFAstates(DFA);
        this.setStates(statesSet);
        this.setTrans(DFA);
        this.setFirst(DFA.first);
        this.setFins(DFA);
    }

    private static ArrayList<ArrayList<Integer>> getMFAstates(DFA DFA) {
        ArrayList<ArrayList<Integer>> statesSet = firstPartition(DFA);
        ArrayList<ArrayList<Integer>> newStatesSet = newPartition(statesSet, DFA);
        while (!newStatesSet.equals(statesSet)) {
            statesSet = newStatesSet;
            newStatesSet = newPartition(statesSet, DFA);
        }
        return statesSet;
    }

    private void setFirst(int DFAfirst) {
        for (int state : this.states.keySet()) {
            if (this.states.get(state).statesList.contains(DFAfirst)) {
                this.first = state;
                break;
            }
        }
    }

    private void setFins(DFA DFA) {
        for (int fin : DFA.fins) {
            for (int state : this.states.keySet()) {
                if (this.states.get(state).statesList.contains(fin) & !this.fins.contains(state)) {
                    this.fins.add(state);
                }
            }
        }
    }

    private void setStates(ArrayList<ArrayList<Integer>> statesSet) {
        for (ArrayList<Integer> group : statesSet) {
            this.states.put(stateNum++, new DFAstateStatus(group, true, new ArrayList<Transition>()));
        }
    }

    private void setTrans(DFA DFA) {
        for (int state : this.states.keySet()) {
            ArrayList<Transition> transList = getTransList(DFA, state);
            for( int destState : this.states.keySet()){
                for( Transition trans: transList){
                    if( this.states.get(destState).statesList.contains(trans.to) ){
                          this.states.get(state).trans.add( new Transition (destState, trans.symbol));
                    }
                }
            }
        }
    }

    private ArrayList<Transition> getTransList(DFA DFA, int state) {
        ArrayList<Transition> trans = new ArrayList<Transition>();
        int dest;
        for (String letter : alphabet) {
            char symb = letter.charAt(0);
            for (int DFAstate : this.states.get(state).statesList) {
                dest = DFA.whereTo(symb, DFAstate);
                if (dest != BAD) {
                    trans.add(new Transition(dest, symb));
                    break;
                }
            }
        }
        return trans;

    }

    private static ArrayList<ArrayList<Integer>> newPartition(ArrayList<ArrayList<Integer>> statesSet, DFA DFA) {
        ArrayList<ArrayList<Integer>> newStatesSet = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> statesWithEquals = new ArrayList<Integer>();
        for (ArrayList<Integer> group : statesSet) {
            for (int state1 : group) {
                if (!statesWithEquals.contains(state1)) {
                    ArrayList<Integer> newGroup = makeGroup(state1);
                    for (int state2 : group) {
                        if (state2 != state1) {
                            if (equival(state1, state2, DFA, statesSet)) {
                                newGroup.add(state2);
                                statesWithEquals.add(state2);
                            }
                        }
                    }
                    newStatesSet.add(newGroup);
                }
            }
        }
        return newStatesSet;
    }

    private static ArrayList<Integer> makeGroup(int state) {
        ArrayList<Integer> newGroup = new ArrayList<Integer>();
        newGroup.add(state);
        return newGroup;
    }

    private static boolean equival(int state1, int state2, DFA DFA, ArrayList<ArrayList<Integer>> statesSet) {
        int dest1, dest2;
        for (String letter : DFA.alphabet) {
            char symb = letter.charAt(0);
            dest1 = DFA.whereTo(symb, state1);
            dest2 = DFA.whereTo(symb, state2);
            for (ArrayList<Integer> group : statesSet) {
                if ((group.contains(dest1) & !group.contains(dest2)) | (!group.contains(dest1) & group.contains(dest2))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static ArrayList<ArrayList<Integer>> firstPartition(DFA DFA) {
        ArrayList<ArrayList<Integer>> statesSet = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> allStates = new ArrayList<Integer>();
        allStates.addAll(DFA.states.keySet());
        allStates.removeAll(DFA.fins);
        statesSet.add(allStates);
        statesSet.add(DFA.fins);
        return statesSet;
    }
}
