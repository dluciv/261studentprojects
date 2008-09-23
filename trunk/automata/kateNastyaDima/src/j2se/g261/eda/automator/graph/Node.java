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
public class Node {
    
    private String name;
    private Vector<Node> outgoing;
    private Vector<Node> incoming;
    private static final String EPSILON = "epsilon";
    private static final String START = "start";
    private static final String END = "end";

    public Node(String name) {
        this.name = name;
        this.outgoing = new Vector<Node>();
        this.incoming = new Vector<Node>();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public void addIncomingNode(Node n) {
        if(!incoming.contains(n))
        incoming.add(n);
    }
    
    public void addOutgoingNode(Node n) {
        if(!outgoing.contains(n))
        outgoing.add(n);
    }
    
    public void prepareForDeleting() {
        
        Iterator<Node> out = outgoing.iterator();
        while (out.hasNext()) {
            out.next().removeNodeFromIncoming(this);
        }
        
        
        Iterator<Node> i = incoming.iterator();
        while (i.hasNext()) {
            Node n = i.next();
            n.removeNodeFromOutgoing(this);
            Iterator<Node> o = outgoing.iterator();
            while (o.hasNext()) {
                Node n1 = o.next();
                n.addOutgoingNode(n1);
                n1.addIncomingNode(n);
            }
            
        }
        
        incoming = null;
        outgoing = null;
        
    }

    public void removeNodeFromIncoming(Node n) {
        incoming.remove(n);
    }

    public void removeNodeFromOutgoing(Node n) {
        outgoing.remove(n);
    }

    @Override
    public String toString() {
        String s = "Node: " + name + "\n";
        Iterator<Node> it = getIncomingIterator();
        while(it.hasNext()){
            s += it.next().getName() + "--->\n";
        }
        
        it = getOutcomingIterator();
        while(it.hasNext()){
            s +="    ---->" + it.next().getName() + "\n";
        }
        
        return s;
    }

    public boolean haveIncoming(){
        return incoming.size() != 0;
    }
    
    public boolean haveOutgoing(){
        return outgoing.size() != 0;
    }
    
    public Iterator<Node> getIncomingIterator(){
        return incoming.iterator();
    }
    
    public Iterator<Node> getOutcomingIterator(){
        return outgoing.iterator();
    }
    
    
    public static Node getStartNode(){
        return new Node(START);
    }
    
    public static boolean isStartNode(Node n){
        return n.getName().equals(START);
    }

    public static Node getEndNode(){
        return new Node(END);
    }
    
    public static boolean isEndNode(Node n){
        return n.getName().equals(END);
    }

    public static Node getEpsilonNode(){
        return new Node(EPSILON);
    }
    
    public static boolean isEpsilonNode(Node n){
        return n.getName().equals(EPSILON);
    }
    
    public Iterator<Node> iteratorOutgoing(){
        return new SafeIterator<Node>(outgoing);
    }

    public Iterator<Node> iteratorIncoming(){
        return new SafeIterator<Node>(incoming);
    }

}
