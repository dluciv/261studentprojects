/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package statistic;

import graph.*;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author lapin
 */
public class Statistic {
    public HashMap<String, StatEntry> table;
    private String checking, checked;
    
    public Statistic(String checking, String checked)
    {
        this.checked = checked;
        this.checking = checking;
        table = new HashMap<String, StatEntry>();
    }
    
    public String findByEntry(StatEntry entr)
    {
        for(String tmp : table.keySet())
            if(table.get(tmp).equals(entr))
                return tmp;
        return null;
    }
    
    public boolean checkAnswersEquality()
    {
        boolean isFirst = true; 
        boolean res = true;
        
        for(String tmp : table.keySet())
        {
            if(isFirst)
            {
                res = table.get(tmp).getAnswer();
                isFirst = false;
                continue;
            }
            
            if(res != table.get(tmp).getAnswer())
                return false;
        }
        return true;
    }
    
    public boolean getAnswer()
    {
        return table.get((String)table.keySet().toArray()[0]).getAnswer();
    }
    
    public String getChecking()
    {
        return checking;
    }
    
    public String getChecked()
    {
        return checked;
    }
    
    
    public void addEntry(String str, StatEntry entry)
    {
       table.put(str, entry);       
    }
    
    public SortedSet<StatEntry> SortByCapasity()
    {
        TreeSet<StatEntry> res = new TreeSet<StatEntry>(StatEntry.getCapasityComparator());        
        for(String tmp : table.keySet())
        {
            res.add(table.get(tmp));
        } 
        
        return res;
    }   
    
//    public long calcSummaryTimeOnEntry(String str) {    
//        if(!table.containsKey(str))
//            return -1;
//        HashSet<StatEntry> entriesSet = table.get(str);
//        long res = 0;
//        for(StatEntry tmpr : entriesSet)
//            res += tmpr.getTimeofCheck();
//        return res;
//    }
    
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
        s += "Matching " + checking + " " + checked + "\n";
        for(String tmp : keySet)
        {            
            s += "Statistic of " + tmp + "\n";
            s += table.get(tmp) + "\n";
        }        
        return s;
    }
}
