/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.representations.dfa;

import j2se.g261.eda.automator.representations.nfa.NFANode;
import j2se.g261.eda.automator.representations.nfa.NFA;
import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author nastya
 */
public class DFAWorker {

    public static DFA convertFromNFA(NFA graph) {
        return collectStates(graph);
    }

    private static DFA collectStates(NFA nfa) {
        NFANode startNode = nfa.getNodeById(0);
        NFANode endNode = nfa.getNodeById(1);

        HashMap<Vector<NFANode>, DFANode> result = new HashMap<Vector<NFANode>, DFANode>();
        Vector<Vector<NFANode>>  seen = new Vector<Vector<NFANode>>();
        Vector<Character> alphabet = getAlphabet(nfa);
        Vector<NFANode> start = getStartNodeSet(startNode);
        Vector<NFANode> end = getEndNodeSet(endNode);
        Stack<Vector<NFANode>> toSee = new Stack<Vector<NFANode>>();

        toSee.push(start);
        DFANode stDFANode = new DFANode("q", extractNumbers(start));
        result.put(start, stDFANode);
        DFA dfa = new DFA(stDFANode);

        while (!toSee.empty()) {
            Vector<NFANode> v = toSee.pop();
            seen.add(v);
            for (Character symbol : alphabet) {
                Vector<NFANode> res = move(v, symbol);
                if (!seen.contains(res)) {
                    toSee.push(res);
                    DFANode newNd = new DFANode("q", extractNumbers(res));
                    result.put(res, newNd);
                }
                result.get(v).mapToSymbolOutgoing(result.get(res), symbol);
                result.get(res).mapToSymbolIncoming(result.get(v), symbol);
                result.get(res).addIncomingNode(result.get(v));
                dfa.addNode(result.get(res));
                if(haveEndNodes(res, end)){
                    dfa.markAsEnd(result.get(res));
                }
            }
        }


        return dfa;
    }

    private static Vector<Character> getAlphabet(NFA nfa) {
        Vector<Character> result = new Vector<Character>();
        for (int i = 0; i < nfa.allSize(); i++) {
            if (!result.contains(nfa.getNodeFromAllAt(i).getName())) {
                result.add(nfa.getNodeFromAllAt(i).getName());
            }
        }
        return result;
    }

    private static Vector<NFANode> getEndNodeSet(NFANode node) {
        Vector<NFANode> result = new Vector<NFANode>();

        for (int i = 0; i < node.getIncomingSize(); i++) {
            result.add((NFANode) node.getIncomingAt(i));
        }

        return result;
    }

    private static Vector<NFANode> getStartNodeSet(NFANode node) {
        Vector<NFANode> result = new Vector<NFANode>();

        for (int i = 0; i < node.getOutgoingSize(); i++) {
            result.add((NFANode) node.getOutgoingAt(i));
        }

        return result;
    }

    private static boolean haveEndNodes(Vector<NFANode> res, Vector<NFANode> end) {
        for (NFANode nFANode : res) {
            if(end.contains(res)){
                return true;
            }
        }
        return false;
    }

    private static Vector<NFANode> move(Vector<NFANode> nodes, char symbol) {
        Vector<NFANode> result = new Vector<NFANode>();

        for (NFANode node : nodes) {
            for (int i = 0; i < node.getOutgoingSize(); i++) {
                if (((NFANode) node.getOutgoingAt(i)).getName().equals(symbol)) {
                    result.add((NFANode) node.getOutgoingAt(i));
                }
            }
        }

        return result;
    }
    
    private static Vector<Integer> extractNumbers(Vector<NFANode> nodes){
        Vector<Integer> v = new Vector<Integer>();
        
        for (NFANode node : nodes) {
            if(!v.contains(node.getNumber())){
                v.add(node.getNumber());
            }
        }
        
        return v;
    }
}
