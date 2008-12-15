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
    
    public boolean equal(Condition other) {  
        if(other.condition.size() != condition.size())
            return false;
        if(!other.condition.equals(condition))
            return false;
        return true;
    }
    
    @Override
    public String toString() {  
        
        String s = "";
        for(Integer tmp : condition){
            s += tmp + " ";            
        }            
        return s;
    }
}
