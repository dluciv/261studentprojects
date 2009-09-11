package j2se.g261.eda.automator.representations.minimisation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;
import j2se.g261.eda.automator.representations.dfa.DFA;

/**
 * @author Dmitry
 * class for cheking if word tenance to minimize graph 
 * (only for minimize and deterministic and graph with edges)
 */
public class EdgeGraphWalker {

    private MinimizedDFA g;
    //  private HashMap<Integer, Vector<Character>> allEdges; 

    public EdgeGraphWalker(MinimizedDFA g) {
        this.g = g;
    // allEdges = new HashMap<Integer, Vector<Character>>();     
    }

    /**
     * @param s - checked word
     * @return true if word tenance to FA
     */
    public boolean check(String s) {
        int length = s.length();
        int state = 0;
        boolean isNext = true;
//        long time1 = System.nanoTime();
        
        for (int i = 0; i < length; i++) {
            if (isNext) {

                char symbol = s.charAt(i);
                
                Edge v = g.getX(state).get(symbol);
                
                if(v != null){
                	state = v.getOutgoing();
                	isNext = true;
                }
                
                else {isNext = false;break;}
                }
            }
        
        //long time2 = System.nanoTime();
        //System.out.println("------" + (time2 - time1));
        //long time2_ = System.nanoTime();
        if (isNext) {
            Edge end = g.getX(state).get(DFA.EMPTY_CHARACTER);
                isNext = end != null;
            }
        
//        System.out.println("++++++" + (System.nanoTime() - time2_));
        return isNext;
    }
}
