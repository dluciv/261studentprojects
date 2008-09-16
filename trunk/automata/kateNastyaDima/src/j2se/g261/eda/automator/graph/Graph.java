/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.graph;

import java.util.Iterator;
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
    
    public Node addNode(String name){
        Node n = new Node(name);
        all.add(n);
        return n;
    }
    
    public Node addNode(String name, boolean start, boolean end){
        Node n = new Node(name, start, end);
        all.add(n);
        
        if(start){
            starts.add(n);
        }
        
        if(end){
            ends.add(n);
        }
        return n;
    }
    
    public void deleteNode(Node n){
        n.prepareForDeleting();
        all.remove(n);
        starts.remove(n);
        ends.remove(n);
    }

    @Override
    public String toString() {
        String s = "Graph:\n";
        
        Iterator<Node> i = all.iterator();
        
        while(i.hasNext()){
            s += i.next().toString();
            s += "\n";
        }
        
        return s;
    }

    public static Graph getEmpty(){
        Graph g = new Graph();
        g.addNode("");
        return g;
    }
    
}
