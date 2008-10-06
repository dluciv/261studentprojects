/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.table;

import j2se.g261.eda.automator.graph.Graph;
import j2se.g261.eda.automator.graph.Node;
import java.util.Vector;

/**
 *
 * @author dmitriy
 */
public class TableWalker {

    private Graph graph;
    private Table table;
    private int startNumber;

    public TableWalker(Graph graph, Table table) {
        this.graph = graph;
        this.table = table;
        startNumber = graph.getNodeIndex(Node.getStartNode());
    }
    
   public boolean check(String s){
       return checkNextSymbol(s,startNumber);
   }
    
   private boolean checkNextSymbol(String rest, int state){
       if(rest.length() == 0){
           if(table.getStateSet(state, TableRecord.SYMBOL_END).size() == 0){
               return false;
           }
            return true;   
       }
       Vector<Integer> states = table.getStateSet(state, rest.charAt(0));
       if(states == null){
           return false;
       }
       for (Integer st : states) {
           if(checkNextSymbol(rest.substring(1), st)){
            return true;   
           }
       }
       return false;
      
   }

}
