/*
 * NFAWalker.java
 * 
 * Version 1.0
 * 
 * 6.10.2008
 * 
 * (c) "EDA"
 */
package j2se.g261.eda.automator.representations.nfa;

import j2se.g261.eda.automator.representations.nfa.NFA;

/**
 * Make graph detour for cheking if word tenance FA 
 * Process epsilon-closed graph only
 * @author Dmitry
 */
public class NFAWalker {

    private NFANode node = null;

    /**
     * default constructor 
     * @param graph walked graph 
     * @throws j2se.g261.eda.automator.graph.NFAWalkerException
     * if graph not epsilon-closed
     */
    public NFAWalker(NFA graph) throws NFAWalkerException {
        try {
            for (int i = 0; i < graph.allSize(); i++) {
                if (NFANode.isStartNode(graph.getNodeFromAllAt(i))) {
                    node = graph.getNodeFromAllAt(i);
                    break;
                }
            }
            node.getIncomingSize();
        } catch (Exception ex) {
            throw new NFAWalkerException();
        }

    }

    /**
     * cheks if word tenances FA
     * @param s string to be checked
     * @return if word tenances FA then <code> true</code> ,<code> false</code> otherwise
     */
    public boolean check(String s) {
        return checkNextElement(s, node);
    }

    private boolean checkNextElement(String rest, NFANode n) {
        if (rest.length() == 0) {
            for (int i = 0; i < n.getOutgoingSize(); i++) {
                if (NFANode.isEndNode((NFANode) n.getOutgoingAt(i))) {
                    return true;
                }
            }
            return false;
        } else {
            char current = rest.charAt(0);
            for (int i = 0; i < n.getOutgoingSize(); i++) {
                if (((NFANode) n).getOutgoingAt(i).getName().equals(current) &&
                        checkNextElement(rest.substring(1),
                        (NFANode) n.getOutgoingAt(i))) {
                    return true;
                }
            }
            return false;
        }

    }
}