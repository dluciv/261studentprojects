/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Node;
import java.util.HashSet;

/**
 *
 * @author lapin
 */
public class Condition {
    public HashSet<Integer> condition;
    
    public Condition (Condition tmp)
    {
        condition = tmp.condition;        
    }
    
    public Condition (HashSet<Integer> tmp)
    {
        condition = tmp;        
    }
    
    public Condition ()
    {
        condition = new HashSet<Integer>();        
    }
    
    public Condition (Integer tmp)
    {
        condition = new HashSet<Integer>();
        condition.add(tmp);
    }
    
    public void mergeCondition(Condition tmp)
    {
        condition.addAll(tmp.condition);
    
    }    
    
    public Integer getFirst() {
        return (Integer)condition.toArray()[0];        
    }
    
    public boolean contains(Condition o) {
        return condition.containsAll(o.condition);        
    }
    
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Condition))
            return false;
        Condition res = (Condition)o;
        return res.condition.equals(condition);        
    }
    
    @Override
    public String toString() {  
        
        String s = "";        
               
        for(Integer tmp : condition){
            s += tmp;            
        }
        
        return s;
    }
}
