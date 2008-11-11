/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.representations.dfa;

import j2se.g261.eda.automator.representations.*;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author nastya
 */
public class DFANode extends Node<String>{
    private static int index = 0;
    Vector<Integer> nodeNumbers;
    HashMap<Character, DFANode> map;


    public DFANode(String name, Vector<Integer> numbers){
        this(name, nextIndex());
        nodeNumbers = numbers;
    }
    
    protected DFANode(String name, int number){
        super(name, number);
        map = new HashMap<Character, DFANode>();
        nodeNumbers = new Vector<Integer>();
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


    public void mapToSymbol(DFANode node, char symbol){
        addOutgoingNode(node);
        map.put(symbol, node);
    }

}
