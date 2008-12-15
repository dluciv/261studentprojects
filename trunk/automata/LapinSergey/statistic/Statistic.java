/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package statistic;

import graph.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author lapin
 */
public class Statistic {
    private HashMap<String, HashSet<StatEntry>> table;
    
    public Statistic()
    {
        table = new HashMap<String, HashSet<StatEntry>>();
    }
    
    public void addEntry(String str, StatEntry entry)
    {
        HashSet<StatEntry> tmp;
        if(table.containsKey(str))
           tmp = table.get(str);
        else
           tmp = new HashSet(); 
        
       tmp.add(entry);
       table.put(str, tmp);
       return;
    }
    
    public long calcSummaryTimeOnEntry(String str) {    
        if(!table.containsKey(str))
            return -1;
        HashSet<StatEntry> entriesSet = table.get(str);
        long res = 0;
        for(StatEntry tmpr : entriesSet)
            res += tmpr.getTimeofCheck();
        return res;
    }
    
    /*public int CheckAnswersOnEntry(Ha str) {    
        if(!table.containsKey(str))
            return -1;
        HashSet<StatEntry> entriesSet = table.get(str);
        int res = -1;
        boolean answer = true;
        for(StatEntry tmpr : entriesSet)
        {
            if(res == -1)
            {
                answer = tmpr.getAnswer();
                res = 1;
                continue;
            }
            if(answer != tmpr.getAnswer())
            {
                res = 0;
                break;
            }
        }
        return res;
    }
    
    public boolean getAnswersOnEntry(String str) {            
        HashSet<StatEntry> entriesSet = table.get(str);
        Iterator<StatEntry> tmp  = entriesSet.iterator();
        return tmp.next().getAnswer();
    }*/
    
    @Override
    public String toString() {    
        Set<String> keySet = table.keySet();
        String s = new String();
        for(String tmp : keySet)
        {
            s += "Statistic of " + tmp + "\n";
            for(StatEntry entry : table.get(tmp))
                s += entry + "\n";
        }        
        return s;
    }
}
