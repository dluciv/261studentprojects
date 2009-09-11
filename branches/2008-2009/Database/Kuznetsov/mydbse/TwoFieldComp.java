
package mydbse;

/**
 *
 * @author Kirill Kuznetsov
 */
import java.util.Comparator;
public class TwoFieldComp implements Comparator<Record>{
    private int first;
    private int second;
    TwoFieldComp(int first, int second){
        this.first = first;
        this.second = second;
    }
    public int compare(Record r1, Record r2){
        OneFieldComp c1 = new OneFieldComp(first),
                     c2 = new OneFieldComp(second);
        if(c1.compare(r1, r2) == 0)
            return c2.compare(r1, r2);
        else
            return c1.compare(r1, r2);
    }

}
