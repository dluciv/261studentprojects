/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.representations.nfa;

import j2se.g261.eda.automator.representations.*;
import java.util.HashSet;
import java.util.Vector;

/**
 *
 * @author Anastasiya, Dmitry
 */
public class NFANode extends Node<Character>{
    
//    private char name;
//    private int number;
//    private Vector<NFANode> outgoing;
//    private Vector<NFANode> incoming;
    private static final Character EPSILON = Character.valueOf('\r');
    private static final char START = Character.valueOf('\t');
    private static final char END = Character.valueOf('\n');
    private static int index = 2;

    public NFANode(char name) {
        super(name, nextIndex());
    }

    protected NFANode(Character name, int number){
        super(name, number);
    }
    
//    private NFANode(char name, int number){
//        this.name = name;
//        this.number = number;
//        this.outgoing = new Vector<NFANode>();
//        this.incoming = new Vector<NFANode>();
//    }

//    public int getNumber() {
//        return number;
//    }
//
//    public void setNumber(int number) {
//        this.number = number;
//    }
//

//
//    public char getName() {
//        return name;
//    }
//
//    public void setName(char name) {
//        this.name = name;
//    }

    
//    public void addIncomingNode(NFANode n) {
//        if(!incoming.contains(n))
//        incoming.add(n);
//    }
//    
//    public void addOutgoingNode(NFANode n) {
//        if(!outgoing.contains(n))
//        outgoing.add(n);
//    }
//    
    public void prepareForDeleting() {
        
        for (Node<Character> node : outgoing) {
            node.removeNodeFromIncoming(this);
        }
        
        
        for (Node<Character> node : incoming) {
            node.removeNodeFromOutgoing(this);
            for (Node<Character> node1 : outgoing) {
                node.addOutgoingNode(node1);
                node1.addIncomingNode(node);
            }
        }
        
        
    }

//    public void removeNodeFromIncoming(NFANode n) {
//        incoming.remove(n);
//    }
//
//    public void removeNodeFromOutgoing(NFANode n) {
//        outgoing.remove(n);
//    }

    @Override
    public String toString() {
        
        String s = "Node: " + toWellName(name) + getNumber() + "\n";

        for (Node<Character> node : incoming) {
            s += toWellName(node.getName()) + node.getNumber() + "--->\n";
        }
        
        for (Node<Character> node : outgoing) {
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
//    public boolean haveIncoming(){
//        return incoming.size() != 0;
//    }
//    
//    public boolean haveOutgoing(){
//        return outgoing.size() != 0;
//    }
//        
//    
    public static NFANode startNode(){
        return new NFANode(START, 0);
    }
    
    public static boolean isStartNode(NFANode n){
        return n.getNumber() == 0;
    }

    public static NFANode endNode(){
        return new NFANode(END, 1);
    }
    
    public static boolean isEndNode(NFANode n){
        return n.getNumber() == 1;
    }

    public static NFANode epsilonNode(){
        return new NFANode(EPSILON);
    }
    
    public static boolean isEpsilonNode(NFANode n){
        return n.getName() == EPSILON;
    }
    
//    public int getOutgoingSize(){
//        return outgoing.size();
//    }
//    
//    public int getIncomingSize(){
//        return incoming.size();
//    }
//    
//    public NFANode getIncomingAt(int index){
//        return (NFANode)incoming.get(index);
//    }
//    
//    public NFANode getOutgoingAt(int index){
//        return (NFANode)outgoing.get(index);
//    }
//    
    private static int nextIndex(){
        return index++;        
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final NFANode other = (NFANode) obj;
//        if (this.number != other.number) {
//            return false;
//        }
//        return true;
//    }
    
    void shiftConnections(NFANode to){
        for (Node<Character> node : incoming) {
            to.addIncomingNode(node);
        }
        for (Node<Character> node : outgoing) {
            to.addOutgoingNode(node);
        }
    }

    //@todo remove debug method (Character.toUpperCase)
    public NFANode cloneWithoutConnections(){
        if(isEndNode(this) || isStartNode(this)){
            return new NFANode(getName(), getNumber());
        }else{
            return new NFANode(Character.toUpperCase(getName()), getNumber());
        }
    }
}

