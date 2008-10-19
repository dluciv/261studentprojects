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
    private int number;
    private Vector<Node> outgoing;
    private Vector<Node> incoming;
    private static final char EPSILON = '\r';
    private static final char START = '\t';
    private static final char END = '\n';
    private static int index = 2;

    public Node(char name) {
        this.name = name;
        this.outgoing = new Vector<Node>();
        this.incoming = new Vector<Node>();
        number = nextIndex();
    }
    
    private Node(char name, int number){
        this.name = name;
        this.number = number;
        this.outgoing = new Vector<Node>();
        this.incoming = new Vector<Node>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
        
        String s = "Node: " + toWellName(name) + getNumber() + "\n";

        for (Node node : incoming) {
            s += toWellName(node.getName()) + node.getNumber() + "--->\n";
        }
        
        for (Node node : outgoing) {
            s +="    ---->" + toWellName(node.getName()) + node.getNumber() + "\n";
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
        
    
    public static Node startNode(){
        return new Node(START, 0);
    }
    
    public static boolean isStartNode(Node n){
        return n.getNumber() == 0;
    }

    public static Node endNode(){
        return new Node(END, 1);
    }
    
    public static boolean isEndNode(Node n){
        return n.getNumber() == 1;
    }

    public static Node epsilonNode(){
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
        return (Node)incoming.get(index);
    }
    
    public Node getOutgoingAt(int index){
        return (Node)outgoing.get(index);
    }
    
    private static int nextIndex(){
        return index++;        
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
        if (this.number != other.number) {
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

    //@todo remove debug method (Character.toUpperCase)
    public Node cloneWithoutConnections(){
        if(isEndNode(this) || isStartNode(this)){
            return new Node(getName(), getNumber());
        }else{
            return new Node(Character.toUpperCase(getName()), getNumber());
        }
    }
}

