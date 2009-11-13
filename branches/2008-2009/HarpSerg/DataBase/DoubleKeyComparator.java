/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
import java.util.Comparator;

public class DoubleKeyComparator implements Comparator<Entry>{
    private int first;
    private int second;
    public DoubleKeyComparator(int first, int second){
        this.first = first;
        this.second = second;
    }
    public int compare(Entry ent1, Entry ent2){
        SingleKeyComparator c1 = new SingleKeyComparator(first),
                     c2 = new SingleKeyComparator(second);
        if (c1.compare(ent1, ent2) == 0) {
            return c2.compare(ent1, ent2);
        } else {
            return c1.compare(ent1, ent2);
        }
    }
}
