/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.graph;

import java.util.HashSet;
import java.util.Vector;

/**
 *
 * @author Anastasiya, Dmitry
 */
public class Node {
    
    private char name;
    private HashSet<Node> outgoing;
    private HashSet<Node> incoming;
    private static final char EPSILON = '\r';
    private static final Node START = new Node('\t');
    private static final Node END = new Node('\n');

    public Node(char name) {
        this.name = name;
        this.outgoing = new HashSet<Node>();
        this.incoming = new HashSet<Node>();
    }



    public char getName() {
        return name;
    }

    public void setName(char name) {
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
        
        for (Node node : outgoing) {
            node.removeNodeFromIncoming(this);
        }
        
        
        for (Node node : incoming) {
            node.removeNodeFromOutgoing(this);
            for (Node node1 : outgoing) {
                node.addOutgoingNode(node1);
                node1.addIncomingNode(node);
            }
        }
        
        
    }

    public void removeNodeFromIncoming(Node n) {
        incoming.remove(n);
    }

    public void removeNodeFromOutgoing(Node n) {
        outgoing.remove(n);
    }

    @Override
    public String toString() {
        
        String s = "Node: " + toWellName(name) + "\n";

        for (Node node : incoming) {
            s += toWellName(node.getName()) + "--->\n";
        }
        
        for (Node node : outgoing) {
            s +="    ---->" + toWellName(node.getName()) + "\n";
        }
       
        return s;
    }

    private String toWellName(char c){
        if(c == '\r'){
            return "eps";
        }else if(c == '\t'){
            return "start";
        }else if(c == '\n'){
            return "end";
        }else{
            return String.valueOf(c);
        }
    }
    public boolean haveIncoming(){
        return incoming.size() != 0;
    }
    
    public boolean haveOutgoing(){
        return outgoing.size() != 0;
    }
        
    
    public static Node getStartNode(){
        return START;
    }
    
    public static boolean isStartNode(Node n){
        return n == START;
    }

    public static Node getEndNode(){
        return END;
    }
    
    public static boolean isEndNode(Node n){
        return n == END;
    }

    public static Node getEpsilonNode(){
        return new Node(EPSILON);
    }
    
    public static boolean isEpsilonNode(Node n){
        return n.getName() == EPSILON;
    }
    
    public int getOutgoingSize(){
        return outgoing.size();
    }
    
    public int getIncomingSize(){
        return incoming.size();
    }
    
    public Node getIncomingAt(int index){
        return (Node)(incoming.toArray())[index];
    }
    
    public Node getOutgoingAt(int index){
        return (Node)(outgoing.toArray())[index];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (this.name != other.name) {
            return false;
        }
        return true;
    }
    
    void shiftConnections(Node to){
        for (Node node : incoming) {
            to.addIncomingNode(node);
        }
        for (Node node : outgoing) {
            to.addOutgoingNode(node);
        }
    }

    
}

