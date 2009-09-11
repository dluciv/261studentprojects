/**
 *
 * Lapin Sergey 261 group mat-mex
 * Regular expression analysis
 * 19.01.2009
 */

package table;

import Node.Node;
import Node.Arc;
import graph.*;
import java.util.HashMap;
import java.util.HashSet;

public class TableElement {
    private Integer condition;
    private HashMap<Character, HashSet<Integer>> outgoing; 
        
    public TableElement(Integer condition, HashMap<Character, HashSet<Integer>> outgoing){
        this.condition = condition;
        this.outgoing = outgoing;
    }
    
    public TableElement(Integer condition, Node node, Graph g){
        this.condition = condition;
        this.outgoing = new HashMap<Character, HashSet<Integer>>();
        for(Arc tmp : node.getOutgoing().set)
        {            
            if(!outgoing.containsKey(tmp.symbol))
            {
                HashSet n = new HashSet<Integer>();
                outgoing.put(tmp.symbol, n);
            }
            HashSet<Integer> tmpr = outgoing.get(tmp.symbol);
            tmpr.add(g.getNodeIndex(tmp.vertex));
            outgoing.put(tmp.symbol, tmpr);
        }
    }
    
    public HashMap<Character, HashSet<Integer>> getOutgoing()
    {
        return outgoing;
    }
    
    public Integer getCondition()
    {
        return condition;
    }
    
    public void setOutgoing(HashMap<Character, HashSet<Integer>> outgoing)
    {
        this.outgoing = outgoing;
    }
    
    public void setCondition(Integer condition)
    {
        this.condition = condition;
    }
    
    @Override
    public String toString() {
        String s = "TableElement:\n" + "Condition:" + condition + "\n";
        for(Character c : outgoing.keySet())
        {
            s += "by symbol:" + c + " to condition:\n";
            for(Integer tmp : outgoing.get(c))
                s += tmp + " ";
            s += "\n";
        }        
        return s;
    }
}