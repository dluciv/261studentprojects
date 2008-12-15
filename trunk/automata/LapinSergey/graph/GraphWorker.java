package graph;

import Node.Arc;
import Node.Node;
import Node.Condition;
import java.util.ArrayList;
import java.util.Vector;
import java.util.HashSet;

public class GraphWorker {

    private GraphWorker() {
    }

    public static Graph concatenateOR(Graph g1, Graph g2) {
        if (g1 == null) {
            return g2;
        }
        if (g2 == null) {
            return g1;
        }
        
        Graph res = new Graph();
        
        Node start1 = g1.getStartNode();
        Node start2 = g2.getStartNode();
        Node end1 = g1.getEndNode();
        Node end2 = g2.getEndNode();
        res.addNode(start1);
        res.addNode(start2);
        res.markStart(start1);
        res.markStart(start2);
        res.markEnd(end1);
        res.markEnd(end2);
        Node start = res.getStartNode();
        res.addNode(start);        
        res.getStarts().clear();
        Node end = res.getEndNode();
        res.addNode(end);
        res.getEnds().clear();
        res.markStart(start);
        res.markEnd(end);
        
        return res;
    }

    public static Graph concatenateONE(Graph g) {
        Graph res = concatenateOR(g, Graph.getEmptyGraph());
        res.normalize();
        return res;
    }

    public static Graph concatanateANY(Graph g) {
        if (g == null) {
            return null;
        }
        
        Graph res = new Graph();
        Node start = g.getStartNode();
        res.addNode(start);
        Node end = g.getEndNode();
        res.addNode(end);
        end.addOutgoingNode(start, Arc.EPSILON);
        start.addIncomingNode(end, Arc.EPSILON);
        res.markStart(start);
        res.markEnd(end);
        
        res = concatenateONE(res);
        res.normalize();        
        return res;        
    }

    public static Graph concatanateAND(Graph g1, Graph g2) {
        if (g1 == null) {
            return g2;
        }
        if (g2 == null) {
            return g1;
        }
        
        Node start1 = g1.getStartNode();
        Node end1 = g1.getEndNode();
        Node start2 = g2.getStartNode();
        Node end2 = g2.getEndNode();                
        
        Graph res = g1;
        res.addNode(start2);
        end1.addOutgoingNode(start2, Arc.EPSILON);
        start2.addIncomingNode(end1, Arc.EPSILON);
        
        res.getEnds().remove(end1);
        res.markEnd(end2);       
        res.normalize();
        return res;                 
    }
    
//    public static void normalize(Graph g){
//        
//        Node start = Node.getStartNode();
//        Node end = Node.getEndNode();
//        Vector<Node> toDelete = new Vector<Node>();
//        
//        g.addNode(start);
//        g.addNode(end);
//
//        for(abstractNode n : g.getStarts())
//        {            
//            n.addIncomingNode(start);
//            start.addOutgoingNode(n);
//        }
//        
//        for(abstractNode n : g.getEnds())
//        {            
//            n.addOutgoingNode(end);
//            end.addIncomingNode(n);
//        }
//        
//        g.markAllStarts();
//        g.markAllEnds();       
//        
//        for(abstractNode n : g.getAll())
//        {            
//            if(Node.isStartNode((Node)n) && !g.getStarts().contains(n) ||
//               Node.isEndNode((Node)n) && !g.getEnds().contains(n))
//            {
//                toDelete.add((Node)n);                
//            }                     
//        }
//        
//        for (Node node : toDelete) {
//            g.deleteNode(node);
//        }
//        makeClosure(g);
//    }

//    private static void makeClosure(Graph g) {
//        HashSet<Node> toDelete = new HashSet<Node>();        
//        
//        for(Node n : g.getAll())
//        {            
//            if(Node.isEpsilonNode(n))
//            {
//                toDelete.add((Node)n);                
//            }                     
//        }
//        
//        for (Node node : toDelete) {
//            g.deleteNode(node);
//        }
//    }

