/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.representations.dfa;

import j2se.g261.eda.automator.representations.*;
import java.util.HashMap;
import java.util.Vector;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 *
 * @author nastya
 */
public class DFANode extends Node<String> {

    private static int index = 1;
    Vector<Integer> nodeNumbers;
    HashMap<Character, DFANode> mapOutgoing;
    HashMap<DFANode, Character> mapIncoming;
    public static final int NUMBER_END = 0;
    public static final String NAME_END = "END";

    public DFANode(String name, Vector<Integer> numbers) {
        this(name, nextIndex());
        nodeNumbers = numbers;
    }

    protected DFANode(String name, int number) {
        super(name, number);
        mapOutgoing = new HashMap<Character, DFANode>();
        mapIncoming = new HashMap<DFANode, Character>();
        nodeNumbers = new Vector<Integer>();
    }

    public int getMapOutgoingSize() {
        return this.mapOutgoing.size();
    }

    public int getMapIncomingSize() {
        return this.mapIncoming.size();
    }

    public DFANode getNextNode(char symbol){
        return mapOutgoing.get(symbol);
    }
    
    public Iterator<Entry<Character, DFANode>> getOutgoingIterator() {
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

    private static int nextIndex() {
        return index++;
    }

    public void mapToSymbolOutgoing(DFANode node, char symbol) {
        addOutgoingNode(node);
        mapOutgoing.put(symbol, node);
    }

    public void mapToSymbolIncoming(DFANode node, char symbol) {
        addIncomingNode(node);
        mapIncoming.put(node, symbol);
    }

    @Override
    public String toString() {
        String result = "";
        result += "------------------------------------------------------\n";
        result += "DFANode " + name + ": " + number + "[i: " + incoming.size() + ",o: " + outgoing.size() + "]";
        result += "\n" + nodeNumbers + "\n";
        result += "\n INCOMING: \n";
        Iterator<Entry<DFANode, Character>> i = mapIncoming.entrySet().iterator();
        while (i.hasNext()) {
            Entry<DFANode, Character> e = i.next();
            result += e.getKey().nodeNumbers + " - " + e.getValue() + "\n";
        }
        result += "\n OUTGOING: \n";
        Iterator<Entry<Character, DFANode>> h = mapOutgoing.entrySet().iterator();
        while (h.hasNext()) {
            Entry<Character, DFANode> e = h.next();
            result += e.getKey() + " - " + e.getValue().nodeNumbers + "\n";
        }
        result += "------------------------------------------------------";

        return result;
    }

    public static DFANode createEndNode() {
        return new DFANode(NAME_END, NUMBER_END);

    }
}
