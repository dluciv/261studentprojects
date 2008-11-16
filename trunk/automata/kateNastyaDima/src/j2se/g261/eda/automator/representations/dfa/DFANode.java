/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.representations.dfa;

import j2se.g261.eda.automator.representations.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;
import java.util.Iterator;
import java.util.Map.Entry;


/**
 *
 * @author nastya
 */
public class DFANode extends Node<String>{
    private static int index = 0;
    Vector<Integer> nodeNumbers;
    HashMap<Character, DFANode> mapOutgoing;
    HashMap< DFANode, Character> mapIncoming;


    public DFANode(String name, Vector<Integer> numbers){
        this(name, nextIndex());
        nodeNumbers = numbers;
    }
    
    protected DFANode(String name, int number){
        super(name, number);
        mapOutgoing = new HashMap<Character, DFANode>();
        mapIncoming = new HashMap<DFANode,Character>();
        nodeNumbers = new Vector<Integer>();
    }
    
    public int getMapOutgoingSize(){
        return this.mapOutgoing.size();
    }
    
    public int getMapIncomingSize(){
        return this.mapIncoming.size();
    }
    
    
    
    public Iterator<Entry<Character, DFANode>> getOutgoingIterator(){
        return mapOutgoing.entrySet().iterator();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DFANode other = (DFANode) obj;
        if (this.nodeNumbers != other.nodeNumbers && (this.nodeNumbers == null || !this.nodeNumbers.equals(other.nodeNumbers))) {
            return false;
        }
        return true;
    }
    
        private static int nextIndex(){
        return index++;        
    }
        
        

        public void mapToSymbolOutgoing(DFANode node, char symbol){
            addOutgoingNode(node);
            mapOutgoing.put(symbol, node);
        }
        public void mapToSymbolIncoming(DFANode node, char symbol){
            addIncomingNode(node);
            mapIncoming.put(node, symbol);
        }

}