    private static Node getEqualed(HashSet<Node> v, Node n) {
        for (Node node : v) {
            if (node.equals(n)) {
                return node;
            }
        }
        return null;
    }
    
    private static Node getFirstBySymbol(HashSet<Arc> v, Character ch) {
        for (Arc tmp : v) {
            if(tmp.symbol.equals(ch))
                return tmp.vertex;
        }
        return null;
    }
    
//    private static boolean deleteBySymbol(Node n, Graph g, HashSet<Node> forDelete) {
//        boolean res = false;
//        for(Node tmp : n.getOutgoing())
//            if(tmp.getName() == n.getName()  && !tmp.equals(n)){
//                res = true;
//                forDelete.add((Node)tmp);                
//            }
//        return res;
//    }
    
    private static HashSet<Node> epsilonClosure(HashSet<Node> r) {
        if(r.size() == 0)
            return r;
        HashSet<Node> res = new HashSet();
        for(Node tmp : r) {
            res.addAll(tmp.getOutgoing().getBySymbol(Arc.EPSILON).getNodeSet());
            res.addAll(epsilonClosure(res));             
        }
        return res;         
    }
    
    private static HashSet<Node> move(HashSet<Node> r, Character a) {
        if(r.size() == 0)
            return r;
        HashSet<Node> res = new HashSet();
        for(Node tmp : r) {
            res.addAll(tmp.getOutgoing().getBySymbol(a).getNodeSet());             
        }
        return res;         
    }
    
    public static HashSet<Character> makeAlphabet(Graph g) {
        HashSet<Character> res = new HashSet<Character>();
        for(Node tmp : g.getAll()) {
            res.addAll(tmp.getOutgoing().getSymbolSet());
        }
        res.remove(Arc.EPSILON);
        return res;         
    }
    
    public static Condition makeConditionFromSetNode(HashSet<Node> setNode) {
        Condition res = new Condition();
        for(Node tmp : setNode) {
            res.mergeCondition(tmp.condition);
        }        
        return res;         
    }
    
    public static Graph makeDeterministic(Graph g) {
        Graph res = new Graph();        
        HashSet<Character> alphabet = makeAlphabet(g);
        HashSet<HashSet<Node>> qMark = new HashSet<HashSet<Node>>();
        HashSet<Node> qUnMark = new HashSet<Node>();
        Node term;
        
        HashSet<Node> q0 = epsilonClosure(g.getStarts());
        qUnMark.addAll(q0);   
        Node start = new Node(makeConditionFromSetNode(qUnMark));
        res.addNode(start);
        term = start;
        res.markStart(start);
        
        while (!qUnMark.isEmpty()){
            qMark.add(qUnMark);
            Node r = res.getByCondition(makeConditionFromSetNode(qUnMark));
            term = r;
            HashSet<Node> tmpr = (HashSet<Node>)qUnMark.clone();
            qUnMark.clear();
            for (Character tmp : alphabet){                    
                    HashSet<Node> S = epsilonClosure(move(tmpr, tmp));
                    if(S.isEmpty()){
                        S = move(tmpr, tmp);
                    }
                    if (!S.isEmpty()){
                        Node s = res.getByCondition(makeConditionFromSetNode(S));                                                   
                        if (s == null || (res.isStart(s) && s.haveIncoming())) {
                                
                            qUnMark.addAll(S);
                            Node s1 = new Node(makeConditionFromSetNode(qUnMark));
                                            
                            r.addOutgoingNode(s1, tmp);
                            s1.addIncomingNode(r, tmp);
                            res.addNode(s1);
                            
                            continue;
                        }
                        r.addOutgoingNode(s, tmp);
                        s.addIncomingNode(r, tmp);
                        res.addNode(s);
                        
                }  
            }
        }
        res.markEnd(term);
        
        res.verifyCondition();
        System.out.println(res);
        return res;
    }
}
