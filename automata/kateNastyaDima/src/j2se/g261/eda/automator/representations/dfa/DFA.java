/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.representations.dfa;

import j2se.g261.eda.automator.representations.*;
import j2se.g261.eda.automator.representations.nfa.NFANode;

/**
 *
 * @author nastya
 */
public class DFA extends FiniteAutomata<DFANode> {

    public static final char EMPTY_CHARACTER = NFANode.END;

    public DFA(DFANode root) {
        super(root);
        ends.clear();
    }

    public DFANode getStartNode(){
        return starts.firstElement();
    }
    public void markAsEnd(DFANode node) {
        if (!all.contains(node)) {
            all.add(node);
        }
        if (!ends.contains(node)) {
            ends.add(node);
        }
    }

    public String toString() {
        String result = "";

        for (DFANode dFANode : all) {
            result += dFANode.toString() + "\n";
        }


        result += "********************END********************\n";
        for (DFANode dFANode : ends) {
            result += dFANode.toString() + "\n";
        }
        result += "<*******************************************>\n";
        result += "********************START********************\n";
        for (DFANode dFANode : starts) {
            result += dFANode.toString() + "\n";
        }
        result += "<*******************************************>\n";
        return result;
    }

    public void makeEndClosure() {
        DFANode end = DFANode.createEndNode();
        for (DFANode dFANode : ends) {
            end.mapToSymbolIncoming(dFANode, EMPTY_CHARACTER);
            dFANode.mapToSymbolOutgoing(end, EMPTY_CHARACTER);
        }
        addNode(end);
    }

    @Override
    public boolean isEnd(DFANode n) {
        DFANode end = DFANode.createEndNode();
        return end.equals(n);
    }
}
