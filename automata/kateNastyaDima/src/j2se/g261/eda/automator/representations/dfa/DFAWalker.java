/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.representations.dfa;

/**
 *
 * @author nastya
 */
public class DFAWalker {

    DFA dfa;

    public DFAWalker(DFA dfa) {
        this.dfa = dfa;
    }

    private boolean checkNextSymbol(DFANode current, String rest) {
//        long time1 = System.nanoTime();
        if (rest.length() == 0) {
//            long time2 = System.nanoTime();
//            System.out.println("next check: " + rest + " " + (time1 - time2));
            return current.getNextNode(DFA.EMPTY_CHARACTER) != null;
        } else {
            DFANode nextNode = current.getNextNode(rest.charAt(0));
            if (nextNode == null) {
//                long time2 = System.nanoTime();
//                System.out.println("next check: " + rest + " " + (time1 - time2));
                return false;
            } else {
//                long time2 = System.nanoTime();
//                System.out.println("next check: " + rest + " " + (time1 - time2));
                return checkNextSymbol(nextNode, rest.substring(1));
            }
        }
    }

    public boolean check(String word) {
        return checkNextSymbol(dfa.getStartNode(), word);
    }
}
