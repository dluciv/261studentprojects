package j2se.g261.eda.automator.representations.minimisation;

import java.util.Vector;

/**
 * @author Dmitry
 * class for cheking if word tenance to minimize graph 
 * (only for minimize and deterministic and graph with edges)
 */
public class EdgeGraphWalker {

    private MinimizedDFA g;

    public EdgeGraphWalker(MinimizedDFA g) {
        this.g = g;
    }

    /**
     * @param s - checked word
     * @return true if word tenance to FA
     */
    public boolean check(String s) {
        int length = s.length();
        int state = 0;
        boolean isNext = true;

        for (int i = 0; i < length; i++) {
            if (isNext) {

                char symbol = s.charAt(i);
                Vector<Edge> v = g.findOutgoingEdge(state);

                for (Edge k : v) {
                    if (k.getName() == symbol) {
                        state = k.getOutgoing();
                        isNext = true;
                        break;
                    }
                    isNext = false;
                }

            }
        }
        if (isNext) {
            Vector<Edge> end = g.findOutgoingEdge(state);
            for (Edge j : end) {
                if (j.getOutgoing() == 1) {
                    isNext = true;
                    break;
                } else {
                    isNext = false;
                }
            }
        }
        return isNext;
    }
}
