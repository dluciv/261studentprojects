/**
 *
 * Lapin Sergey 261 group mat-mex
 * Regular expression analysis
 * 19.01.2009
 */

package graph;

import Node.Arc;
import Node.Node;
import Node.Condition;
import java.util.HashSet;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class GraphWorker {
    private static Graph current;
    private static HashSet<Character> alphabet;

    private GraphWorker() {
        current = null;
        alphabet = null;
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
    
    public static HashSet<Node> epsilonClosure(HashSet<Node> r) {
        if(r.size() == 0)
            return r;
        HashSet<Node> res = new HashSet();
        for(Node tmp : r) {
            HashSet<Node> epsset = new HashSet();
            if(tmp.getOutgoing().getBySymbol(Arc.EPSILON).set.size() == 0)
            {
                res.add(tmp);
                continue;
            }
            epsset.addAll(tmp.getOutgoing().getBySymbol(Arc.EPSILON).getNodeSet());
            res.addAll(epsilonClosure(epsset));            
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
    
    private static void addNodeSet(HashSet<HashSet<Node>> from, HashSet<HashSet<Node>> to)
    {        
        for(HashSet<Node> tmp : from)
        {
            to.add(tmp);
        }        
    }   
    
    private static HashSet<HashSet<Node>> prosseding(HashSet<HashSet<Node>> resH, Graph g)
    {        
        HashSet<HashSet<Node>> newH = new HashSet<HashSet<Node>>();
        for(HashSet<Node> tmp : resH){            
            addNodeSet(partition(tmp, resH, g), newH);
        }
        if(newH.size() == resH.size())
            return newH;
        return prosseding(newH, g);
    }
    
    
    private static HashSet<Node> getOriginalSet (Node one, HashSet<HashSet<Node>> old)
    {
        for(HashSet<Node> tmp : old)
            if(tmp.contains(one))
                return tmp;           
        return null;
    }
    
    private static boolean checkEquality (Node one, Node two, Graph g, HashSet<HashSet<Node>> old)
    {        
        boolean res = true;
        for(Character tmp : makeAlphabet(g))
        {
            HashSet<Node> tmpone = new HashSet<Node>();
            tmpone.add(one);
            HashSet<Node> tmptwo = new HashSet<Node>();
            tmptwo.add(two);
        
            HashSet<Node> resone = move(tmpone, tmp);
            HashSet<Node> restwo = move(tmptwo, tmp);
            for(HashSet<Node> tmpr : old)
                if(!tmpr.containsAll(resone) && tmpr.containsAll(restwo) ||
                    tmpr.containsAll(resone) && !tmpr.containsAll(restwo))
                    return false;
        }
        return true;       
    }
    
    private static HashSet<HashSet<Node>> partition(HashSet<Node> group, HashSet<HashSet<Node>> old, Graph g)
    {
        HashSet<HashSet<Node>> res = new HashSet<HashSet<Node>>();        
        for(Node tmp : group){
            boolean isAdd = false;
            if(!res.isEmpty())
                for(HashSet<Node> tmp1 : res){
                    if(tmp1.isEmpty())
                        continue;
                    if(checkEquality(tmp, (Node)tmp1.toArray()[0], g, old))
                    {
                        tmp1.add(tmp);                                        
                        isAdd = true;
                    }
                }
            if(isAdd)
                continue;
            HashSet<Node> newset = new HashSet<Node>();
            newset.add(tmp);
            res.add(newset);
        }
        return res;
    }    
    
    public static Graph makeMinimal(Graph g)
    {
        Graph res = new Graph();
        HashSet<Node> finite = new HashSet<Node>();
        HashSet<Node> other = new HashSet<Node>();
        for(Node tmp : g.getAll()){
            if(g.isEnd(tmp)){
                finite.add(tmp);
                continue;
            }
            other.add(tmp);            
        }
        HashSet<HashSet<Node>> H = new HashSet<HashSet<Node>>();
        H.add(finite);
        H.add(other);
        HashSet<HashSet<Node>> resH = prosseding(H, g);
        
        for(HashSet<Node> tmp : resH)
        {
            Node temp = new Node(makeConditionFromSetNode(tmp));
            res.addOneNode(temp);
            for(Node tmpr : g.getStarts())
                if(temp.condition.contains(tmpr.condition))
                {
                    res.markStart(temp);
                    break;
                }
            for(Node tmpr : g.getEnds())
                if(temp.condition.contains(tmpr.condition))
                {
                    res.markEnd(temp);
                    break;
                }            
        }
        
        for(Node tmp : res.getAll())
        {
            Condition cond = new Condition((Integer)tmp.condition.condition.toArray()[0]);
            for(Arc tmpr : g.getByCondition(cond).getOutgoing().set){
                for(Node tmp1 : res.getAll())
                    if(tmp1.condition.contains(tmpr.vertex.condition))
                    {
                        tmp1.addIncomingNode(tmp, tmpr.symbol);
                        tmp.addOutgoingNode(tmp1, tmpr.symbol);
                    }
            }                
        }
        res.simplifyConditionNames();
        return res;
    }

    public static Graph makeDeterministic(Graph g) {
        Graph res = new Graph();        
        HashSet<Condition> qUnMark = new HashSet<Condition>();
        
        HashSet<Node> q0 = epsilonClosure(g.getStarts());
        if(q0.isEmpty())
            q0.add(g.getStartNode());
        qUnMark.add(makeConditionFromSetNode(q0));   
        Node start = new Node((Condition)qUnMark.toArray()[0]);
        res.addNode(start);
        res.markStart(start);
        
        while (!qUnMark.isEmpty()){            
            Node r = res.getByCondition((Condition)qUnMark.toArray()[0]);            
            HashSet<Node> tmpr = g.getSetNodeFromCondition((Condition)qUnMark.toArray()[0]);
            qUnMark.remove((Condition)qUnMark.toArray()[0]);
            for (Character tmp : makeAlphabet(g)){                    
                    HashSet<Node> S = epsilonClosure(move(tmpr, tmp));
                    if (!S.isEmpty()){
                        Node s = res.getByCondition(makeConditionFromSetNode(S));                                                   
                        if (s == null || (res.isStart(s) && s.getIncoming().getBySymbol(tmp).set.size() != 0)) {                                
                            qUnMark.add(makeConditionFromSetNode(S));
                            Node s1 = new Node(makeConditionFromSetNode(S));
                                            
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
        
        for(Node tmp : res.getAll())
        {
            if(tmp.condition.contains(g.getEndNode().condition))            
                res.markEnd(tmp);
        }       
        
        res.simplifyConditionNames();
        return res;
    } 
    
    public static boolean fileForGraphviz (Graph g, String fileName)
    {       
        try {            
            FileWriter file = new FileWriter(fileName);
            BufferedWriter out = new BufferedWriter(file);
            
            String res = "";
            Double size = 8.5;
            res += "digraph finite_state_machine {\n";
            res += "rankdir = LR\n";
            res += "size= " + size + "\n";
            res += "node [shape = doublecircle];";
            for(Node tmp : g.getEnds())
                res += tmp.condition + " ";
            res += ";\n";
            res += "node [shape = box];" + "S_";
            for(Node tmp : g.getStarts())
                res += tmp.condition + " ";            
            res += ";\n";
            res += "node [shape = circle];\n";
            for(Node tmp : g.getAll())
            {
                for(Arc tmpr : tmp.getOutgoing().set)
                {
                    if(tmpr.symbol.equals(Arc.EPSILON)){
                        res += tmp.condition + "->" + tmpr.vertex.condition + 
                            "[label = " + "epsilon" + "];\n";
                        continue;
                    }
                    res += tmp.condition + "->" + tmpr.vertex.condition + 
                            "[label = " + tmpr.symbol + "];\n";
                }            
            }
            out.write(res);
            out.close();
            return true;            
        }
        catch (Exception ex)
        {
            return false;
        }        
    }
}
