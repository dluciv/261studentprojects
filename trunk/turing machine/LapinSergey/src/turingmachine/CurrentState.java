package turingmachine;
import java.util.Vector;

/**
 *
 * @author Lapin Sergey 261group
 */
public class CurrentState {
    public Vector<Character> tape;
    public int tapePointer;
    public Rule rule;

    public String getString()
    {
        String res = "";
        res += getTapeString();
        res += " ";
        res += rule.getString();
        return res;
    }
    
    public String getTapeString()
    {
        String res = "";
        int i = 0;
        for(Character tmp : tape)
        {
            if(i == tapePointer)
                res += "(";
            res += tmp;
            if(i == tapePointer)
                res += ")";
            i++;
        }
        return res;
    }

    public Object[] createRuleInfo(){
        Object res[] = new Object[6];
        Object ruletmp[] = rule.createRuleInfo();
        res[0] = getTapeString();
        for(int i = 1; i < 6; i++)
            res[i] = ruletmp[i - 1];
        return res;
    }
}
