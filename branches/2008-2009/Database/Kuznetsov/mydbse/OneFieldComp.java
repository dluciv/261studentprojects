
package mydbse;

/**
 *
 * @author Kirill Kuznetsov
 */
import java.util.Comparator;
public class OneFieldComp implements Comparator<Record>{
    private int field;
    OneFieldComp(int field){
        this.field = field;
    }
    public int compare(Record r1, Record r2){
        if(field == Record.SURNAME)
            return r1.getSN().compareTo(r2.getSN());
        else
            return r1.getTel().compareTo(r2.getTel());
    }
}
