/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.graph;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author nastya
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

        Iterator<Node> it = n.getOutcomingIterator();
        while (it.hasNext()) {
            Node n1 = it.next();
            if (!all.contains(n1)) {
                addNode(n1);
            }
        }
    }

    public Node addNode(String name) {
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

    public Iterator<Node> iteratorEnds(){
        return new SafeIterator<Node>(ends);
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
    public Iterator<Node> iteratorStarts() {
        return new SafeIterator<Node>(starts);
        
    }
    public Iterator<Node> iteratorAll() {
        return new SafeIterator<Node>(all);
    }
    
    public boolean isStart(Node n){
        return (starts.contains(n));
    }
}
