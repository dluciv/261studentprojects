/**
 *
 * Lapin Sergey 261 group mat-mex
 * Regular expression analysis
 * 19.01.2009
 */

package table;

import graph.Graph;
import Node.Node;
import Node.Arc;
import parser.ParserMatcher;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.BufferedWriter;
import java.io.FileWriter;
import statistic.MathModel;


public class Table implements MathModel{
    private ArrayList<TableElement> all;
    private ArrayList<Character> symbols;
    private TableElement start;
    private HashSet<TableElement> ends;
    
    public Table(Graph g)
    {
        all = new ArrayList<TableElement>();
        symbols = new ArrayList<Character>();
        Node nstart = (Node)g.getStartNode();
        HashSet<Node> nend = g.getEnds();        
        start = new TableElement(g.getNodeIndex(nstart), nstart, g);
        ends = new HashSet<TableElement>();
        for(Node tmp : nend)
        {
            TableElement temp = new TableElement(g.getNodeIndex(tmp), tmp, g);
            ends.add(temp);
        }
        for(Node node : g.getAll())
        {            
            Node n = node;
            all.add(new TableElement(g.getNodeIndex(n), n, g));
            HashSet<Character> symbset = new HashSet<Character>();
            for(Arc tmp : node.getAll().set)
                symbset.add(tmp.symbol);
            for(Character c : symbset)
                if(!symbols.contains(c))
                    symbols.add(c);          
        }
    }
    
    public TableElement getStart()
    {
        return start;
    }
    
    public HashSet<TableElement> getEnd()
    {
        return ends;
    }
    
    public boolean Match(String str)
    {
        return ParserMatcher.TableMatch(this, str);                    
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
    
    public boolean fileForGraphviz(String fileName)
    {       
        try {            
            FileWriter file = new FileWriter(fileName);
            BufferedWriter out = new BufferedWriter(file);
            
            String res = "";
            Double size = 8.5;
            res += "digraph finite_state_machine {\n";
            res += "rankdir = LR\n";
            res += "size= " + size + "\n";
            res += "node [shape = doublecircle];";
            for(TableElement tmp : ends)
                res += tmp.getCondition() + " ";
            res += ";\n";
            res += "node [shape = box];" + "S_" + start.getCondition() + ";\n";
            res += "node [shape = circle];\n";
            for(TableElement tmp : all)
            {
                for(Character temp : symbols)
                {
                    if(!tmp.getOutgoing().containsKey(temp))
                        continue;
                    for(Integer tmpr : tmp.getOutgoing().get(temp))
                    {
                        if(temp.equals(Arc.EPSILON)){
                            res += tmp.getCondition() + "->" + tmpr + 
                                "[label = " + "epsilon" + "];\n";
                            continue;
                        }
                        res += tmp.getCondition() + "->" + tmpr + 
                                "[label = " + temp + "];\n";
                    }            
                }
            }
            out.write(res);
            out.close();
            return true;            
        }
        catch (Exception ex)
        {
            return false;
        }        
    }   
    
    @Override
    public String toString() {
        String s = "Table: " + all.size() + " table elements\n";
        s += "Start:" + start + "\n";
        s += "End:" + ends + "\n";
        
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