/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package turingmachine;
import java.util.HashSet;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class RulesTable {
    public HashSet<Rule> rules = new HashSet<Rule>();

    public Rule findByCondition(InitialCondition cond){
        for(Rule tmp : rules)
            if(tmp.getInitCondition().equals(cond))
                return tmp;
        return null;
    }

    public Rule findByAction(Action cond){
        for(Rule tmp : rules)
            if(tmp.getAct().equals(cond))
                return tmp;
        return null;
    }

    public Object[][] createTableInfo(){
        Object res[][] = new Object[rules.size()][5];
        int i = 0;
        for(Rule tmprule : rules){
            res[i] = tmprule.createRuleInfo();
            i++;
        }
        return res;
    }
    public Vector<Vector<Object>> createTableVectorInfo(){
        Vector<Vector<Object>> res = new Vector<Vector<Object>>();
        for(Rule tmprule : rules){
            res.add(tmprule.createRuleVectorInfo());
        }
        return res;
    }
}
