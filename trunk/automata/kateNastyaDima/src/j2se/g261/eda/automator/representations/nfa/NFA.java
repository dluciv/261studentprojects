/*
 * NFA.java
 * 
 * Version 1.0
 * 
 * 16.0.2008
 * 
 * (c) "EDA"
 */
package j2se.g261.eda.automator.representations.nfa;

import j2se.g261.eda.automator.representations.*;
import java.util.Vector;

/**
 *Represents graph with start and end nodes. Created for presentation of 
 * NFA and DFA. All actions you may perform with graph locates in GraphWorker 
 * class. NFA object just a storage for nodes with possibility of node
 * ordering accordingly to topologoical role(start, end or nothing)
 * 
 * 
 * @author Anastasiya
 * @author Dmitry
 * 
 * @see j2se.g261.eda.automator.graph.GraphWorker
 */
public class NFA extends FiniteAutomata<NFANode> implements Cloneable {

    /**
     * Constructor with one node which will be only node in graph.
     * 
     * @param root 
     *        root node. This node will be only node in graph. It is start 
     *        and end node.
     */
    public NFA(NFANode root) {
        super(root);
    }

    private NFA(Vector<NFANode> allNFANodes, Vector<NFANode> startNFANodes, Vector<NFANode> endNFANodes) {

        for (NFANode node : allNFANodes) {
            all.add(node.cloneWithoutConnections());
        }

        for (NFANode node : startNFANodes) {
            starts.add(all.get(all.indexOf(node)));
        }

        for (NFANode node : endNFANodes) {
            ends.add(all.get(all.indexOf(node)));
        }

        for (NFANode node : allNFANodes) {
            NFANode newNFANode = all.get(all.indexOf(node));
            for (int i = 0; i < node.getIncomingSize(); i++) {
                newNFANode.addIncomingNode(all.get(all.indexOf(node.getIncomingAt(i))));
            }
            for (int i = 0; i < node.getOutgoingSize(); i++) {
                newNFANode.addOutgoingNode(all.get(all.indexOf(node.getOutgoingAt(i))));
            }
        }

    }

    /**
     * Represents graph as string (for debug)
     * 
     * @return string representation of graph
     */
    @Override
    public String toString() {
        String s = "Graph: " + all.size() + "nodes\n";

        for (NFANode node : all) {
            s += node.toString();
            s += "\n";

        }

        return s;
    }

    /**
     * 
     * Walks through the graph and checks each node if it haven't incoming 
     * connections. If true when marks node as start.
     * 
     * @see j2se.g261.eda.automator.graph.NFA#markAllEnds()
     */
    void markAllStarts() {
        starts.removeAll(starts);

        for (NFANode node : all) {

            if (!node.haveIncoming()) {
                starts.add(node);
            }
        }
    }

    public NFANode getNodeById(int index) {
        for (NFANode nFANode : all) {
            if (nFANode.getNumber() == index) {
                return nFANode;
            }
        }
        return null;
    }

    /**
     * 
     * Walks through the graph and checks each node if it haven't outgoing 
     * connections. If true when marks node as end.
     * 
     * @see j2se.g261.eda.automator.graph.NFA#markAllStarts()
     * @see j2se.g261.eda.automator.graph.NFA#isEnd(j2se.g261.eda.automator.graph.NFANode)
     */
    void markAllEnds() {

        ends.removeAll(ends);

        for (NFANode node : all) {
            if (!node.haveOutgoing()) {
                ends.add(node);
            }
        }

    }

    public int getNodeIndex(NFANode n) {
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i) == n) {
                return i;
            }
        }
        return -1;
    }

    public static NFA emptyGraph() {
        return new NFA(NFANode.epsilonNode());
    }

    @Override
    public NFA clone() {
        return new NFA(all, starts, ends);
    }
}
