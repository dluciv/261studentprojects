package j2se.g261.eda.automator.representations.minimisation;

import j2se.g261.eda.automator.representations.dfa.DFA;
import j2se.g261.eda.automator.representations.dfa.DFANode;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Class contains some methods for processing DFAs and minimized DFAs and easing
 * working with minimized DFA.
 *
 * @author Dmitriy
 */
public class MinimizedDFAWorker {

    private MinimizedDFA minDFA;
    public static final int START_NODE_NUMBER = 0;
    public static final int END_NODE_NUMBER = 1;

    /**
     * Default constructor
     */
    public MinimizedDFAWorker() {
        this.minDFA = new MinimizedDFA();
    }

    /**
     * Transforms all entities from DFA-specified to MinimizedDFA-specified.
     * Used for easing of applying of minimisation algorithm.
     *
     * @param g determinated finite automata to be transformed
     * @return entity represents equialent minimized DFA
     */
    public MinimizedDFA convertFromNFAToMinimizedDFA(DFA g) {
        int num = g.allSize();
        int newNumber = 2;
        for (int i = 0; i < num; i++) {
            if (g.isEnd(g.getNodeFromAllAt(i))) {
                g.getNodeFromAllAt(i).setNumber(END_NODE_NUMBER);

            } else if (g.isStart(g.getNodeFromAllAt(i))) {
                g.getNodeFromAllAt(i).setNumber(START_NODE_NUMBER);
            } else {
                g.getNodeFromAllAt(i).setNumber(newNumber);
                newNumber++;
            }
        }
        DFANode n = (DFANode) g.getNodeFromAllAt(0);

        return toEdges(n, g);
    }

    private MinimizedDFA toEdges(DFANode n, DFA g) {
        int num = g.allSize();
        for (int i = 0; i < num; i++) {
            DFANode dfaNode = g.getNodeFromAllAt(i);
            
            Iterator<Entry<Character, DFANode>> iter = dfaNode.getOutgoingIterator();
            while (iter.hasNext()) {
                Entry<Character, DFANode> en = iter.next();
                if (en.getKey() == DFA.EMPTY_CHARACTER) {
                    Edge endEdge = new Edge(DFA.EMPTY_CHARACTER, dfaNode.getNumber(), 1);
                    
                    minDFA.add(endEdge);
                } else {
                    Edge endEdge = new Edge(en.getKey(), dfaNode.getNumber(), en.getValue().getNumber());
                    
                    minDFA.add(endEdge);
                }
            }


        }
        return minDFA;
    }
}
