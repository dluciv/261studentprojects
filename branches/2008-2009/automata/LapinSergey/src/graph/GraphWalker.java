/**
 *
 * Lapin Sergey 261 group mat-mex
 * Regular expression analysis
 * 19.01.2009
 */

package graph;

import Node.Node;
import Node.Arc;
import graph.GraphWorker;
import java.util.HashSet;

public class GraphWalker {

    private HashSet<Node> starts, ends;

    public GraphWalker(Graph graph) throws WalkerException {
        try {
            starts = graph.getStarts();
            ends = graph.getEnds();
        } catch (Exception ex) {
            throw new WalkerException();
        }       
    }   
    
    private boolean checkNextChar(String str, int index, Node n, HashSet<Integer> epsset){
        if(str.length() == index){
            HashSet<Node> res = GraphWorker.epsilonClosure(n.getOutgoing().getBySymbol(Arc.EPSILON).getNodeSet());
            for(Node tmp : res)
                if(ends.contains(tmp))
                    return true;
            if(ends.contains(n))
                    return true;
        }
        if(index >= str.length() || epsset.contains(n.getCondition())) {
            return false;
        }        
        boolean res = false;
        for(Arc tmp : n.getOutgoing().getBySymbol(Arc.EPSILON).set) {
            epsset.addAll(n.getCondition().condition);
            res = res || checkNextChar(str, index, tmp.vertex, epsset);
        }
        for(Arc tmp : n.getOutgoing().getBySymbol(str.charAt(index)).set) {
            res = res || checkNextChar(str, index + 1, tmp.vertex, epsset);
        }                
        return res;          
    }
    
    public boolean checkString(String str){
        HashSet<Integer> epsset = new HashSet<Integer>();
        boolean res = false;
        for(Node start : starts)
            res = res || checkNextChar(str, 0, start, epsset);
        return res;
    }
}