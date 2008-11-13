package j2se.g261.eda.automator.representations.minimisation;

import j2se.g261.eda.automator.representations.Node;
import j2se.g261.eda.automator.representations.nfa.NFA;
import j2se.g261.eda.automator.representations.nfa.NFANode;
import j2se.g261.eda.automator.representations.dfa.DFA;
import j2se.g261.eda.automator.representations.dfa.DFANode;

import java.util.Iterator;
import java.util.Vector;
import java.util.Map.Entry;
/**
 * Class contains some methods for processing DFAs and minimized DFAs and easing
 * working with minimized DFA.
 *
 * @author Dmitriy
 */
public class MinimizedDFAWorker {

    private MinimizedDFA minDFA;

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
                g.getNodeFromAllAt(i).setNumber(1);

            } else if (g.isStart(g.getNodeFromAllAt(i))) {
                g.getNodeFromAllAt(i).setNumber(0);
            } else {
                g.getNodeFromAllAt(i).setNumber(newNumber);
                newNumber++;
            }
        }
        DFANode n = (DFANode) g.getNodeFromStartsAt(0);
        
        return toEdges(n, g);
    }

    private MinimizedDFA toEdges(DFANode n, DFA g) {
    	int num = g.allSize();
    	for(int i = 0;i < num; i++){
    		DFANode dfa = g.getNodeFromAllAt(i);
    		int num2 = dfa.getMapOutgoingSize();
    		Iterator<Entry<Character, DFANode>> iter = dfa.getOutgoingIterator();
    		while(iter.hasNext()){
    			Entry<Character, DFANode> en = iter.next() ;
    			if (dfa.getMapIncomingSize() == 0) {
                    Edge endEdge = new Edge('\n', dfa.getNumber(), en.getValue().getNumber());
                    minDFA.add(endEdge);
                    }
                    else{
                    	Edge endEdge = new Edge(en.getKey(), dfa.getNumber(), en.getValue().getNumber());
                        minDFA.add(endEdge);
                    }         
    		}
    		
    		
    	}
    	return minDFA;
    }
}