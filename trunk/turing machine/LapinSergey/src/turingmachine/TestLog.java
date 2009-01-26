/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package turingmachine;
import java.util.Vector;
/**
 *
 * @author Administrator
 */

public class TestLog {

    public TestLog(Vector<Character> inittape, ProgrammLog log) {
        this.inittape = inittape;
        this.log = log;
    }
    public Vector<Character> inittape;
    public ProgrammLog log;

    public TestLog() {

    }

    public Object[][] createTableInfo(){
        return log.createTableInfo();
    }

    public String getTapeString()
    {
        String res = "";
        for(Character tmp : inittape)
            res += tmp;
        return res;
    }

    public String getString()
    {
        String res = "inittape:";
        res += getTapeString();
        res += "\n";
        res += "programm:\n";
        res += log.getString();
        return res;
    }
}
