package Regular;

/**
 *
 * @author Кирилл
 */

import java.util.ArrayList;

public class DFAstateStatus {
    ArrayList<Integer> statesList;
    boolean marked;
    ArrayList<Transition> trans;

    DFAstateStatus(ArrayList<Integer> NFAstates, boolean marked, ArrayList<Transition> trans) {
        this.statesList = NFAstates;
        this.marked = marked;
        this.trans = trans;
    }
}
