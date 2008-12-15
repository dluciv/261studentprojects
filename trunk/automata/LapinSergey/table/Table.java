/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package table;

import graph.Graph;
import Node.Node;
import Node.Arc;
import java.util.ArrayList;
import java.util.HashSet;

public class Table {
    private ArrayList<TableElement> all;
    private ArrayList<Character> symbols;
    private TableElement start, end;
    
    public Table(Graph g)
    {
        all = new ArrayList<TableElement>();
        symbols = new ArrayList<Character>();
        Node nstart = (Node)g.getStartNode();
        Node nend = (Node)g.getEndNode();
        start = new TableElement(g.getNodeIndex(nstart), nstart, g);
        end = new TableElement(g.getNodeIndex(nend), nend, g);
        for(Node node : g.getAll())
        {            
            Node n = node;
            all.add(new TableElement(g.getNodeIndex(n), n, g));
            HashSet<Character> symbset = new HashSet<Character>();
            for(Arc tmp : node.getAll().set)
                symbset.add(tmp.symbol);
            for(Character c : symbset)
                if(!symbols.contains(c) && c != Arc.EPSILON)
                    symbols.add(c);          
        }
    }
    
    public TableElement getStart()
    {
        return start;
    }
    
    public TableElement getEnd()
    {
        return end;
    }
            
    
    public TableElement getByCondition(Integer condition)
    {        
        for(TableElement tmp : all)
            if(tmp.getCondition() == condition)
                return tmp;
        return null;
    }
    
    public ArrayList<TableElement> getAll()
    {
        return all;
    }
    
    @Override
    public String toString() {
        String s = "Table: " + all.size() + " table elements\n";
        s += "Start:" + start + "\n";
        s += "End:" + end + "\n";
        
        s += " " + symbols.toString() + "\n";
        for(TableElement elem : all)
        {
            s += elem.getCondition() + " ";                
            for(Character symb : symbols)
                s += elem.getOutgoing().get(symb) + " ";            
            s += "\n";
        }        
        return s;
    }   
}