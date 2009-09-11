/**
 *
 * @author Lapin Sergey 261group
 */
package turingmachine;
import java.util.Vector;

public class ProgrammLog {
    public Vector<CurrentState> wholeTape = new Vector<CurrentState>();

    public void addState(CurrentState tmp)
    {        
        wholeTape.add(tmp);
    }

    public String getString()
    {
        String res = "";
        for(CurrentState tmp : wholeTape)
        {
            if(tmp == null)
            {
                res += "There are no such rule - reject" + "\n";
                return res;
            }
            res += tmp.getString() + "\n";
        }
        return res;
    }

    public Object[][] createTableInfo(){
        Object res[][] = new Object[wholeTape.size()][6];
        int i = 0;
        for(CurrentState tmprule : wholeTape){
            if(tmprule == null)
            {
                res[i][0] = "There are no such rule - reject";
                res[i][1] = "";
                res[i][2] = "";
                res[i][3] = "";
                res[i][4] = "";
                res[i][5] = "";
                return res;
            }
            res[i] = tmprule.createRuleInfo();
            i++;
        }
        return res;
    }

}
