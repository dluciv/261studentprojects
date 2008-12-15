package graph;

import Node.Node;
import Node.Arc;
import java.util.HashSet;

public class GraphWalker {

    private Node start, end;

    public GraphWalker(Graph graph) throws WalkerException {
        try {
            start = graph.getStartNode();
            end = graph.getEndNode();
        } catch (Exception ex) {
            throw new WalkerException();
        }

        if (!graph.isStart(start)) {
            throw new WalkerException();
        }
    }   
    
    private boolean checkNextChar(String str, int index, Node n, HashSet<Integer> epsset){
        if(str.length() == index && n.getOutgoing().getNodeSet().contains(end)) {
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
        return checkNextChar(str, 0, (Node)start, epsset);        
    }
}