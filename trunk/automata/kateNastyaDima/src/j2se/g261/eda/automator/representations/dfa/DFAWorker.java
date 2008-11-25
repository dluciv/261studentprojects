/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.representations.dfa;

import j2se.g261.eda.automator.representations.nfa.NFANode;
import j2se.g261.eda.automator.representations.nfa.NFA;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author nastya
 */
public class DFAWorker {

    private static final String DEFAULT_PREFIX = "q";

    public static DFA convertFromNFA(NFA graph) {
        return collectStates(graph);
    }

    private static DFA collectStates(NFA nfa) {
        NFANode startNode = nfa.getNodeById(NFANode.START_INDEX);
        NFANode endNode = nfa.getNodeById(NFANode.END_INDEX);

        HashMap<VectorWithIndiscernibleOrder<NFANode>, DFANode> result =
                new HashMap<VectorWithIndiscernibleOrder<NFANode>, DFANode>();
        Vector<VectorWithIndiscernibleOrder<NFANode>> seen =
                new Vector<VectorWithIndiscernibleOrder<NFANode>>();
        Vector<Character> alphabet = getAlphabet(nfa);
        VectorWithIndiscernibleOrder<NFANode> start =
                new VectorWithIndiscernibleOrder<NFANode>();
        start.add(startNode);
        Vector<NFANode> end = getEndNodeSet(endNode);
        Vector<VectorWithIndiscernibleOrder<NFANode>> toSee =
                new Vector<VectorWithIndiscernibleOrder<NFANode>>();

        toSee.add(start);

        DFANode stDFANode = new DFANode(DEFAULT_PREFIX, extractNumbers(start));
        result.put(start, stDFANode);
        DFA dfa = new DFA(stDFANode);
        if (haveEndNodes(start, end)) {
            dfa.markAsEnd(stDFANode);
        }

        while (!toSee.isEmpty()) {
            VectorWithIndiscernibleOrder<NFANode> v = toSee.firstElement();
            seen.add(v);
            toSee.remove(0);
            for_loop:
            for (Character symbol : alphabet) {
                VectorWithIndiscernibleOrder<NFANode> res = move(v, symbol);
                if (res.isEmpty()) {
                    continue for_loop;
                }
                if (!seen.contains(res) && !toSee.contains(res)) {
                    toSee.add(res);
                    DFANode newNd = new DFANode(DEFAULT_PREFIX, extractNumbers(res));
                    result.put(res, newNd);
                }

                result.get(v).mapToSymbolOutgoing(result.get(res), symbol);
                result.get(res).mapToSymbolIncoming(result.get(v), symbol);
                dfa.addNode(result.get(res));
                if (haveEndNodes(res, end)) {
                    dfa.markAsEnd(result.get(res));
                }
            }

            dfa.makeEndClosure();
        }

        return dfa;
    }

    private static Vector<Character> getAlphabet(NFA nfa) {
        Vector<Character> result = new Vector<Character>();
        for (int i = 0; i < nfa.allSize(); i++) {
            if (!result.contains(nfa.getNodeFromAllAt(i).getName()) && !nfa.getNodeFromAllAt(i).getName().equals(NFANode.START) && !nfa.getNodeFromAllAt(i).getName().equals(NFANode.END)) {
                result.add(nfa.getNodeFromAllAt(i).getName());
            }
        }
        return result;
    }

    private static Vector<NFANode> getEndNodeSet(NFANode node) {
        Vector<NFANode> result = new Vector<NFANode>();

        int num = node.getIncomingSize();

        for (int i = 0; i < num; i++) {
            result.add((NFANode) node.getIncomingAt(i));
        }

        return result;
    }

    private static boolean haveEndNodes(Vector<NFANode> res, Vector<NFANode> end) {
        for (NFANode nFANode : res) {
            if (end.contains(nFANode)) {
                return true;
            }
        }
        return false;
    }

    private static VectorWithIndiscernibleOrder<NFANode> move(Vector<NFANode> nodes, char symbol) {
        VectorWithIndiscernibleOrder<NFANode> result =
                new VectorWithIndiscernibleOrder<NFANode>();

        for (NFANode node : nodes) {
            for (int i = 0; i < node.getOutgoingSize(); i++) {
                if (((NFANode) node.getOutgoingAt(i)).getName().equals(symbol)) {
                    if (!result.contains(((NFANode) node).getOutgoingAt(i))) {
                        result.add((NFANode) node.getOutgoingAt(i));
                    }
                }
            }
        }

        return result;
    }

    private static Vector<Integer> extractNumbers(Vector<NFANode> nodes) {
        Vector<Integer> v = new Vector<Integer>();

        for (NFANode node : nodes) {
            if (!v.contains(node.getNumber())) {
                v.add(node.getNumber());
            }
        }

        return v;
    }
}

