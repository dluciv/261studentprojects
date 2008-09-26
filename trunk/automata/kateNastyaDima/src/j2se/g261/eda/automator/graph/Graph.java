/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.graph;

import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author Anastasiya, Dmitry
 */
public class Graph {

    private Vector<Node> starts;
    private Vector<Node> ends;
    private Vector<Node> all;

    public Graph() {
        starts = new Vector<Node>();
        ends = new Vector<Node>();
        all = new Vector<Node>();
    }

    public void addNode(Node n) {
        if (!all.contains(n)) {
            all.add(n);
        }

        int num = n.getOutgoingSize();
        
        for (int i = 0; i < num; i++) {
            Node n1 = n.getOutgoingAt(i);
            if (!all.contains(n1)) {
                addNode(n1);
            }
        }
    }

    public Node addNode(char name) {
        Node n = new Node(name);
        all.add(n);
        return n;
    }

    public void deleteNode(Node n) {
        n.prepareForDeleting();
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

    public static Graph getEmpty() {
        Graph g = new Graph();
        g.addNode(Node.getEpsilonNode());
        g.markAllEnds();
        g.markAllStarts();
        return g;
    }
    

    public void markAllStarts() {
        starts.removeAllElements();
        Iterator<Node> it = all.iterator();

        while (it.hasNext()) {
            Node n = it.next();
            if (!n.haveIncoming()) {
                starts.add(n);
            }
        }
    }

    public void markAllEnds(){
        ends.removeAllElements();
        Iterator<Node> it = all.iterator();

        while (it.hasNext()) {
            Node n = it.next();
            if (!n.haveOutgoing()) {
                ends.add(n);
            }
        }
        
    }
    
    public boolean isStart(Node n){
        return (starts.contains(n));
    }
    
    public int startsSize(){
        return starts.size();
    }
    
    public int endsSize(){
        return ends.size();
    }
    
    public int allSize(){
        return all.size();
    }
    
    public Node getNodeFromStartsAt(int index){
        return starts.get(index);
    }
    
    public Node getNodeFromEndsAt(int index){
        return ends.get(index);
    }
    
    public Node getNodeFromAllAt(int index){
        return all.get(index);
    }
    
    public int getNodeIndex(Node n){
        for (int i = 0; i < all.size(); i++) {
            if(all.get(i) == n){
                return i;
            }
        }
        return -1;
    }

}
