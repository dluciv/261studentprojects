package graph;

import Node.Node;
import Node.Condition;
import Node.Arc;
import Node.ArcSet;
import java.util.HashSet;
import java.util.Iterator;
import table.Table;

public class Graph{

    private HashSet<Node> starts;
    private HashSet<Node> ends;
    private HashSet<Node> all;
    
//    public Graph(Node root) {
//        starts = new HashSet<Node>();
//        ends = new HashSet<Node>();
//        all = new HashSet<Node>();
//        starts.add(root);
//        ends.add(root);
//        all.add(root);                
//    }
    public Graph() {
        starts = new HashSet<Node>();
        ends = new HashSet<Node>();
        all = new HashSet<Node>();        
    }
    
    public void markStart(Node n)
    {
        if(all.contains(n))
            starts.add(n);
    }
    
    public void markEnd(Node n)
    {
        if(all.contains(n))
            ends.add(n);
    }
    
    public void normalize()
    {
        HashSet<Node> forDelete = new HashSet<Node>();
        for(Node tmp : all){
            if(starts.contains(tmp) || ends.contains(tmp))
                continue;
            ArcSet all = new ArcSet();
            all.addArcSet(tmp.getIncoming());
            all.addArcSet(tmp.getOutgoing());
            if(all.getBySymbol(Arc.EPSILON).set.size() == all.set.size()){                
                forDelete.add(tmp);            
            }
        }
        
        for(Node tmp : forDelete){
            deleteNode(tmp);
        }
        verifyCondition();
    }
    
    public static Graph getEmptyGraph()
    {
        Graph res = new Graph();
        Node start = new Node(0);    
        Node end = new Node(0);
        res.addOneNode(start);
        res.addOneNode(end);
        res.markStart(start);
        res.markEnd(end);
        start.addOutgoingNode(end, Arc.EPSILON);
        end.addIncomingNode(start, Arc.EPSILON);
        return res;
    }
    
    public Node getStartNode()
    {
        if(starts.size() == 1)
            return (Node)(starts.toArray())[0];
        if(all.size() == 0)
            return null;      
        
        Node start = new Node(0);
        for(Node tmp : starts)
        {
            tmp.addIncomingNode(start, Arc.EPSILON);
            start.addOutgoingNode(tmp, Arc.EPSILON);
        }
        return start;
    }
    
    public Node getEndNode()
    {
        if(ends.size() == 1)
            return (Node)(ends.toArray())[0];
        if(all.size() == 0)
            return null;
        
        Node end = new Node(all.size());
        for(Node tmp : ends)
        {
            end.addIncomingNode(tmp, Arc.EPSILON);
            tmp.addOutgoingNode(end, Arc.EPSILON);
        }
        return end;
    }
    
    public HashSet<Node> getStarts() {
        return starts;
    }
    public HashSet<Node> getEnds() {
        return ends;
    }
    public HashSet<Node> getAll() {
        return all;
    }   

    public void addOneNode(Node n) {
        if (!all.contains(n)) {
            all.add(n);            
        }        
    }  
    
    public void addNode (Node n) {
        if (!all.contains(n)) {
            all.add(n);            
        }

        for (Node n1 : n.getOutgoing().getNodeSet()) {
            if (!all.contains(n1)) {
                addNode(n1);
            }
        }
    }

    public void deleteNode(Node n) {
        n.prepareForDeleting();
        all.remove(n);
        starts.remove(n);
        ends.remove(n);
    }    
    public void simpleDeleteNode(Node n){
        all.remove(n);
        starts.remove(n);
        ends.remove(n);
    }

    @Override
    public String toString() {
        String s = "Graph: " + all.size() + "nodes\n";

        Iterator<Node> i = all.iterator();

        while (i.hasNext()) {
            s += i.next().toString();
            s += "\n";
        }

        return s;
    }

//    void markAllStarts() {
//        starts.removeAll(starts);
//        for (Node abstractNode : all) {
//            if (!abstractNode.haveIncoming()) {
//                starts.add(abstractNode);
//            }
//        }
//    }
//    void markAllEnds() {
//
//        ends.removeAll(ends);
//
//        for (abstractNode abstractNode : all) {            
//            if (!abstractNode.haveOutgoing()) {
//                ends.add(abstractNode);
//            }
//        }
//    }       
    
    boolean isStart(Node n) {
        return (starts.contains(n));
    }        
    boolean isEnd(Node n) {
        return ends.contains(n);
    }
    
    public int startsSize() {
        return starts.size();
    }
    int endsSize() {
        return ends.size();
    }
    int allSize() {
        return all.size();
    }
    
    Node getNodeFromAllAt(int index) {
        return (Node)(all.toArray())[index];
    }
    
    public int getNodeIndex(Node n) {
        for (int i = 0; i < all.size(); i++) {
            if (all.toArray()[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public void verifyCondition() {
        int i = 0;
        for (Node n : all){
            n.setCondition(i);
            i++;
        }        
    }
    
    public Node getByCondition(Condition tmp) {
        Node res = null;
        for (Node n : all){
            if(n.condition.equal(tmp))
                return n;
        }
        return res;
    }
}