/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package parser;
import table.Table;
import table.TableElement;
import graph.Graph;
import graph.GraphWalker;
import graph.WalkerException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lapin
 */
public class ParserMatcher {
    
    private static boolean TableMatchVisit(Table table, TableElement t, String str, int index)
    {
        if(str.length() == index && t.getOutgoing().containsKey('\n'))
            return true;
        if(index < str.length())
        {
            boolean res = false;
            if(t.getOutgoing().containsKey(str.charAt(index)))
                for(Integer tmp : t.getOutgoing().get(str.charAt(index)))
                    res = res || TableMatchVisit(table, table.getByCondition(tmp), str, index + 1);
                return res;
        }
        return false;
    }
    
    public static boolean TableMatch(Table table, String str)
    {
        TableElement tmp = table.getStart();
        return TableMatchVisit(table, tmp, str, 0);            
    }
    
    public static boolean GraphMatch(Graph graph, String str)
    {
        try {
            GraphWalker walker = new GraphWalker(graph);
            return walker.checkString(str);
           
        } catch (WalkerException ex) {
            Logger.getLogger(PatternParser.class.getName()).log(Level.SEVERE, null, ex);       
        }
        return false;
    }
}
