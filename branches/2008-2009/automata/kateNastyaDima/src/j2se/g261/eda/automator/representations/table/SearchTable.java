/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.representations.table;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 *
 * @author nastya
 */
public class SearchTable {
    
    private HashMap<StateTransition, Vector<Integer>> content;
    
    public SearchTable(Table table){
        content = new HashMap<StateTransition, Vector<Integer>>();
        ConcurrentSkipListSet<Table.Entry<Integer, TableRecord>> list = table.listOfRecords();
        for (Table.Entry<Integer, TableRecord> entry : list) {
            int state = entry.getKey();
            TableRecord record = entry.getValue();
            Set<Character> set = record.keySet();
            
            for (Character character : set) {
                StateTransition transition = new StateTransition(state, character);
                content.put(transition, record.getStateSet(character));
            }
        }
    }
    
    public Vector<Integer> getStateSet(int state, char symbol){
        return content.get(new StateTransition(state, symbol));
    }

}
