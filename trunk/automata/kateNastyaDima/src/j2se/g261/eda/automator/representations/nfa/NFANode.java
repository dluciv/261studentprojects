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
    public static final char EPSILON = '\r';
    public  static final char START = '\t';
    public  static final char END = '\n';
    public static final int START_INDEX = 0;
    public static final int END_INDEX = 1;
    private static int index = 2;

    public NFANode(char name) {
        super(name, nextIndex());
    }

    protected NFANode(Character name, int number){
        super(name, number);
    }

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

    public static NFANode startNode(){
        return new NFANode(START, START_INDEX);
    }
    
    public static boolean isStartNode(NFANode n){
        return n.getNumber() == START_INDEX;
    }

    public static NFANode endNode(){
        return new NFANode(END, END_INDEX);
    }
    
    public static boolean isEndNode(NFANode n){
        return n.getNumber() == END_INDEX;
    }

    public static NFANode epsilonNode(){
        return new NFANode(EPSILON);
    }
    
    public static boolean isEpsilonNode(NFANode n){
        return n.getName() == EPSILON;
    }
    

    private static int nextIndex(){
        return index++;        
    }


    void shiftConnections(NFANode to){
        for (Node<Character> node : incoming) {
            to.addIncomingNode(node);
        }
        for (Node<Character> node : outgoing) {
            to.addOutgoingNode(node);
        }
    }

    public NFANode cloneWithoutConnections(){
            return new NFANode(getName(), getNumber());
    }
}

