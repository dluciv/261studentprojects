/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.representations;

import j2se.g261.eda.automator.representations.nfa.NFANode;
import java.util.Vector;

/**
 *
 * @author nastya
 */
public class FiniteAutomata<NodeType extends Node> {
    /**
     * Storage for start nodes
     */
    protected  Vector<NodeType> starts;
    /**
     * Storage for end nodes
     */
    protected  Vector<NodeType> ends;
    /**
     * Storage for all nodes
     */
    protected  Vector<NodeType> all;

    public FiniteAutomata(NodeType root) {
        starts = new Vector<NodeType>();
        ends = new Vector<NodeType>();
        all = new Vector<NodeType>();
        starts.add(root);
        ends.add(root);
        all.add(root);
    }

    public FiniteAutomata() {
        starts = new Vector<NodeType>();
        ends = new Vector<NodeType>();
        all = new Vector<NodeType>();
    }


    public void addNode(NodeType n) {
        if (!all.contains(n)) {
            all.add(n);
        }
        int num = n.getOutgoingSize();
        for (int i = 0; i < num; i++) {
            NodeType n1 = (NodeType) n.getOutgoingAt(i);
            if (!all.contains(n1)) {
                addNode(n1);
            }
        }
    }

    /**
     * Returns number of nodes ing graph(size of all node list)
     *
     * @return all nodes size
     */
    public int allSize() {
        return all.size();
    }

    public void deleteNode(NFANode n) {
        n.prepareForDeleting();
        all.remove(n);
        starts.remove(n);
        ends.remove(n);
    }

    /**
     * Returns number of end nodes (size of end node list)
     *
     * @return end nodes size
     */
    public int endsSize() {
        return ends.size();
    }

    public NodeType getNodeFromAllAt(int index) {
        return (NodeType) all.get(index);
    }

    public NodeType getNodeFromEndsAt(int index) {
        return (NodeType) ends.get(index);
    }

    public NodeType getNodeFromStartsAt(int index) {
        return (NodeType) starts.get(index);
    }

    public boolean isEnd(NodeType n) {
        return ends.contains(n);
    }

    public boolean isStart(NodeType n) {
        return starts.contains(n);
    }

    void simpleDeleteNode(NodeType n) {
        all.remove(n);
        starts.remove(n);
        ends.remove(n);
    }

    /**
     * Returns number of start nodes (size of start node list)
     *
     * @return start nodes size
     */
    public int startsSize() {
        return starts.size();
    }

}
